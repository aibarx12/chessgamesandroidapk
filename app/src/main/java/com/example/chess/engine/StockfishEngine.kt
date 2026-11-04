package com.example.chess.engine

import com.example.chess.model.BoardState
import com.example.chess.model.Move
import com.example.chess.model.PieceColor
import com.example.chess.model.PieceType
import com.example.chess.model.SanParser
import com.example.chess.model.Square
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

data class EngineEvaluation(
    val scoreCp: Int,                // Centipawns from White's perspective (+150 = White +1.50)
    val mateInMoves: Int? = null,    // Positive = White mates in N, Negative = Black mates in N
    val bestMove: Move? = null,
    val bestMoveSan: String = "",
    val principalVariation: List<String> = emptyList(),
    val depth: Int = 0,
    val nodesEvaluated: Int = 0
) {
    val formattedScore: String
        get() {
            if (mateInMoves != null) {
                return if (mateInMoves > 0) "#+$mateInMoves" else "#$mateInMoves"
            }
            val pawns = scoreCp / 100.0
            return if (pawns > 0) "+%.2f".format(pawns) else "%.2f".format(pawns)
        }

    val winPercentageWhite: Float
        get() {
            // Lichess winning chances formula: 50 + 50 * (2 / (1 + exp(-0.00368208 * cp)) - 1)
            val cp = scoreCp.coerceIn(-2000, 2000)
            val prob = 1.0 / (1.0 + Math.exp(-0.00368208 * cp))
            return (prob * 100).toFloat().coerceIn(0f, 100f)
        }
}

object StockfishEngine {

    // Piece-Square Tables (White perspective: rank 0=1st rank, rank 7=8th rank)
    // Values derived from PeSTO / Stockfish evaluation tables
    private val PAWN_PST = intArrayOf(
        0,   0,   0,   0,   0,   0,   0,   0,
        5,  10,  10, -20, -20,  10,  10,   5,
        5,  -5, -10,   0,   0, -10,  -5,   5,
        0,   0,   0,  20,  20,   0,   0,   0,
        5,   5,  10,  25,  25,  10,   5,   5,
        10,  10,  20,  30,  30,  20,  10,  10,
        50,  50,  50,  50,  50,  50,  50,  50,
        0,   0,   0,   0,   0,   0,   0,   0
    )

    private val KNIGHT_PST = intArrayOf(
        -50, -40, -30, -30, -30, -30, -40, -50,
        -40, -20,   0,   5,   5,   0, -20, -40,
        -30,   5,  10,  15,  15,  10,   5, -30,
        -30,   0,  15,  20,  20,  15,   0, -30,
        -30,   5,  15,  20,  20,  15,   5, -30,
        -30,   0,  10,  15,  15,  10,   0, -30,
        -40, -20,   0,   0,   0,   0, -20, -40,
        -50, -40, -30, -30, -30, -30, -40, -50
    )

    private val BISHOP_PST = intArrayOf(
        -20, -10, -10, -10, -10, -10, -10, -20,
        -10,   5,   0,   0,   0,   0,   5, -10,
        -10,  10,  10,  10,  10,  10,  10, -10,
        -10,   0,  10,  10,  10,  10,   0, -10,
        -10,   5,   5,  10,  10,   5,   5, -10,
        -10,   0,   5,  10,  10,   5,   0, -10,
        -10,   0,   0,   0,   0,   0,   0, -10,
        -20, -10, -10, -10, -10, -10, -10, -20
    )

    private val ROOK_PST = intArrayOf(
        0,   0,   0,   5,   5,   0,   0,   0,
        -5,   0,   0,   0,   0,   0,   0,  -5,
        -5,   0,   0,   0,   0,   0,   0,  -5,
        -5,   0,   0,   0,   0,   0,   0,  -5,
        -5,   0,   0,   0,   0,   0,   0,  -5,
        -5,   0,   0,   0,   0,   0,   0,  -5,
        5,  10,  10,  10,  10,  10,  10,   5,
        0,   0,   0,   0,   0,   0,   0,   0
    )

