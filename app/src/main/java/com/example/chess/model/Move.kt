package com.example.chess.model

data class Move(
    val from: Square,
    val to: Square,
    val promotion: PieceType? = null,
    val isEnPassant: Boolean = false,
    val isCastlingKingside: Boolean = false,
    val isCastlingQueenside: Boolean = false,
    val capturedPiece: Piece? = null,
    val san: String = ""
) {
    val isCapture: Boolean get() = capturedPiece != null || isEnPassant

    val uci: String
        get() {
            val base = "${from.algebraic}${to.algebraic}"
            return if (promotion != null) {
                base + promotion.symbol.lowercaseChar()
            } else {
                base
            }
        }
}
