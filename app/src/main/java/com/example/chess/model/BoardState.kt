package com.example.chess.model

data class CastlingRights(
    val whiteKingside: Boolean = true,
    val whiteQueenside: Boolean = true,
    val blackKingside: Boolean = true,
    val blackQueenside: Boolean = true
) {
    fun toFen(): String {
        val sb = StringBuilder()
        if (whiteKingside) sb.append('K')
        if (whiteQueenside) sb.append('Q')
        if (blackKingside) sb.append('k')
        if (blackQueenside) sb.append('q')
        return if (sb.isEmpty()) "-" else sb.toString()
    }

    companion object {
        fun fromFen(fen: String): CastlingRights {
            return CastlingRights(
                whiteKingside = fen.contains('K'),
                whiteQueenside = fen.contains('Q'),
                blackKingside = fen.contains('k'),
                blackQueenside = fen.contains('q')
            )
        }
    }
}

class BoardState(
    val squares: Array<Piece?>,
    val turn: PieceColor = PieceColor.WHITE,
    val castlingRights: CastlingRights = CastlingRights(),
    val enPassantSquare: Square? = null,
    val halfmoveClock: Int = 0,
    val fullmoveNumber: Int = 1
) {
    fun getPiece(square: Square): Piece? = squares[square.index]
    fun getPiece(file: Int, rank: Int): Piece? = squares[rank * 8 + file]

    fun isSquareAttacked(square: Square, attackerColor: PieceColor): Boolean {
        val f = square.file
        val r = square.rank

        // 1. Attacked by pawns
        val pawnPushes = if (attackerColor == PieceColor.WHITE) -1 else 1
        val attackerPawnRank = r + pawnPushes
        if (attackerPawnRank in 0..7) {
            for (pawnFile in listOf(f - 1, f + 1)) {
                if (pawnFile in 0..7) {
                    val p = getPiece(pawnFile, attackerPawnRank)
                    if (p != null && p.color == attackerColor && p.type == PieceType.PAWN) {
                        return true
                    }
                }
            }
        }

        // 2. Attacked by Knights
        val knightOffsets = listOf(
            Pair(1, 2), Pair(2, 1), Pair(-1, 2), Pair(-2, 1),
            Pair(1, -2), Pair(2, -1), Pair(-1, -2), Pair(-2, -1)
        )
        for (offset in knightOffsets) {
            val nf = f + offset.first
            val nr = r + offset.second
            if (nf in 0..7 && nr in 0..7) {
                val p = getPiece(nf, nr)
                if (p != null && p.color == attackerColor && p.type == PieceType.KNIGHT) {
                    return true
                }
            }
        }

        // 3. Attacked by King
        val kingOffsets = listOf(
            Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1),
            Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1)
        )
        for (offset in kingOffsets) {
            val kf = f + offset.first
            val kr = r + offset.second
            if (kf in 0..7 && kr in 0..7) {
                val p = getPiece(kf, kr)
                if (p != null && p.color == attackerColor && p.type == PieceType.KING) {
                    return true
                }
            }
        }

        // 4. Straight lines (Rook / Queen)
        val straightDirections = listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
        for (dir in straightDirections) {
            var step = 1
            while (true) {
                val curF = f + dir.first * step
                val curR = r + dir.second * step
                if (curF !in 0..7 || curR !in 0..7) break
                val p = getPiece(curF, curR)
                if (p != null) {
                    if (p.color == attackerColor && (p.type == PieceType.ROOK || p.type == PieceType.QUEEN)) {
                        return true
                    }
                    break
                }
                step++
            }
        }

        // 5. Diagonal lines (Bishop / Queen)
        val diagonalDirections = listOf(Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1))
        for (dir in diagonalDirections) {
            var step = 1
            while (true) {
                val curF = f + dir.first * step
                val curR = r + dir.second * step
                if (curF !in 0..7 || curR !in 0..7) break
                val p = getPiece(curF, curR)
                if (p != null) {
                    if (p.color == attackerColor && (p.type == PieceType.BISHOP || p.type == PieceType.QUEEN)) {
                        return true
                    }
                    break
                }
                step++
            }
        }

        return false
    }

    fun findKing(color: PieceColor): Square? {
        for (i in 0..63) {
            val p = squares[i]
            if (p != null && p.color == color && p.type == PieceType.KING) {
                return Square.fromIndex(i)
            }
        }
        return null
    }

    val isCheck: Boolean
        get() {
            val kingSq = findKing(turn) ?: return false
            return isSquareAttacked(kingSq, turn.opposite)
        }

    fun generatePseudoLegalMoves(): List<Move> {
        val moves = mutableListOf<Move>()
        for (i in 0..63) {
            val piece = squares[i] ?: continue
            if (piece.color != turn) continue
            val from = Square.fromIndex(i)

            when (piece.type) {
                PieceType.PAWN -> generatePawnMoves(from, piece.color, moves)
                PieceType.KNIGHT -> generateKnightMoves(from, piece.color, moves)
                PieceType.BISHOP -> generateSlidingMoves(from, piece.color, listOf(Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1)), moves)
                PieceType.ROOK -> generateSlidingMoves(from, piece.color, listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1)), moves)
                PieceType.QUEEN -> generateSlidingMoves(from, piece.color, listOf(
                    Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1),
                    Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1)
                ), moves)
                PieceType.KING -> generateKingMoves(from, piece.color, moves)
            }
        }
        return moves
    }

    private fun generatePawnMoves(from: Square, color: PieceColor, moves: MutableList<Move>) {
        val forward = if (color == PieceColor.WHITE) 1 else -1
        val startRank = if (color == PieceColor.WHITE) 1 else 6
        val promoRank = if (color == PieceColor.WHITE) 7 else 0

        // Single step forward
        val nextRank = from.rank + forward
        if (nextRank in 0..7 && getPiece(from.file, nextRank) == null) {
            val target = Square(from.file, nextRank)
            if (nextRank == promoRank) {
                listOf(PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT).forEach { promo ->
                    moves.add(Move(from, target, promotion = promo))
                }
            } else {
                moves.add(Move(from, target))
                // Double step from start rank
                if (from.rank == startRank) {
                    val doubleRank = from.rank + forward * 2
                    if (getPiece(from.file, doubleRank) == null) {
                        moves.add(Move(from, Square(from.file, doubleRank)))
                    }
                }
            }
        }

        // Diagonal captures
        for (fileOffset in listOf(-1, 1)) {
            val capFile = from.file + fileOffset
            if (capFile in 0..7 && nextRank in 0..7) {
                val target = Square(capFile, nextRank)
                val destPiece = getPiece(target)
                if (destPiece != null && destPiece.color != color) {
                    if (nextRank == promoRank) {
                        listOf(PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT).forEach { promo ->
                            moves.add(Move(from, target, promotion = promo, capturedPiece = destPiece))
                        }
                    } else {
                        moves.add(Move(from, target, capturedPiece = destPiece))
                    }
                } else if (enPassantSquare != null && target == enPassantSquare) {
                    val epCaptured = getPiece(capFile, from.rank)
                    moves.add(Move(from, target, isEnPassant = true, capturedPiece = epCaptured))
                }
            }
        }
    }

    private fun generateKnightMoves(from: Square, color: PieceColor, moves: MutableList<Move>) {
        val offsets = listOf(
            Pair(1, 2), Pair(2, 1), Pair(-1, 2), Pair(-2, 1),
            Pair(1, -2), Pair(2, -1), Pair(-1, -2), Pair(-2, -1)
        )
        for (offset in offsets) {
            val f = from.file + offset.first
            val r = from.rank + offset.second
            if (f in 0..7 && r in 0..7) {
                val target = Square(f, r)
                val targetPiece = getPiece(target)
                if (targetPiece == null) {
                    moves.add(Move(from, target))
                } else if (targetPiece.color != color) {
                    moves.add(Move(from, target, capturedPiece = targetPiece))
                }
            }
        }
    }

    private fun generateSlidingMoves(
        from: Square,
        color: PieceColor,
        directions: List<Pair<Int, Int>>,
        moves: MutableList<Move>
    ) {
        for (dir in directions) {
            var step = 1
            while (true) {
                val f = from.file + dir.first * step
                val r = from.rank + dir.second * step
                if (f !in 0..7 || r !in 0..7) break
                val target = Square(f, r)
                val targetPiece = getPiece(target)
                if (targetPiece == null) {
                    moves.add(Move(from, target))
                } else {
                    if (targetPiece.color != color) {
                        moves.add(Move(from, target, capturedPiece = targetPiece))
                    }
                    break
                }
                step++
            }
        }
    }

    private fun generateKingMoves(from: Square, color: PieceColor, moves: MutableList<Move>) {
        val offsets = listOf(
            Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1),
            Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1)
        )
        for (offset in offsets) {
            val f = from.file + offset.first
            val r = from.rank + offset.second
            if (f in 0..7 && r in 0..7) {
                val target = Square(f, r)
                val targetPiece = getPiece(target)
                if (targetPiece == null) {
                    moves.add(Move(from, target))
                } else if (targetPiece.color != color) {
                    moves.add(Move(from, target, capturedPiece = targetPiece))
                }
            }
        }

        // Castling moves
        if (color == PieceColor.WHITE && from == Square(4, 0) && !isSquareAttacked(from, PieceColor.BLACK)) {
            // White Kingside
            if (castlingRights.whiteKingside &&
                getPiece(5, 0) == null && getPiece(6, 0) == null &&
                !isSquareAttacked(Square(5, 0), PieceColor.BLACK) &&
                !isSquareAttacked(Square(6, 0), PieceColor.BLACK) &&
                getPiece(7, 0) == Piece(PieceType.ROOK, PieceColor.WHITE)
            ) {
                moves.add(Move(from, Square(6, 0), isCastlingKingside = true))
            }
            // White Queenside
            if (castlingRights.whiteQueenside &&
                getPiece(3, 0) == null && getPiece(2, 0) == null && getPiece(1, 0) == null &&
                !isSquareAttacked(Square(3, 0), PieceColor.BLACK) &&
                !isSquareAttacked(Square(2, 0), PieceColor.BLACK) &&
                getPiece(0, 0) == Piece(PieceType.ROOK, PieceColor.WHITE)
            ) {
                moves.add(Move(from, Square(2, 0), isCastlingQueenside = true))
            }
        } else if (color == PieceColor.BLACK && from == Square(4, 7) && !isSquareAttacked(from, PieceColor.WHITE)) {
            // Black Kingside
            if (castlingRights.blackKingside &&
                getPiece(5, 7) == null && getPiece(6, 7) == null &&
                !isSquareAttacked(Square(5, 7), PieceColor.WHITE) &&
                !isSquareAttacked(Square(6, 7), PieceColor.WHITE) &&
                getPiece(7, 7) == Piece(PieceType.ROOK, PieceColor.BLACK)
            ) {
                moves.add(Move(from, Square(6, 7), isCastlingKingside = true))
            }
            // Black Queenside
            if (castlingRights.blackQueenside &&
                getPiece(3, 7) == null && getPiece(2, 7) == null && getPiece(1, 7) == null &&
                !isSquareAttacked(Square(3, 7), PieceColor.WHITE) &&
                !isSquareAttacked(Square(2, 7), PieceColor.WHITE) &&
                getPiece(0, 7) == Piece(PieceType.ROOK, PieceColor.BLACK)
            ) {
                moves.add(Move(from, Square(2, 7), isCastlingQueenside = true))
            }
        }
    }

    fun makeMove(move: Move): BoardState {
        val newSquares = squares.clone()
        val movingPiece = newSquares[move.from.index] ?: return this

        // Remove from start square
        newSquares[move.from.index] = null

        // Handle En Passant capture
        if (move.isEnPassant) {
            val capturedPawnRank = if (movingPiece.color == PieceColor.WHITE) move.to.rank - 1 else move.to.rank + 1
            newSquares[Square(move.to.file, capturedPawnRank).index] = null
        }

        // Handle Castling Rook move
        if (move.isCastlingKingside) {
            val rRank = move.from.rank
            val rook = newSquares[Square(7, rRank).index]
            newSquares[Square(7, rRank).index] = null
            newSquares[Square(5, rRank).index] = rook
        } else if (move.isCastlingQueenside) {
            val rRank = move.from.rank
            val rook = newSquares[Square(0, rRank).index]
            newSquares[Square(0, rRank).index] = null
            newSquares[Square(3, rRank).index] = rook
        }

        // Put piece at destination (with promotion if any)
        val finalPiece = if (move.promotion != null) {
            Piece(move.promotion, movingPiece.color)
        } else {
            movingPiece
        }
        newSquares[move.to.index] = finalPiece

        // Update Castling rights
        var wK = castlingRights.whiteKingside
        var wQ = castlingRights.whiteQueenside
        var bK = castlingRights.blackKingside
        var bQ = castlingRights.blackQueenside

        if (movingPiece.type == PieceType.KING) {
            if (movingPiece.color == PieceColor.WHITE) {
                wK = false
                wQ = false
            } else {
                bK = false
                bQ = false
            }
        }
        if (move.from == Square(0, 0) || move.to == Square(0, 0)) wQ = false
        if (move.from == Square(7, 0) || move.to == Square(7, 0)) wK = false
        if (move.from == Square(0, 7) || move.to == Square(0, 7)) bQ = false
        if (move.from == Square(7, 7) || move.to == Square(7, 7)) bK = false

        // Update En Passant square
        val newEnPassant: Square? = if (movingPiece.type == PieceType.PAWN && Math.abs(move.to.rank - move.from.rank) == 2) {
            val midRank = (move.to.rank + move.from.rank) / 2
            Square(move.from.file, midRank)
        } else {
            null
        }

        val isCap = move.isCapture || move.isEnPassant
        val newHalfmove = if (movingPiece.type == PieceType.PAWN || isCap) 0 else halfmoveClock + 1
        val newFullmove = if (turn == PieceColor.BLACK) fullmoveNumber + 1 else fullmoveNumber

        return BoardState(
            squares = newSquares,
            turn = turn.opposite,
            castlingRights = CastlingRights(wK, wQ, bK, bQ),
            enPassantSquare = newEnPassant,
            halfmoveClock = newHalfmove,
            fullmoveNumber = newFullmove
        )
    }

    fun generateLegalMoves(): List<Move> {
        val pseudo = generatePseudoLegalMoves()
        val legal = mutableListOf<Move>()
        for (m in pseudo) {
            val nextState = makeMove(m)
            val kingSq = nextState.findKing(turn)
            if (kingSq != null && !nextState.isSquareAttacked(kingSq, turn.opposite)) {
                legal.add(m)
            }
        }
        return legal
    }

    val isCheckmate: Boolean
        get() = isCheck && generateLegalMoves().isEmpty()

    val isStalemate: Boolean
        get() = !isCheck && generateLegalMoves().isEmpty()

    fun toFen(): String {
        val sb = StringBuilder()
        for (r in 7 downTo 0) {
            var emptyCount = 0
            for (f in 0..7) {
                val p = getPiece(f, r)
                if (p == null) {
                    emptyCount++
                } else {
                    if (emptyCount > 0) {
                        sb.append(emptyCount)
                        emptyCount = 0
                    }
                    sb.append(p.fenChar)
                }
            }
            if (emptyCount > 0) sb.append(emptyCount)
            if (r > 0) sb.append('/')
        }

        sb.append(' ')
        sb.append(if (turn == PieceColor.WHITE) 'w' else 'b')
        sb.append(' ')
        sb.append(castlingRights.toFen())
        sb.append(' ')
        sb.append(enPassantSquare?.algebraic ?: "-")
        sb.append(' ')
        sb.append(halfmoveClock)
        sb.append(' ')
        sb.append(fullmoveNumber)

        return sb.toString()
    }

    companion object {
        fun initial(): BoardState {
            return fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
        }

        fun fromFen(fen: String): BoardState {
            val parts = fen.trim().split("\\s+".toRegex())
            val boardPart = parts.getOrNull(0) ?: "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
            val turnPart = parts.getOrNull(1) ?: "w"
            val castlingPart = parts.getOrNull(2) ?: "KQkq"
            val epPart = parts.getOrNull(3) ?: "-"
            val halfmove = parts.getOrNull(4)?.toIntOrNull() ?: 0
            val fullmove = parts.getOrNull(5)?.toIntOrNull() ?: 1

            val sqs = arrayOfNulls<Piece>(64)
            val ranks = boardPart.split('/')
            for ((rankIndex, rankStr) in ranks.reversed().withIndex()) {
                if (rankIndex > 7) break
                var fileIndex = 0
                for (ch in rankStr) {
                    if (ch.isDigit()) {
                        fileIndex += ch - '0'
                    } else {
                        val piece = Piece.fromFenChar(ch)
                        if (piece != null && fileIndex in 0..7) {
                            sqs[rankIndex * 8 + fileIndex] = piece
                        }
                        fileIndex++
                    }
                }
            }

            val turn = if (turnPart.lowercase() == "b") PieceColor.BLACK else PieceColor.WHITE
            val cr = CastlingRights.fromFen(castlingPart)
            val ep = if (epPart != "-") try { Square.fromAlgebraic(epPart) } catch (e: Exception) { null } else null

            return BoardState(
                squares = sqs,
                turn = turn,
                castlingRights = cr,
                enPassantSquare = ep,
                halfmoveClock = halfmove,
                fullmoveNumber = fullmove
            )
        }
    }
}
