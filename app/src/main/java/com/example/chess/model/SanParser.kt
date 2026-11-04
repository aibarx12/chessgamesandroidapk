package com.example.chess.model

object SanParser {

    /**
     * Finds the legal Move matching a SAN string in the current BoardState.
     */
    fun parseSan(state: BoardState, sanInput: String): Move? {
        val clean = sanInput.trim()
            .replace("!", "")
            .replace("?", "")
            .replace("+", "")
            .replace("#", "")
            .replace("x", "")
            .replace(":", "")

        val legalMoves = state.generateLegalMoves()
        if (clean.isEmpty()) return null

        // 1. Castling
        if (clean.equals("O-O", ignoreCase = true) || clean == "0-0") {
            return legalMoves.find { it.isCastlingKingside }
        }
        if (clean.equals("O-O-O", ignoreCase = true) || clean == "0-0-0") {
            return legalMoves.find { it.isCastlingQueenside }
        }

        // 2. Promotion check: e.g. "e8=Q" or "e8Q" or "cxd8Q"
        var targetPromo: PieceType? = null
        var working = clean
        if (clean.contains('=')) {
            val parts = clean.split('=')
            working = parts[0]
            if (parts.size > 1 && parts[1].isNotEmpty()) {
                targetPromo = PieceType.fromChar(parts[1][0])
            }
        } else if (clean.length >= 3 && clean.last().isUpperCase() && PieceType.fromChar(clean.last()) != null && clean[clean.length - 2].isDigit()) {
            targetPromo = PieceType.fromChar(clean.last())
            working = clean.substring(0, clean.length - 1)
        }

        // Target square is usually the last 2 characters of 'working'
        if (working.length < 2) return null
        val targetSquareStr = working.takeLast(2)
        val targetSquare = try {
            Square.fromAlgebraic(targetSquareStr)
        } catch (e: Exception) {
            return null
        }

        val prefix = working.dropLast(2)

        // Piece type: if first char is uppercase letter other than a-h, it's piece type
        val pieceType: PieceType
        val disambiguation: String

        if (prefix.isNotEmpty() && prefix[0].isUpperCase() && prefix[0] in "NBRQK") {
            pieceType = PieceType.fromChar(prefix[0]) ?: PieceType.PAWN
            disambiguation = prefix.substring(1)
        } else {
            pieceType = PieceType.PAWN
            disambiguation = prefix // might contain pawn file for captures e.g. "e" in "ed5"
        }

        val candidates = legalMoves.filter { move ->
            if (move.to != targetSquare) return@filter false
            val movingPiece = state.getPiece(move.from) ?: return@filter false
            if (movingPiece.type != pieceType) return@filter false
            if (targetPromo != null && move.promotion != targetPromo) return@filter false
            true
        }

        if (candidates.size == 1) {
            return candidates[0]
        }

        // Disambiguate
        return candidates.find { move ->
            when (disambiguation.length) {
                0 -> true
                1 -> {
                    val ch = disambiguation[0]
                    if (ch in 'a'..'h') {
                        move.from.file == (ch - 'a')
                    } else if (ch in '1'..'8') {
                        move.from.rank == (ch - '1')
                    } else {
                        false
                    }
                }
                2 -> {
                    move.from.algebraic == disambiguation
                }
                else -> false
            }
        } ?: candidates.firstOrNull()
    }

    /**
     * Generates a standard SAN representation for a move on the given board.
     */
    fun moveToSan(state: BoardState, move: Move): String {
        if (move.isCastlingKingside) return "O-O"
        if (move.isCastlingQueenside) return "O-O-O"

        val piece = state.getPiece(move.from) ?: return move.uci
        val sb = StringBuilder()

        if (piece.type != PieceType.PAWN) {
            sb.append(piece.type.symbol)

            // Disambiguation if another piece of same type can move to same square
            val others = state.generateLegalMoves().filter {
                it.to == move.to && it.from != move.from && state.getPiece(it.from)?.type == piece.type
            }
            if (others.isNotEmpty()) {
                val sameFile = others.any { it.from.file == move.from.file }
                val sameRank = others.any { it.from.rank == move.from.rank }
                if (!sameFile) {
                    sb.append(('a' + move.from.file))
                } else if (!sameRank) {
                    sb.append(move.from.rank + 1)
                } else {
                    sb.append(move.from.algebraic)
                }
            }
        } else {
            if (move.isCapture) {
                sb.append(('a' + move.from.file))
            }
        }

        if (move.isCapture) {
            sb.append('x')
        }

        sb.append(move.to.algebraic)

        if (move.promotion != null) {
            sb.append('=').append(move.promotion.symbol)
        }

        val nextState = state.makeMove(move)
        if (nextState.isCheckmate) {
            sb.append('#')
        } else if (nextState.isCheck) {
            sb.append('+')
        }

        return sb.toString()
    }
}
