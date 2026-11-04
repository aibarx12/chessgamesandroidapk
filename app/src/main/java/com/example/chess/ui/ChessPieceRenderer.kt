package com.example.chess.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.chess.model.Piece
import com.example.chess.model.PieceColor
import com.example.chess.model.PieceShapeFamily
import com.example.chess.model.PieceStyle
import com.example.chess.model.PieceType

/**
 * Renderizador de piezas vectoriales en Jetpack Compose, con formas y siluetas
 * diferenciadas inspiradas en los sets libres de Lichess (Cburnett, Mérida, Alpha,
 * Dubrovnik 1950, California, Pirouetti).
 */
object ChessPieceRenderer {

    @Composable
    fun DrawPiece(
        piece: Piece,
        style: PieceStyle = PieceStyle.CLASSIC_STAUNTON,
        modifier: Modifier = Modifier
    ) {
        Canvas(modifier = modifier.fillMaxSize()) {
            val isWhite = piece.color == PieceColor.WHITE
            val fill = if (isWhite) style.whiteFill else style.blackFill
            val stroke = if (isWhite) style.whiteStroke else style.blackStroke
            val detail = if (isWhite) style.whiteDetail else style.blackDetail
            val accent = if (isWhite) style.whiteAccent else style.blackAccent

            when (piece.type) {
                PieceType.PAWN -> drawPawn(fill, stroke, detail, accent, style)
                PieceType.KNIGHT -> drawKnight(fill, stroke, detail, accent, style)
                PieceType.BISHOP -> drawBishop(fill, stroke, detail, accent, style)
                PieceType.ROOK -> drawRook(fill, stroke, detail, accent, style)
                PieceType.QUEEN -> drawQueen(fill, stroke, detail, accent, style)
                PieceType.KING -> drawKing(fill, stroke, detail, accent, style)
            }
        }
    }

    // =========================================================================
    // PEÓN
    // =========================================================================
    private fun DrawScope.drawPawn(
        fill: Color,
        stroke: Color,
        detail: Color,
        accent: Color,
        style: PieceStyle
    ) {
        val w = size.width
        val h = size.height
        val strokeWidth = getStrokeWidth(w, style.shapeFamily)

        when (style.shapeFamily) {
            PieceShapeFamily.ALPHA -> {
                // Alpha: Geométrico, ángulos rectos y collar simple
                val basePath = Path().apply {
                    moveTo(w * 0.24f, h * 0.88f)
                    lineTo(w * 0.76f, h * 0.88f)
                    lineTo(w * 0.70f, h * 0.80f)
                    lineTo(w * 0.30f, h * 0.80f)
                    close()
                }
                drawPath(basePath, fill)
                drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Miter))

                val bodyPath = Path().apply {
                    moveTo(w * 0.34f, h * 0.80f)
                    lineTo(w * 0.44f, h * 0.46f)
                    lineTo(w * 0.56f, h * 0.46f)
                    lineTo(w * 0.66f, h * 0.80f)
                    close()
                }
                drawPath(bodyPath, fill)
                drawPath(bodyPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Miter))

                // Collar rectangular
                val collarPath = Path().apply {
                    moveTo(w * 0.36f, h * 0.46f)
                    lineTo(w * 0.64f, h * 0.46f)
                    lineTo(w * 0.60f, h * 0.40f)
                    lineTo(w * 0.40f, h * 0.40f)
                    close()
                }
                drawPath(collarPath, fill)
                drawPath(collarPath, stroke, style = Stroke(strokeWidth * 0.8f))