    private val QUEEN_PST = intArrayOf(
        -20, -10, -10,  -5,  -5, -10, -10, -20,
        -10,   0,   5,   0,   0,   0,   0, -10,
        -10,   5,   5,   5,   5,   5,   0, -10,
        0,   0,   5,   5,   5,   5,   0,  -5,
        -5,   0,   5,   5,   5,   5,   0,  -5,
        -10,   0,   5,   5,   5,   5,   0, -10,
        -10,   0,   0,   0,   0,   0,   0, -10,
        -20, -10, -10,  -5,  -5, -10, -10, -20
    )

    private val KING_MIDDLE_PST = intArrayOf(
        20,  30,  10,   0,   0,  10,  30,  20,
        20,  20,   0,   0,   0,   0,  20,  20,
        -10, -20, -20, -20, -20, -20, -20, -10,
        -20, -30, -30, -40, -40, -30, -30, -20,
        -30, -40, -40, -50, -50, -40, -40, -30,
        -30, -40, -40, -50, -50, -40, -40, -30,
        -30, -40, -40, -50, -50, -40, -40, -30,
        -30, -40, -40, -50, -50, -40, -40, -30
    )

    private const val CHECKMATE_SCORE = 30000

    /**
     * Evaluates static position relative to side to move (White positive, Black negative if converted)
     */
    fun evaluateStatic(state: BoardState): Int {
        var whiteMaterial = 0
        var blackMaterial = 0
        var whitePst = 0
        var blackPst = 0
        var whiteBishops = 0
        var blackBishops = 0

        for (i in 0..63) {
            val piece = state.squares[i] ?: continue
            val rank = i / 8
            val file = i % 8
            val flippedIdx = (7 - rank) * 8 + file

            val baseVal = piece.type.baseValue
            val pstVal = when (piece.type) {
                PieceType.PAWN -> if (piece.color == PieceColor.WHITE) PAWN_PST[i] else PAWN_PST[flippedIdx]
                PieceType.KNIGHT -> if (piece.color == PieceColor.WHITE) KNIGHT_PST[i] else KNIGHT_PST[flippedIdx]
                PieceType.BISHOP -> if (piece.color == PieceColor.WHITE) BISHOP_PST[i] else BISHOP_PST[flippedIdx]
                PieceType.ROOK -> if (piece.color == PieceColor.WHITE) ROOK_PST[i] else ROOK_PST[flippedIdx]
                PieceType.QUEEN -> if (piece.color == PieceColor.WHITE) QUEEN_PST[i] else QUEEN_PST[flippedIdx]
                PieceType.KING -> if (piece.color == PieceColor.WHITE) KING_MIDDLE_PST[i] else KING_MIDDLE_PST[flippedIdx]
            }

            if (piece.color == PieceColor.WHITE) {
                whiteMaterial += baseVal
                whitePst += pstVal
                if (piece.type == PieceType.BISHOP) whiteBishops++
            } else {
                blackMaterial += baseVal
                blackPst += pstVal
                if (piece.type == PieceType.BISHOP) blackBishops++
            }
        }

        // Bishop pair bonus
        if (whiteBishops >= 2) whiteMaterial += 35
        if (blackBishops >= 2) blackMaterial += 35

        val totalWhite = whiteMaterial + whitePst
        val totalBlack = blackMaterial + blackPst

        return totalWhite - totalBlack
    }

