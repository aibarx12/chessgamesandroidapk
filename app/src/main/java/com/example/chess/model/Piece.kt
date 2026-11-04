package com.example.chess.model

enum class PieceColor {
    WHITE,
    BLACK;

    val opposite: PieceColor
        get() = if (this == WHITE) BLACK else WHITE
}

enum class PieceType(val symbol: Char, val baseValue: Int) {
    PAWN('P', 100),
    KNIGHT('N', 320),
    BISHOP('B', 330),
    ROOK('R', 500),
    QUEEN('Q', 900),
    KING('K', 20000);

    companion object {
        fun fromChar(char: Char): PieceType? {
            return when (char.uppercaseChar()) {
                'P' -> PAWN
                'N' -> KNIGHT
                'B' -> BISHOP
                'R' -> ROOK
                'Q' -> QUEEN
                'K' -> KING
                else -> null
            }
        }
    }
}

data class Piece(val type: PieceType, val color: PieceColor) {
    val fenChar: Char
        get() = if (color == PieceColor.WHITE) type.symbol else type.symbol.lowercaseChar()

    companion object {
        fun fromFenChar(char: Char): Piece? {
            val type = PieceType.fromChar(char) ?: return null
            val color = if (char.isUpperCase()) PieceColor.WHITE else PieceColor.BLACK
            return Piece(type, color)
        }
    }
}
