package com.example.chess.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.model.BoardState
import com.example.chess.model.BoardTheme
import com.example.chess.model.Move
import com.example.chess.model.PieceStyle
import com.example.chess.model.Square

@Composable
fun ChessBoardView(
    boardState: BoardState,
    selectedSquare: Square? = null,
    lastMove: Move? = null,
    bestMove: Move? = null,
    isFlipped: Boolean = false,
    boardTheme: BoardTheme = BoardTheme.CLASSIC_WALNUT,
    pieceStyle: PieceStyle = PieceStyle.CLASSIC_STAUNTON,
    onSquareClick: (Square) -> Unit,
    modifier: Modifier = Modifier
) {
    // Board theme colors
    val lightSquareColor = boardTheme.lightSquare
    val darkSquareColor = boardTheme.darkSquare
    val selectedSquareColor = boardTheme.selectedSquareColor
    val lastMoveColor = boardTheme.lastMoveColor
    val checkColor = boardTheme.checkColor
    val bestMoveHighlight = boardTheme.bestMoveHighlight
    val borderColor = boardTheme.borderColor

    val legalMovesFromSelected = remember(selectedSquare, boardState) {
        if (selectedSquare != null) {
            boardState.generateLegalMoves().filter { it.from == selectedSquare }
        } else {
            emptyList()
        }
    }

    val kingInCheckSquare = remember(boardState) {
        if (boardState.isCheck) {
            boardState.findKing(boardState.turn)
        } else null
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(borderColor)
            .padding(4.dp)
            .testTag("chess_board")
    ) {
        val squareSize = maxWidth / 8f

        Column(modifier = Modifier.fillMaxSize()) {
            val ranks = if (isFlipped) 0..7 else 7 downTo 0
            val files = if (isFlipped) 7 downTo 0 else 0..7

            for (r in ranks) {
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    for (f in files) {
                        val square = Square(f, r)
                        val piece = boardState.getPiece(square)
                        val isLight = square.isLightSquare

                        val isSelected = selectedSquare == square
                        val isLastMove = lastMove != null && (lastMove.from == square || lastMove.to == square)
                        val isBestMoveSquare = bestMove != null && (bestMove.from == square || bestMove.to == square)
                        val isKingInCheck = kingInCheckSquare == square
                        val legalTargetMove = legalMovesFromSelected.find { it.to == square }

                        // Base square color
                        var sqColor = if (isLight) lightSquareColor else darkSquareColor
                        if (isLastMove) sqColor = blendColors(sqColor, lastMoveColor)
                        if (isSelected) sqColor = blendColors(sqColor, selectedSquareColor)
                        if (isBestMoveSquare) sqColor = blendColors(sqColor, bestMoveHighlight)
                        if (isKingInCheck) sqColor = blendColors(sqColor, checkColor)

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(sqColor)
                                .then(
                                    if (isSelected) Modifier.border(2.dp, boardTheme.accentColor)
                                    else Modifier
                                )
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    onSquareClick(square)
                                }
                                .testTag("square_${square.algebraic}"),
                            contentAlignment = Alignment.Center
                        ) {
                            // Coordinate label on outer borders
                            val showRankLabel = if (isFlipped) f == 7 else f == 0
                            val showFileLabel = if (isFlipped) r == 7 else r == 0

                            if (showRankLabel) {
                                Text(
                                    text = "${r + 1}",
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(start = 2.dp, top = 1.dp),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isLight) darkSquareColor else lightSquareColor
                                )
                            }
                            if (showFileLabel) {
                                Text(
                                    text = "${'a' + f}",
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(end = 2.dp, bottom = 1.dp),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isLight) darkSquareColor else lightSquareColor
                                )
                            }

                            // Piece rendering with chosen style
                            if (piece != null) {
                                ChessPieceRenderer.DrawPiece(
                                    piece = piece,
                                    style = pieceStyle,
                                    modifier = Modifier
                                        .fillMaxSize(0.85f)
                                        .padding(1.dp)
                                )
                            }

                            // Legal destination indicator
                            if (legalTargetMove != null) {
                                if (piece == null && !legalTargetMove.isEnPassant) {
                                    // Empty square dot
                                    Box(
                                        modifier = Modifier
                                            .size(squareSize * 0.28f)
                                            .clip(CircleShape)
                                            .background(Color(0x55000000))
                                    )
                                } else {
                                    // Capture target ring
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(2.dp)
                                            .border(
                                                width = 3.dp,
                                                color = Color(0x77000000),
                                                shape = CircleShape
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun blendColors(base: Color, overlay: Color): Color {
    val alpha = overlay.alpha
    val invAlpha = 1f - alpha
    return Color(
        red = base.red * invAlpha + overlay.red * alpha,
        green = base.green * invAlpha + overlay.green * alpha,
        blue = base.blue * invAlpha + overlay.blue * alpha,
        alpha = 1f
    )
}