    suspend fun analyze(state: BoardState, targetDepth: Int = 3): EngineEvaluation = withContext(Dispatchers.Default) {
        if (state.isCheckmate) {
            return@withContext if (state.turn == PieceColor.WHITE) {
                EngineEvaluation(scoreCp = -CHECKMATE_SCORE, mateInMoves = -1, depth = targetDepth)
            } else {
                EngineEvaluation(scoreCp = CHECKMATE_SCORE, mateInMoves = 1, depth = targetDepth)
            }
        }

        if (state.isStalemate) {
            return@withContext EngineEvaluation(scoreCp = 0, depth = targetDepth)
        }

        var nodeCount = 0
        var bestMove: Move? = null
        var bestScore = if (state.turn == PieceColor.WHITE) -100000 else 100000
        val legalMoves = state.generateLegalMoves().sortedByDescending { moveScoreGuess(state, it) }

        if (legalMoves.isEmpty()) {
            return@withContext EngineEvaluation(scoreCp = 0)
        }

        // Root Alpha-Beta
        var alpha = -100000
        var beta = 100000

        for (move in legalMoves) {
            if (!coroutineContext.isActive) break
            val nextState = state.makeMove(move)
            nodeCount++

            val score = -alphaBeta(
                state = nextState,
                depth = targetDepth - 1,
                alpha = if (state.turn == PieceColor.WHITE) -beta else alpha,
                beta = if (state.turn == PieceColor.WHITE) -alpha else beta,
                colorSign = if (state.turn == PieceColor.WHITE) -1 else 1
            )

            if (state.turn == PieceColor.WHITE) {
                if (score > bestScore) {
                    bestScore = score
                    bestMove = move
                }
                alpha = maxOf(alpha, score)
                if (alpha >= beta) break
            } else {
                // For black, bestScore is minimum or score inverted
                if (score < bestScore) {
                    bestScore = score
                    bestMove = move
                }
                beta = minOf(beta, score)
                if (beta <= alpha) break
            }
        }

        val pv = mutableListOf<String>()
        if (bestMove != null) {
            val san = SanParser.moveToSan(state, bestMove)
            pv.add(san)
        }

        var mateIn: Int? = null
        val finalScore: Int
        if (bestScore > CHECKMATE_SCORE - 100) {
            mateIn = (CHECKMATE_SCORE - bestScore + 1) / 2
            finalScore = CHECKMATE_SCORE
        } else if (bestScore < -CHECKMATE_SCORE + 100) {
            mateIn = -((CHECKMATE_SCORE + bestScore + 1) / 2)
            finalScore = -CHECKMATE_SCORE
        } else {
            finalScore = bestScore
        }

        EngineEvaluation(
            scoreCp = finalScore,
            mateInMoves = mateIn,
            bestMove = bestMove,
            bestMoveSan = pv.firstOrNull() ?: "",
            principalVariation = pv,
            depth = targetDepth,
            nodesEvaluated = nodeCount
        )
    }

    private fun alphaBeta(
        state: BoardState,
        depth: Int,
        alpha: Int,
        beta: Int,
        colorSign: Int
    ): Int {
        if (depth <= 0) {
            return quiescence(state, alpha, beta, colorSign)
        }

        val moves = state.generateLegalMoves().sortedByDescending { moveScoreGuess(state, it) }
        if (moves.isEmpty()) {
            return if (state.isCheck) {
                -CHECKMATE_SCORE - depth
            } else {
                0
            }
        }

        var a = alpha
        for (move in moves) {
            val nextState = state.makeMove(move)
            val score = -alphaBeta(nextState, depth - 1, -beta, -a, -colorSign)
            if (score >= beta) return beta
            if (score > a) a = score
        }
        return a
    }

    private fun quiescence(
        state: BoardState,
        alpha: Int,
        beta: Int,
        colorSign: Int
    ): Int {
        val standPat = evaluateStatic(state) * colorSign
        if (standPat >= beta) return beta
        var a = maxOf(alpha, standPat)

        val captureMoves = state.generateLegalMoves().filter { it.isCapture }
            .sortedByDescending { moveScoreGuess(state, it) }

        for (move in captureMoves) {
            val nextState = state.makeMove(move)
            val score = -quiescence(nextState, -beta, -a, -colorSign)
            if (score >= beta) return beta
            if (score > a) a = score
        }
        return a
    }

    private fun moveScoreGuess(state: BoardState, move: Move): Int {
        var score = 0
        if (move.capturedPiece != null) {
            val attacker = state.getPiece(move.from)
            val victimValue = move.capturedPiece.type.baseValue
            val attackerValue = attacker?.type?.baseValue ?: 100
            score += 10 * victimValue - attackerValue
        }
        if (move.promotion != null) {
            score += move.promotion.baseValue
        }
        return score
    }
}
