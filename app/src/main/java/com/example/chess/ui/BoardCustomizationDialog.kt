package com.example.chess.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.model.BoardTheme
import com.example.chess.model.Piece
import com.example.chess.model.PieceColor
import com.example.chess.model.PieceStyle
import com.example.chess.model.PieceType

/**
 * Diálogo dedicado EXCLUSIVAMENTE a cambiar los colores y contraste del tablero.
 */
@Composable
fun BoardThemeDialog(
    currentTheme: BoardTheme,
    currentStyle: PieceStyle,
    onSelectTheme: (BoardTheme) -> Unit,
    onResetDefault: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.GridView,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        },
        title = {
            Text(
                text = "Cambiar Tablero",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Vista Previa en Tiempo Real",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))

                MiniBoardPreview(
                    theme = currentTheme,
                    style = currentStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Temas de Casillas Disponibles",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Selecciona la paleta que mejor se adapte a tu vista",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BoardTheme.entries.forEach { theme ->
                        val isSelected = theme == currentTheme
                        ThemeSelectionCard(
                            theme = theme,
                            isSelected = isSelected,
                            onSelect = { onSelectTheme(theme) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.testTag("apply_board_theme_button")
            ) {
                Text("Listo")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onResetDefault,
                modifier = Modifier.testTag("reset_board_theme_button")
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Por defecto")
            }
        },
        modifier = Modifier.testTag("board_theme_dialog")
    )
}

/**
 * Diálogo dedicado EXCLUSIVAMENTE a cambiar el set y la silueta de las piezas (Cburnett, Mérida, Alpha, Dubrovnik, etc.).
 */
@Composable
fun PieceStyleDialog(
    currentStyle: PieceStyle,
    currentTheme: BoardTheme,
    onSelectStyle: (PieceStyle) -> Unit,
    onResetDefault: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        },
        title = {
            Text(
                text = "Cambiar Formas de Piezas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Muestrario de Piezas (${currentStyle.displayName})",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Showcase of all 6 pieces in current style
                PieceFullShowcase(
                    style = currentStyle,
                    boardTheme = currentTheme,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Estilos y Formas Inspirados en Lichess",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Cada set tiene siluetas, coronas y caballos diferenciados",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PieceStyle.entries.forEach { style ->
                        val isSelected = style == currentStyle
                        PieceStyleCard(
                            style = style,
                            isSelected = isSelected,
                            onSelect = { onSelectStyle(style) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.testTag("apply_piece_style_button")
            ) {
                Text("Listo")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onResetDefault,
                modifier = Modifier.testTag("reset_piece_style_button")
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Por defecto")
            }
        },
        modifier = Modifier.testTag("piece_style_dialog")
    )
}

/**
 * Diálogo combinado mantenido para compatibilidad general.
 */
@Composable
fun BoardCustomizationDialog(
    currentTheme: BoardTheme,
    currentStyle: PieceStyle,
    onSelectTheme: (BoardTheme) -> Unit,
    onSelectStyle: (PieceStyle) -> Unit,
    onResetDefaults: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        },
        title = {
            Text(
                text = "Personalizar Tablero y Piezas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 4.dp)
            ) {
                // Live Interactive Mini-Preview
                Text(
                    text = "Vista Previa en Tiempo Real",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))

                MiniBoardPreview(
                    theme = currentTheme,
                    style = currentStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Section 1: Colores del Tablero
                Text(
                    text = "Colores del Tablero",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Elige la combinación de casillas y contraste visual",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BoardTheme.entries.forEach { theme ->
                        val isSelected = theme == currentTheme
                        ThemeSelectionCard(
                            theme = theme,
                            isSelected = isSelected,
                            onSelect = { onSelectTheme(theme) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Section 2: Estilos de Piezas
                Text(
                    text = "Formas de Piezas (Lichess)",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Elige la silueta y acabado de las piezas",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PieceStyle.entries.forEach { style ->
                        val isSelected = style == currentStyle
                        PieceStyleCard(
                            style = style,
                            isSelected = isSelected,
                            onSelect = { onSelectStyle(style) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.testTag("apply_customization_button")
            ) {
                Text("Listo")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onResetDefaults,
                modifier = Modifier.testTag("reset_defaults_customization_button")
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Por defecto")
            }
        },
        modifier = Modifier.testTag("board_customization_dialog")
    )
}

/**
 * Muestrario horizontal completo de piezas (Rey, Dama, Torre, Alfil, Caballo, Peón) en el estilo seleccionado.
 */
@Composable
private fun PieceFullShowcase(
    style: PieceStyle,
    boardTheme: BoardTheme,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = boardTheme.borderColor),
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .border(2.dp, boardTheme.accentColor.copy(alpha = 0.7f), RoundedCornerShape(12.dp))
            .padding(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val showcasePieces = listOf(
                Piece(PieceType.KING, PieceColor.WHITE) to true,
                Piece(PieceType.QUEEN, PieceColor.WHITE) to false,
                Piece(PieceType.KNIGHT, PieceColor.WHITE) to true,
                Piece(PieceType.BISHOP, PieceColor.BLACK) to false,
                Piece(PieceType.ROOK, PieceColor.BLACK) to true,
                Piece(PieceType.PAWN, PieceColor.BLACK) to false
            )

            showcasePieces.forEach { (piece, isLight) ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(if (isLight) boardTheme.lightSquare else boardTheme.darkSquare),
                    contentAlignment = Alignment.Center
                ) {
                    ChessPieceRenderer.DrawPiece(
                        piece = piece,
                        style = style,
                        modifier = Modifier
                            .fillMaxSize(0.85f)
                            .padding(2.dp)
                    )
                }
            }
        }
    }
}

/**
 * Vista previa interactiva de 4x4 casillas con piezas blancas y negras representativas.
 */
@Composable
private fun MiniBoardPreview(
    theme: BoardTheme,
    style: PieceStyle,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = theme.borderColor),
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .border(2.dp, theme.accentColor.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
            .padding(4.dp)
            .testTag("mini_board_preview")
    ) {
        val previewPieces = listOf(
            listOf(
                Piece(PieceType.ROOK, PieceColor.BLACK),
                Piece(PieceType.KNIGHT, PieceColor.BLACK),
                Piece(PieceType.BISHOP, PieceColor.BLACK),
                Piece(PieceType.KING, PieceColor.BLACK)
            ),
            listOf(
                Piece(PieceType.PAWN, PieceColor.BLACK),
                Piece(PieceType.PAWN, PieceColor.BLACK),
                null,
                Piece(PieceType.QUEEN, PieceColor.BLACK)
            ),
            listOf(
                null,
                Piece(PieceType.KNIGHT, PieceColor.WHITE),
                Piece(PieceType.PAWN, PieceColor.WHITE),
                null
            ),
            listOf(
                Piece(PieceType.ROOK, PieceColor.WHITE),
                null,
                Piece(PieceType.QUEEN, PieceColor.WHITE),
                Piece(PieceType.KING, PieceColor.WHITE)
            )
        )

        Column(modifier = Modifier.fillMaxSize()) {
            for (r in 0..3) {
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    for (f in 0..3) {
                        val isLight = (r + f) % 2 == 0
                        val piece = previewPieces[r][f]

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(if (isLight) theme.lightSquare else theme.darkSquare),
                            contentAlignment = Alignment.Center
                        ) {
                            if (piece != null) {
                                ChessPieceRenderer.DrawPiece(
                                    piece = piece,
                                    style = style,
                                    modifier = Modifier
                                        .fillMaxSize(0.85f)
                                        .padding(1.dp)
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Tarjeta de selección de tema de tablero con muestra de colores.
 */
@Composable
private fun ThemeSelectionCard(
    theme: BoardTheme,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            }
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .testTag("theme_card_${theme.id}")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            // Dual-Square color swatch
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(theme.borderColor)
                    .padding(3.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(theme.lightSquare)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(theme.darkSquare)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = theme.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = theme.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Seleccionado",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Tarjeta de selección de estilo de piezas con vista previa de piezas reales (Rey, Caballo, Alfil).
 */
@Composable
private fun PieceStyleCard(
    style: PieceStyle,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            }
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .testTag("piece_style_card_${style.id}")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            // Miniature piece showcase with 3 pieces: King, Knight, Bishop
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier
                    .size(width = 96.dp, height = 48.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // White King (shows cross or Dubrovnik orb)
                    ChessPieceRenderer.DrawPiece(
                        piece = Piece(PieceType.KING, PieceColor.WHITE),
                        style = style,
                        modifier = Modifier.size(28.dp)
                    )
                    // Black Knight (shows shape of horse and mouth/mane)
                    ChessPieceRenderer.DrawPiece(
                        piece = Piece(PieceType.KNIGHT, PieceColor.BLACK),
                        style = style,
                        modifier = Modifier.size(28.dp)
                    )
                    // White Bishop (shows mitre cut)
                    ChessPieceRenderer.DrawPiece(
                        piece = Piece(PieceType.BISHOP, PieceColor.WHITE),
                        style = style,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = style.displayName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = style.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Seleccionado",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
