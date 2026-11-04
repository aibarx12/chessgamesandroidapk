package com.example.chess.model

/**
 * Represents a square on the 8x8 chessboard.
 * file: 0 to 7 (a to h)
 * rank: 0 to 7 (1 to 8)
 * index: 0 to 63 (index = rank * 8 + file)
 */
data class Square(val file: Int, val rank: Int) {
    init {
        require(file in 0..7 && rank in 0..7) { "Square out of bounds: file=$file, rank=$rank" }
    }

    val index: Int get() = rank * 8 + file

    val isLightSquare: Boolean get() = (file + rank) % 2 != 0

    val algebraic: String
        get() = "${('a' + file)}${rank + 1}"

    companion object {
        fun fromIndex(index: Int): Square {
            require(index in 0..63) { "Index out of bounds: $index" }
            return Square(file = index % 8, rank = index / 8)
        }

        fun fromAlgebraic(str: String): Square {
            val s = str.trim().lowercase()
            require(s.length >= 2) { "Invalid algebraic square: $str" }
            val fileChar = s[0]
            val rankChar = s[1]
            val f = fileChar - 'a'
            val r = rankChar - '1'
            return Square(f, r)
        }
    }
}