                val headRadius = w * 0.15f
                val center = Offset(w * 0.5f, h * 0.25f)
                drawCircle(fill, radius = headRadius, center = center)
                drawCircle(stroke, radius = headRadius, center = center, style = Stroke(strokeWidth))
            }
            PieceShapeFamily.DUBROVNIK -> {
                // Dubrovnik: Base ancha acampanada y cabeza robusta con doble collar
                val basePath = Path().apply {
                    moveTo(w * 0.18f, h * 0.90f)
                    lineTo(w * 0.82f, h * 0.90f)
                    cubicTo(w * 0.80f, h * 0.83f, w * 0.74f, h * 0.81f, w * 0.70f, h * 0.79f)
                    lineTo(w * 0.30f, h * 0.79f)
                    cubicTo(w * 0.26f, h * 0.81f, w * 0.20f, h * 0.83f, w * 0.18f, h * 0.90f)
                    close()
                }
                drawPath(basePath, fill)
                drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                val bodyPath = Path().apply {
                    moveTo(w * 0.31f, h * 0.79f)
                    cubicTo(w * 0.37f, h * 0.62f, w * 0.40f, h * 0.50f, w * 0.42f, h * 0.42f)
                    lineTo(w * 0.58f, h * 0.42f)
                    cubicTo(w * 0.60f, h * 0.50f, w * 0.63f, h * 0.62f, w * 0.69f, h * 0.79f)
                    close()
                }
                drawPath(bodyPath, fill)
                drawPath(bodyPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Doble collar característico Dubrovnik
                drawLine(stroke, start = Offset(w * 0.35f, h * 0.42f), end = Offset(w * 0.65f, h * 0.42f), strokeWidth = strokeWidth * 0.9f)
                drawLine(stroke, start = Offset(w * 0.38f, h * 0.38f), end = Offset(w * 0.62f, h * 0.38f), strokeWidth = strokeWidth * 0.8f)

                val headRadius = w * 0.17f
                val center = Offset(w * 0.5f, h * 0.24f)
                drawCircle(fill, radius = headRadius, center = center)
                drawCircle(stroke, radius = headRadius, center = center, style = Stroke(strokeWidth))
                drawCircle(accent, radius = headRadius * 0.3f, center = Offset(center.x - headRadius * 0.3f, center.y - headRadius * 0.3f))
            }
            PieceShapeFamily.MERIDA -> {
                // Mérida: Silueta esbelta con collar abombado
                val basePath = Path().apply {
                    moveTo(w * 0.22f, h * 0.88f)
                    lineTo(w * 0.78f, h * 0.88f)
                    lineTo(w * 0.71f, h * 0.80f)
                    lineTo(w * 0.29f, h * 0.80f)
                    close()
                }
                drawPath(basePath, fill)
                drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                val bodyPath = Path().apply {
                    moveTo(w * 0.32f, h * 0.80f)
                    cubicTo(w * 0.39f, h * 0.63f, w * 0.41f, h * 0.51f, w * 0.43f, h * 0.43f)
                    lineTo(w * 0.57f, h * 0.43f)
                    cubicTo(w * 0.59f, h * 0.51f, w * 0.61f, h * 0.63f, w * 0.68f, h * 0.80f)
                    close()
                }
                drawPath(bodyPath, fill)
                drawPath(bodyPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                drawOval(
                    color = fill,
                    topLeft = Offset(w * 0.36f, h * 0.39f),
                    size = Size(w * 0.28f, h * 0.08f)
                )
                drawOval(
                    color = stroke,
                    topLeft = Offset(w * 0.36f, h * 0.39f),
                    size = Size(w * 0.28f, h * 0.08f),
                    style = Stroke(strokeWidth * 0.8f)
                )

                val headRadius = w * 0.16f
                val center = Offset(w * 0.5f, h * 0.25f)
                drawCircle(fill, radius = headRadius, center = center)
                drawCircle(stroke, radius = headRadius, center = center, style = Stroke(strokeWidth))
                drawCircle(detail, radius = headRadius * 0.32f, center = Offset(center.x - headRadius * 0.32f, center.y - headRadius * 0.32f))
            }
            else -> {
                // Cburnett / California / Pirouetti
                val basePath = Path().apply {
                    moveTo(w * 0.22f, h * 0.88f)
                    lineTo(w * 0.78f, h * 0.88f)
                    lineTo(w * 0.72f, h * 0.80f)
                    lineTo(w * 0.28f, h * 0.80f)
                    close()
                }
                drawPath(basePath, fill)
                drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                val bodyPath = Path().apply {
                    moveTo(w * 0.32f, h * 0.80f)
                    cubicTo(w * 0.38f, h * 0.65f, w * 0.40f, h * 0.52f, w * 0.43f, h * 0.44f)
                    lineTo(w * 0.57f, h * 0.44f)
                    cubicTo(w * 0.60f, h * 0.52f, w * 0.62f, h * 0.65f, w * 0.68f, h * 0.80f)
                    close()
                }
                drawPath(bodyPath, fill)
                drawPath(bodyPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                val collarPath = Path().apply {
                    moveTo(w * 0.36f, h * 0.44f)
                    lineTo(w * 0.64f, h * 0.44f)
                    lineTo(w * 0.60f, h * 0.40f)
                    lineTo(w * 0.40f, h * 0.40f)
                    close()
                }
                drawPath(collarPath, fill)
                drawPath(collarPath, stroke, style = Stroke(strokeWidth * 0.8f))

                val headRadius = w * 0.16f
                val center = Offset(w * 0.5f, h * 0.26f)
                drawCircle(fill, radius = headRadius, center = center)
                drawCircle(stroke, radius = headRadius, center = center, style = Stroke(strokeWidth))
                drawCircle(detail, radius = headRadius * 0.35f, center = Offset(center.x - headRadius * 0.3f, center.y - headRadius * 0.3f))
            }
        }
    }

    // =========================================================================
    // CABALLO (La pieza más representativa y diferenciada de cada set)
    // =========================================================================
    private fun DrawScope.drawKnight(
        fill: Color,
        stroke: Color,
        detail: Color,
        accent: Color,
        style: PieceStyle
    ) {
        val w = size.width
        val h = size.height
        val strokeWidth = getStrokeWidth(w, style.shapeFamily)

        // Base estándar de apoyo
        val basePath = Path().apply {
            moveTo(w * 0.22f, h * 0.88f)
            lineTo(w * 0.78f, h * 0.88f)
            lineTo(w * 0.73f, h * 0.80f)
            lineTo(w * 0.27f, h * 0.80f)
            close()
        }
        drawPath(basePath, fill)
        drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        when (style.shapeFamily) {
            PieceShapeFamily.CBURNETT -> {
                // Cburnett (Lichess Clásico): Crin dentada con 3 picos característicos y hocico curvo
                val horsePath = Path().apply {
                    moveTo(w * 0.28f, h * 0.80f)
                    cubicTo(w * 0.25f, h * 0.66f, w * 0.20f, h * 0.52f, w * 0.25f, h * 0.42f)
                    lineTo(w * 0.33f, h * 0.46f) // hocico
                    cubicTo(w * 0.36f, h * 0.38f, w * 0.40f, h * 0.30f, w * 0.40f, h * 0.18f)
                    lineTo(w * 0.46f, h * 0.12f) // oreja
                    lineTo(w * 0.50f, h * 0.20f)
                    // 3 muescas dentadas en la crin (identidad de Cburnett)
                    lineTo(w * 0.58f, h * 0.23f)
                    lineTo(w * 0.52f, h * 0.28f)
                    lineTo(w * 0.65f, h * 0.33f)
                    lineTo(w * 0.58f, h * 0.39f)
                    lineTo(w * 0.72f, h * 0.46f)
                    cubicTo(w * 0.74f, h * 0.58f, w * 0.72f, h * 0.70f, w * 0.72f, h * 0.80f)
                    close()
                }
                drawPath(horsePath, fill)
                drawPath(horsePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Ojo ovalado y fosa nasal
                drawCircle(stroke, radius = w * 0.030f, center = Offset(w * 0.36f, h * 0.30f))
                drawLine(detail, start = Offset(w * 0.27f, h * 0.43f), end = Offset(w * 0.30f, h * 0.45f), strokeWidth = strokeWidth * 0.8f)
            }

            PieceShapeFamily.MERIDA -> {
                // Mérida: Boca abierta expresiva, cuello largo en "S" y oreja curvada
                val horsePath = Path().apply {
                    moveTo(w * 0.28f, h * 0.80f)
                    cubicTo(w * 0.25f, h * 0.68f, w * 0.18f, h * 0.56f, w * 0.22f, h * 0.46f)
                    // Mandíbula inferior y boca abierta característica de Mérida
                    lineTo(w * 0.29f, h * 0.48f)
                    lineTo(w * 0.25f, h * 0.44f) // hendidura de la boca
                    lineTo(w * 0.24f, h * 0.38f) // morro superior
                    cubicTo(w * 0.32f, h * 0.34f, w * 0.38f, h * 0.26f, w * 0.40f, h * 0.15f)
                    // Oreja curvada hacia adelante
                    cubicTo(w * 0.44f, h * 0.12f, w * 0.47f, h * 0.16f, w * 0.48f, h * 0.23f)
                    // Crin fluida continua
                    cubicTo(w * 0.62f, h * 0.25f, w * 0.74f, h * 0.37f, w * 0.76f, h * 0.55f)
                    cubicTo(w * 0.76f, h * 0.68f, w * 0.74f, h * 0.75f, w * 0.72f, h * 0.80f)
                    close()
                }
                drawPath(horsePath, fill)
                drawPath(horsePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Ojo expresivo con iris
                drawCircle(stroke, radius = w * 0.035f, center = Offset(w * 0.36f, h * 0.28f))
                drawCircle(fill, radius = w * 0.015f, center = Offset(w * 0.35f, h * 0.27f))

                // Línea de la mandíbula y crin en relieve
                drawLine(detail, start = Offset(w * 0.28f, h * 0.46f), end = Offset(w * 0.38f, h * 0.40f), strokeWidth = strokeWidth * 0.8f)
                drawLine(detail, start = Offset(w * 0.50f, h * 0.28f), end = Offset(w * 0.65f, h * 0.40f), strokeWidth = strokeWidth * 0.8f)
                drawLine(detail, start = Offset(w * 0.54f, h * 0.40f), end = Offset(w * 0.68f, h * 0.52f), strokeWidth = strokeWidth * 0.8f)
            }

            PieceShapeFamily.ALPHA -> {
                // Alpha: Geométrico puro, ángulos afilados, hocico recto y orejas triangulares
                val horsePath = Path().apply {
                    moveTo(w * 0.28f, h * 0.80f)
                    lineTo(w * 0.22f, h * 0.52f)
                    lineTo(w * 0.18f, h * 0.45f) // hocico puntiagudo
                    lineTo(w * 0.30f, h * 0.42f)
                    lineTo(w * 0.40f, h * 0.16f) // oreja triangular
                    lineTo(w * 0.47f, h * 0.22f)
                    lineTo(w * 0.60f, h * 0.24f)
                    lineTo(w * 0.54f, h * 0.34f)
                    lineTo(w * 0.68f, h * 0.38f)
                    lineTo(w * 0.62f, h * 0.48f)
                    lineTo(w * 0.74f, h * 0.56f)
                    lineTo(w * 0.72f, h * 0.80f)
                    close()
                }
                drawPath(horsePath, fill)
                drawPath(horsePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Miter))

                // Ojo geométrico triangular
                val eyePath = Path().apply {
                    moveTo(w * 0.34f, h * 0.32f)
                    lineTo(w * 0.40f, h * 0.30f)
                    lineTo(w * 0.37f, h * 0.36f)
                    close()
                }
                drawPath(eyePath, stroke)
            }

            PieceShapeFamily.DUBROVNIK -> {
                // Dubrovnik: Crin peinada lisa arqueada, pecho robusto, hocico curvado y orejas redondeadas
                val horsePath = Path().apply {
                    moveTo(w * 0.28f, h * 0.80f)
                    cubicTo(w * 0.26f, h * 0.66f, w * 0.21f, h * 0.54f, w * 0.24f, h * 0.44f)
                    cubicTo(w * 0.28f, h * 0.44f, w * 0.34f, h * 0.48f, w * 0.38f, h * 0.44f)
                    cubicTo(w * 0.40f, h * 0.36f, w * 0.40f, h * 0.26f, w * 0.42f, h * 0.16f)
                    // Oreja redondeada
                    cubicTo(w * 0.45f, h * 0.14f, w * 0.48f, h * 0.16f, w * 0.49f, h * 0.22f)
                    // Lomo continuo peinado (la famosa cresta lisa de Dubrovnik)
                    cubicTo(w * 0.65f, h * 0.22f, w * 0.76f, h * 0.34f, w * 0.77f, h * 0.54f)
                    cubicTo(w * 0.76f, h * 0.68f, w * 0.74f, h * 0.76f, w * 0.72f, h * 0.80f)
                    close()
                }
                drawPath(horsePath, fill)
                drawPath(horsePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Ojo con aro decorativo
                drawCircle(accent, radius = w * 0.040f, center = Offset(w * 0.38f, h * 0.30f))
                drawCircle(stroke, radius = w * 0.040f, center = Offset(w * 0.38f, h * 0.30f), style = Stroke(strokeWidth * 0.8f))
                drawCircle(fill, radius = w * 0.015f, center = Offset(w * 0.37f, h * 0.29f))

                // Fosa nasal redonda
                drawCircle(stroke, radius = w * 0.020f, center = Offset(w * 0.28f, h * 0.45f))
            }

            PieceShapeFamily.CALIFORNIA -> {
                // California: Staunton robusto con pecho amplio y crin acanalada
                val horsePath = Path().apply {
                    moveTo(w * 0.27f, h * 0.80f)
                    cubicTo(w * 0.24f, h * 0.66f, w * 0.18f, h * 0.52f, w * 0.24f, h * 0.40f)
                    lineTo(w * 0.32f, h * 0.44f)
                    cubicTo(w * 0.36f, h * 0.38f, w * 0.38f, h * 0.28f, w * 0.40f, h * 0.15f)
                    lineTo(w * 0.46f, h * 0.12f)
                    lineTo(w * 0.50f, h * 0.20f)
                    cubicTo(w * 0.65f, h * 0.22f, w * 0.76f, h * 0.34f, w * 0.77f, h * 0.52f)
                    cubicTo(w * 0.77f, h * 0.68f, w * 0.74f, h * 0.75f, w * 0.73f, h * 0.80f)
                    close()
                }
                drawPath(horsePath, fill)
                drawPath(horsePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                drawCircle(stroke, radius = w * 0.032f, center = Offset(w * 0.37f, h * 0.29f))
                // Canales paralelos en la crin
                drawLine(detail, start = Offset(w * 0.50f, h * 0.25f), end = Offset(w * 0.62f, h * 0.35f), strokeWidth = strokeWidth * 0.9f)
                drawLine(detail, start = Offset(w * 0.55f, h * 0.36f), end = Offset(w * 0.67f, h * 0.46f), strokeWidth = strokeWidth * 0.9f)
                drawLine(detail, start = Offset(w * 0.60f, h * 0.48f), end = Offset(w * 0.70f, h * 0.58f), strokeWidth = strokeWidth * 0.9f)
            }

            PieceShapeFamily.PIROUETTI -> {
                // Pirouetti: Minimalismo redondeado fluido y orgánico
                val horsePath = Path().apply {
                    moveTo(w * 0.28f, h * 0.80f)
                    cubicTo(w * 0.22f, h * 0.62f, w * 0.20f, h * 0.48f, w * 0.26f, h * 0.38f)
                    cubicTo(w * 0.32f, h * 0.30f, w * 0.38f, h * 0.18f, w * 0.44f, h * 0.16f)
                    cubicTo(w * 0.48f, h * 0.16f, w * 0.50f, h * 0.22f, w * 0.54f, h * 0.26f)
                    cubicTo(w * 0.68f, h * 0.30f, w * 0.75f, h * 0.45f, w * 0.74f, h * 0.62f)
                    cubicTo(w * 0.74f, h * 0.72f, w * 0.72f, h * 0.78f, w * 0.72f, h * 0.80f)
                    close()
                }
                drawPath(horsePath, fill)
                drawPath(horsePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                drawCircle(accent, radius = w * 0.035f, center = Offset(w * 0.38f, h * 0.32f))
            }
        }
    }

    // =========================================================================
    // ALFIL
    // =========================================================================
    private fun DrawScope.drawBishop(
        fill: Color,
        stroke: Color,
        detail: Color,
        accent: Color,
        style: PieceStyle
    ) {
        val w = size.width
        val h = size.height
        val strokeWidth = getStrokeWidth(w, style.shapeFamily)

        // Base
        val basePath = Path().apply {
            moveTo(w * 0.22f, h * 0.88f)
            lineTo(w * 0.78f, h * 0.88f)
            lineTo(w * 0.72f, h * 0.80f)
            lineTo(w * 0.28f, h * 0.80f)
            close()
        }
        drawPath(basePath, fill)
        drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        when (style.shapeFamily) {
            PieceShapeFamily.DUBROVNIK -> {
                // Dubrovnik: Mitra redondeada con BOLA SUPERIOR DE COLOR CONTRASTADO sin cruz
                val mitrePath = Path().apply {
                    moveTo(w * 0.31f, h * 0.80f)
                    cubicTo(w * 0.26f, h * 0.62f, w * 0.28f, h * 0.42f, w * 0.44f, h * 0.22f)
                    lineTo(w * 0.50f, h * 0.17f)
                    lineTo(w * 0.56f, h * 0.22f)
                    cubicTo(w * 0.72f, h * 0.42f, w * 0.74f, h * 0.62f, w * 0.69f, h * 0.80f)
                    close()
                }
                drawPath(mitrePath, fill)
                drawPath(mitrePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Famosa bola en la cúspide de Dubrovnik con color acento
                drawCircle(accent, radius = w * 0.065f, center = Offset(w * 0.50f, h * 0.13f))
                drawCircle(stroke, radius = w * 0.065f, center = Offset(w * 0.50f, h * 0.13f), style = Stroke(strokeWidth * 0.8f))

                // Corte diagonal curvo
                val slitPath = Path().apply {
                    moveTo(w * 0.52f, h * 0.28f)
                    cubicTo(w * 0.46f, h * 0.36f, w * 0.38f, h * 0.44f, w * 0.36f, h * 0.50f)
                }
                drawPath(slitPath, stroke, style = Stroke(strokeWidth * 1.1f, cap = StrokeCap.Round))
            }

            PieceShapeFamily.ALPHA -> {
                // Alpha: Ángulos rectos y corte en chevron
                val mitrePath = Path().apply {
                    moveTo(w * 0.32f, h * 0.80f)
                    lineTo(w * 0.32f, h * 0.45f)
                    lineTo(w * 0.50f, h * 0.17f)
                    lineTo(w * 0.68f, h * 0.45f)
                    lineTo(w * 0.68f, h * 0.80f)
                    close()
                }
                drawPath(mitrePath, fill)
                drawPath(mitrePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Miter))

                // Diamante superior
                val topGem = Path().apply {
                    moveTo(w * 0.50f, h * 0.10f)
                    lineTo(w * 0.54f, h * 0.14f)
                    lineTo(w * 0.50f, h * 0.18f)
                    lineTo(w * 0.46f, h * 0.14f)
                    close()
                }
                drawPath(topGem, accent)
                drawPath(topGem, stroke, style = Stroke(strokeWidth * 0.8f))

                // Corte chevron
                val slit = Path().apply {
                    moveTo(w * 0.48f, h * 0.28f)
                    lineTo(w * 0.40f, h * 0.38f)
                    lineTo(w * 0.44f, h * 0.48f)
                }
                drawPath(slit, stroke, style = Stroke(strokeWidth * 1.1f))
            }

            else -> {
                // Cburnett / Mérida / California
                val mitrePath = Path().apply {
                    moveTo(w * 0.32f, h * 0.80f)
                    cubicTo(w * 0.28f, h * 0.60f, w * 0.30f, h * 0.40f, w * 0.45f, h * 0.22f)
                    lineTo(w * 0.50f, h * 0.16f)
                    lineTo(w * 0.55f, h * 0.22f)
                    cubicTo(w * 0.70f, h * 0.40f, w * 0.72f, h * 0.60f, w * 0.68f, h * 0.80f)
                    close()
                }
                drawPath(mitrePath, fill)
                drawPath(mitrePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                drawCircle(accent, radius = w * 0.05f, center = Offset(w * 0.50f, h * 0.13f))
                drawCircle(stroke, radius = w * 0.05f, center = Offset(w * 0.50f, h * 0.13f), style = Stroke(strokeWidth * 0.8f))

                // Corte diagonal
                drawLine(detail, start = Offset(w * 0.48f, h * 0.30f), end = Offset(w * 0.38f, h * 0.48f), strokeWidth = strokeWidth)

                // Cruz emblemática en el centro del alfil
                drawLine(detail, start = Offset(w * 0.50f, h * 0.50f), end = Offset(w * 0.50f, h * 0.66f), strokeWidth = strokeWidth * 0.8f)
                drawLine(detail, start = Offset(w * 0.43f, h * 0.56f), end = Offset(w * 0.57f, h * 0.56f), strokeWidth = strokeWidth * 0.8f)
            }
        }
    }

    // =========================================================================
    // TORRE
    // =========================================================================
    private fun DrawScope.drawRook(
        fill: Color,
        stroke: Color,
        detail: Color,
        accent: Color,
        style: PieceStyle
    ) {
        val w = size.width
        val h = size.height
        val strokeWidth = getStrokeWidth(w, style.shapeFamily)

        // Base
        val basePath = Path().apply {
            moveTo(w * 0.20f, h * 0.88f)
            lineTo(w * 0.80f, h * 0.88f)
            lineTo(w * 0.74f, h * 0.78f)
            lineTo(w * 0.26f, h * 0.78f)
            close()
        }
        drawPath(basePath, fill)
        drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        when (style.shapeFamily) {
            PieceShapeFamily.DUBROVNIK -> {
                // Dubrovnik: Torre pesada, muros acampanados con almenas anchas
                val towerPath = Path().apply {
                    moveTo(w * 0.30f, h * 0.78f)
                    lineTo(w * 0.32f, h * 0.44f)
                    lineTo(w * 0.68f, h * 0.44f)
                    lineTo(w * 0.70f, h * 0.78f)
                    close()
                }
                drawPath(towerPath, fill)
                drawPath(towerPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Almenas anchas y macizas
                val castlePath = Path().apply {
                    moveTo(w * 0.22f, h * 0.44f)
                    lineTo(w * 0.22f, h * 0.24f)
                    lineTo(w * 0.33f, h * 0.24f)
                    lineTo(w * 0.33f, h * 0.32f)
                    lineTo(w * 0.44f, h * 0.32f)
                    lineTo(w * 0.44f, h * 0.24f)
                    lineTo(w * 0.56f, h * 0.24f)
                    lineTo(w * 0.56f, h * 0.32f)
                    lineTo(w * 0.67f, h * 0.32f)
                    lineTo(w * 0.67f, h * 0.24f)
                    lineTo(w * 0.78f, h * 0.24f)
                    lineTo(w * 0.78f, h * 0.44f)
                    close()
                }
                drawPath(castlePath, fill)
                drawPath(castlePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Banda horizontal
                drawLine(accent, start = Offset(w * 0.24f, h * 0.44f), end = Offset(w * 0.76f, h * 0.44f), strokeWidth = strokeWidth * 0.8f)
            }

            PieceShapeFamily.ALPHA -> {
                // Alpha: Minimalismo geométrico con saetera vertical estrecha
                val towerPath = Path().apply {
                    moveTo(w * 0.33f, h * 0.78f)
                    lineTo(w * 0.33f, h * 0.42f)
                    lineTo(w * 0.67f, h * 0.42f)
                    lineTo(w * 0.67f, h * 0.78f)
                    close()
                }
                drawPath(towerPath, fill)
                drawPath(towerPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Miter))

                val castlePath = Path().apply {
                    moveTo(w * 0.26f, h * 0.42f)
                    lineTo(w * 0.26f, h * 0.22f)
                    lineTo(w * 0.36f, h * 0.22f)
                    lineTo(w * 0.36f, h * 0.30f)
                    lineTo(w * 0.46f, h * 0.30f)
                    lineTo(w * 0.46f, h * 0.22f)
                    lineTo(w * 0.54f, h * 0.22f)
                    lineTo(w * 0.54f, h * 0.30f)
                    lineTo(w * 0.64f, h * 0.30f)
                    lineTo(w * 0.64f, h * 0.22f)
                    lineTo(w * 0.74f, h * 0.22f)
                    lineTo(w * 0.74f, h * 0.42f)
                    close()
                }
                drawPath(castlePath, fill)
                drawPath(castlePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Miter))

                // Tronera saetera central
                drawLine(stroke, start = Offset(w * 0.50f, h * 0.50f), end = Offset(w * 0.50f, h * 0.68f), strokeWidth = strokeWidth * 1.2f)
            }

            else -> {
                // Cburnett / Mérida / California
                val towerPath = Path().apply {
                    moveTo(w * 0.32f, h * 0.78f)
                    lineTo(w * 0.34f, h * 0.42f)
                    lineTo(w * 0.66f, h * 0.42f)
                    lineTo(w * 0.68f, h * 0.78f)
                    close()
                }
                drawPath(towerPath, fill)
                drawPath(towerPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                val castlePath = Path().apply {
                    moveTo(w * 0.24f, h * 0.42f)
                    lineTo(w * 0.24f, h * 0.22f)
                    lineTo(w * 0.34f, h * 0.22f)
                    lineTo(w * 0.34f, h * 0.30f)
                    lineTo(w * 0.44f, h * 0.30f)
                    lineTo(w * 0.44f, h * 0.22f)
                    lineTo(w * 0.56f, h * 0.22f)
                    lineTo(w * 0.56f, h * 0.30f)
                    lineTo(w * 0.66f, h * 0.30f)
                    lineTo(w * 0.66f, h * 0.22f)
                    lineTo(w * 0.76f, h * 0.22f)
                    lineTo(w * 0.76f, h * 0.42f)
                    close()
                }
                drawPath(castlePath, fill)
                drawPath(castlePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                drawLine(detail, start = Offset(w * 0.50f, h * 0.50f), end = Offset(w * 0.50f, h * 0.66f), strokeWidth = strokeWidth * 0.9f, cap = StrokeCap.Round)
            }
        }
    }

    // =========================================================================
    // DAMA
    // =========================================================================
    private fun DrawScope.drawQueen(
        fill: Color,
        stroke: Color,
        detail: Color,
        accent: Color,
        style: PieceStyle
    ) {
        val w = size.width
        val h = size.height
        val strokeWidth = getStrokeWidth(w, style.shapeFamily)

        // Base
        val basePath = Path().apply {
            moveTo(w * 0.20f, h * 0.88f)
            lineTo(w * 0.80f, h * 0.88f)
            lineTo(w * 0.74f, h * 0.80f)
            lineTo(w * 0.26f, h * 0.80f)
            close()
        }
        drawPath(basePath, fill)
        drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        // Cintura
        val waistPath = Path().apply {
            moveTo(w * 0.30f, h * 0.80f)
            cubicTo(w * 0.38f, h * 0.66f, w * 0.38f, h * 0.54f, w * 0.34f, h * 0.48f)
            lineTo(w * 0.66f, h * 0.48f)
            cubicTo(w * 0.62f, h * 0.54f, w * 0.62f, h * 0.66f, w * 0.70f, h * 0.80f)
            close()
        }
        drawPath(waistPath, fill)
        drawPath(waistPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        when (style.shapeFamily) {
            PieceShapeFamily.ALPHA -> {
                // Alpha: 3 picos geométricos marcados con diamantes
                val coronetPath = Path().apply {
                    moveTo(w * 0.32f, h * 0.48f)
                    lineTo(w * 0.22f, h * 0.24f) // pico izq
                    lineTo(w * 0.38f, h * 0.36f)
                    lineTo(w * 0.50f, h * 0.16f) // pico central
                    lineTo(w * 0.62f, h * 0.36f)
                    lineTo(w * 0.78f, h * 0.24f) // pico der
                    lineTo(w * 0.68f, h * 0.48f)
                    close()
                }
                drawPath(coronetPath, fill)
                drawPath(coronetPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Miter))

                val tips = listOf(
                    Offset(w * 0.22f, h * 0.24f),
                    Offset(w * 0.50f, h * 0.16f),
                    Offset(w * 0.78f, h * 0.24f)
                )
                for (pt in tips) {
                    drawCircle(accent, radius = w * 0.040f, center = pt)
                    drawCircle(stroke, radius = w * 0.040f, center = pt, style = Stroke(strokeWidth * 0.8f))
                }
            }

            PieceShapeFamily.DUBROVNIK -> {
                // Dubrovnik: Corona acampanada con esfera central dominante
                val coronetPath = Path().apply {
                    moveTo(w * 0.28f, h * 0.48f)
                    cubicTo(w * 0.20f, h * 0.34f, w * 0.24f, h * 0.25f, w * 0.26f, h * 0.24f)
                    lineTo(w * 0.36f, h * 0.36f)
                    lineTo(w * 0.50f, h * 0.20f)
                    lineTo(w * 0.64f, h * 0.36f)
                    cubicTo(w * 0.76f, h * 0.25f, w * 0.80f, h * 0.34f, w * 0.72f, h * 0.48f)
                    close()
                }
                drawPath(coronetPath, fill)
                drawPath(coronetPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                // Esfera central Dubrovnik
                drawCircle(accent, radius = w * 0.055f, center = Offset(w * 0.50f, h * 0.16f))
                drawCircle(stroke, radius = w * 0.055f, center = Offset(w * 0.50f, h * 0.16f), style = Stroke(strokeWidth * 0.8f))
            }

            else -> {
                // Cburnett / Mérida / California: 5 agujas coronadas con perlas
                val coronetPath = Path().apply {
                    moveTo(w * 0.28f, h * 0.48f)
                    lineTo(w * 0.20f, h * 0.26f)
                    lineTo(w * 0.34f, h * 0.36f)
                    lineTo(w * 0.36f, h * 0.20f)
                    lineTo(w * 0.50f, h * 0.34f)
                    lineTo(w * 0.50f, h * 0.16f)
                    lineTo(w * 0.50f, h * 0.34f)
                    lineTo(w * 0.64f, h * 0.20f)
                    lineTo(w * 0.66f, h * 0.36f)
                    lineTo(w * 0.80f, h * 0.26f)
                    lineTo(w * 0.72f, h * 0.48f)
                    close()
                }
                drawPath(coronetPath, fill)
                drawPath(coronetPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

                val spirePoints = listOf(
                    Offset(w * 0.20f, h * 0.24f),
                    Offset(w * 0.36f, h * 0.18f),
                    Offset(w * 0.50f, h * 0.14f),
                    Offset(w * 0.64f, h * 0.18f),
                    Offset(w * 0.80f, h * 0.24f)
                )
                for (pt in spirePoints) {
                    drawCircle(accent, radius = w * 0.038f, center = pt)
                    drawCircle(stroke, radius = w * 0.038f, center = pt, style = Stroke(strokeWidth * 0.7f))
                }
            }
        }

        // Cinturón decorativo de la realeza
        drawLine(detail, start = Offset(w * 0.34f, h * 0.54f), end = Offset(w * 0.66f, h * 0.54f), strokeWidth = strokeWidth * 0.8f)
    }

    // =========================================================================
    // REY
    // =========================================================================
    private fun DrawScope.drawKing(
        fill: Color,
        stroke: Color,
        detail: Color,
        accent: Color,
        style: PieceStyle
    ) {
        val w = size.width
        val h = size.height
        val strokeWidth = getStrokeWidth(w, style.shapeFamily)

        // Base
        val basePath = Path().apply {
            moveTo(w * 0.20f, h * 0.88f)
            lineTo(w * 0.80f, h * 0.88f)
            lineTo(w * 0.74f, h * 0.80f)
            lineTo(w * 0.26f, h * 0.80f)
            close()
        }
        drawPath(basePath, fill)
        drawPath(basePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        // Manto / Cuerpo
        val robePath = Path().apply {
            moveTo(w * 0.30f, h * 0.80f)
            cubicTo(w * 0.36f, h * 0.64f, w * 0.36f, h * 0.50f, w * 0.32f, h * 0.44f)
            lineTo(w * 0.68f, h * 0.44f)
            cubicTo(w * 0.64f, h * 0.50f, w * 0.64f, h * 0.64f, w * 0.70f, h * 0.80f)
            close()
        }
        drawPath(robePath, fill)
        drawPath(robePath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        // Corona Real
        val crownPath = Path().apply {
            moveTo(w * 0.28f, h * 0.44f)
            cubicTo(w * 0.22f, h * 0.30f, w * 0.32f, h * 0.24f, w * 0.50f, h * 0.22f)
            cubicTo(w * 0.68f, h * 0.24f, w * 0.78f, h * 0.30f, w * 0.72f, h * 0.44f)
            close()
        }
        drawPath(crownPath, fill)
        drawPath(crownPath, stroke, style = Stroke(strokeWidth, join = StrokeJoin.Round))

        if (style.shapeFamily == PieceShapeFamily.DUBROVNIK) {
            // ¡DUBROVNIK 1950! LA MARCA HISTÓRICA: ¡SIN CRUZ!
            // En su lugar, lleva una gran esfera finial imperial pulida
            val orbRadius = w * 0.080f
            val orbCenter = Offset(w * 0.50f, h * 0.14f)
            drawCircle(accent, radius = orbRadius, center = orbCenter)
            drawCircle(stroke, radius = orbRadius, center = orbCenter, style = Stroke(strokeWidth))
            // Ribete del orbe
            drawCircle(fill, radius = orbRadius * 0.35f, center = Offset(orbCenter.x - orbRadius * 0.25f, orbCenter.y - orbRadius * 0.25f))
        } else {
            // Cburnett / Mérida / Alpha / California: Cruz cristiana Staunton tradicional
            val crossH = h * 0.16f
            val crossY = h * 0.12f
            val crossColor = if (style.shapeFamily == PieceShapeFamily.ALPHA) stroke else accent

            drawLine(crossColor, start = Offset(w * 0.50f, crossY - crossH * 0.5f), end = Offset(w * 0.50f, crossY + crossH * 0.5f), strokeWidth = strokeWidth * 1.2f, cap = StrokeCap.Square)
            drawLine(crossColor, start = Offset(w * 0.42f, crossY - crossH * 0.1f), end = Offset(w * 0.58f, crossY - crossH * 0.1f), strokeWidth = strokeWidth * 1.2f, cap = StrokeCap.Square)
        }

        // Arco embellecedor en la corona
        drawArc(
            color = detail,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.38f, h * 0.28f),
            size = Size(w * 0.24f, h * 0.14f),
            style = Stroke(strokeWidth * 0.8f)
        )
    }

    private fun getStrokeWidth(width: Float, shape: PieceShapeFamily): Float {
        return when (shape) {
            PieceShapeFamily.ALPHA -> width * 0.052f
            PieceShapeFamily.DUBROVNIK -> width * 0.048f
            PieceShapeFamily.MERIDA -> width * 0.044f
            PieceShapeFamily.PIROUETTI -> width * 0.042f
            else -> width * 0.045f
        }
    }
}
