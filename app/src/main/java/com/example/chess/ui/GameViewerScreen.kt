package com.example.chess.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.LastPage
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.data.AnnotatedMove
import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.portraitRes
import com.example.chess.model.PieceColor
import com.example.ui.theme.AmberStar
import com.example.ui.theme.EmeraldWin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameViewerScreen(
    viewModel: ChessViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = uiState.activeGame ?: return
    val isUnsolvedChallenge = uiState.isChallengeMode &&
        (uiState.challengeStatus == ChallengeStatus.PENDING ||
            uiState.challengeStatus == ChallengeStatus.WRONG_TRY)

    // Handle Android system back press to return to game list
    BackHandler {
        viewModel.navigateTo(Screen.GAME_LIST)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = game.portraitRes),
                            contentDescription = game.whitePlayer,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, GoldPrimary, CircleShape)
                                .background(Color.Black)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = if (uiState.isChallengeMode) "Momento Crítico: ${game.title}" else game.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${game.whitePlayer} vs ${game.blackPlayer} (${game.year})",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(Screen.GAME_LIST) },
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver al menú principal"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.toggleMute() }
                    ) {
                        Icon(
                            imageVector = if (uiState.isMuted) Icons.AutoMirrored.Filled.VolumeOff else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = if (uiState.isMuted) "Activar sonido" else "Silenciar sonido",
                            tint = if (uiState.isMuted) MaterialTheme.colorScheme.outline else GoldPrimary
                        )
                    }
                    IconButton(
                        onClick = { viewModel.toggleBoardThemeDialog(true) },
                        modifier = Modifier.testTag("button_change_board")
                    ) {
                        Icon(
                            imageVector = Icons.Default.GridView,
                            contentDescription = "Cambiar tablero",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(
                        onClick = { viewModel.togglePieceStyleDialog(true) },
                        modifier = Modifier.testTag("button_change_pieces")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = "Cambiar piezas",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(
                        onClick = { viewModel.toggleHistoricalDialog(true) },
                        modifier = Modifier.testTag("history_intro_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Introducción histórica",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(
                        onClick = { viewModel.toggleFlip() },
                        modifier = Modifier.testTag("flip_board_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapVert,
                            contentDescription = "Girar tablero"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.testTag("viewer_top_app_bar")
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            // Quick Critical Moments Bar for the active game
            if (game.criticalMoments.isNotEmpty()) {
                CriticalMomentsBar(
                    moments = game.criticalMoments,
                    activeChallenge = uiState.activeChallenge,
                    solvedChallengeIds = uiState.solvedChallengeIds,
                    onMomentSelected = { moment -> viewModel.startChallenge(game, moment) },
                    onExitChallenge = { viewModel.exitChallengeMode() }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Chessboard + Vertical Evaluation Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 380.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Vertical Evaluation Bar (Stockfish gauge - hidden during unsolved challenge)
                if (uiState.isEngineEnabled && !isUnsolvedChallenge) {
                    VerticalEvaluationBar(
                        evaluation = uiState.engineEvaluation,
                        isFlipped = uiState.isFlipped,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 8.dp)
                    )
                }

                // The 8x8 Board
                ChessBoardView(
                    boardState = uiState.boardState,
                    selectedSquare = uiState.selectedSquare,
                    lastMove = uiState.lastMove,
                    bestMove = if (uiState.isEngineEnabled && !isUnsolvedChallenge) {
                        uiState.engineEvaluation?.bestMove
                    } else null,
                    isFlipped = uiState.isFlipped,
                    boardTheme = uiState.selectedBoardTheme,
                    pieceStyle = uiState.selectedPieceStyle,
                    onSquareClick = { square -> viewModel.onSquareClicked(square) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Dos botones dedicados: cambiar tablero y cambiar piezas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilledTonalButton(
                    onClick = { viewModel.toggleBoardThemeDialog(true) },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("quick_button_change_board")
                ) {
                    Icon(
                        imageVector = Icons.Default.GridView,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Tablero",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                FilledTonalButton(
                    onClick = { viewModel.togglePieceStyleDialog(true) },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("quick_button_change_pieces")
                ) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Piezas",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Challenge Mode ("Buscar la continuación") Interactive Panel
            if (uiState.isChallengeMode && uiState.activeChallenge != null) {
                CriticalChallengePanel(
                    moment = uiState.activeChallenge!!,
                    status = uiState.challengeStatus,
                    isWhiteTurn = uiState.boardState.turn == PieceColor.WHITE,
                    showHint = uiState.showChallengeHint,
                    wrongAttempts = uiState.wrongAttemptsCount,
                    earnedXp = uiState.lastEarnedXp,
                    stars = uiState.userProgress.challengeStars[uiState.activeChallenge?.id] ?: 3,
                    onToggleHint = { viewModel.toggleChallengeHint() },
                    onRetry = { viewModel.retryChallenge() },
                    onRevealSolution = { viewModel.revealChallengeSolution() },
                    onNextChallenge = { viewModel.startNextChallenge() },
                    onExitToAnalysis = { viewModel.exitChallengeMode() }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Free explore mode banner
            AnimatedVisibility(visible = uiState.isFreeExploreMode && !uiState.isChallengeMode) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .testTag("free_explore_banner")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Modo Exploración Libre (Variante propia)",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }

                        FilledTonalButton(
                            onClick = { viewModel.resetToBookLine() },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary
                            ),
                            modifier = Modifier.testTag("reset_book_line_button")
                        ) {
                            Text("Volver", fontSize = 12.sp)
                        }
                    }
                }
            }

            // Navigation Controls (First, Prev, Play/Pause, Next, Last, Engine Toggle)
            if (!isUnsolvedChallenge) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { viewModel.goToStart() },
                            enabled = uiState.currentPly > 0,
                            modifier = Modifier.testTag("nav_first_button")
                        ) {
                            Icon(Icons.Default.FirstPage, contentDescription = "Primera jugada")
                        }

                        IconButton(
                            onClick = { viewModel.previousMove() },
                            enabled = uiState.currentPly > 0,
                            modifier = Modifier.testTag("nav_prev_button")
                        ) {
                            Icon(Icons.AutoMirrored.Filled.NavigateBefore, contentDescription = "Jugada anterior")
                        }

                        FilledTonalIconButton(
                            onClick = { viewModel.toggleAutoPlay() },
                            modifier = Modifier.testTag("nav_play_pause_button")
                        ) {
                            Icon(
                                imageVector = if (uiState.isAutoPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (uiState.isAutoPlaying) "Pausar" else "Reproducir partida"
                            )
                        }

                        IconButton(
                            onClick = { viewModel.nextMove() },
                            enabled = uiState.currentPly < game.moves.size,
                            modifier = Modifier.testTag("nav_next_button")
                        ) {
                            Icon(Icons.AutoMirrored.Filled.NavigateNext, contentDescription = "Siguiente jugada")
                        }

                        IconButton(
                            onClick = { viewModel.goToEnd() },
                            enabled = uiState.currentPly < game.moves.size,
                            modifier = Modifier.testTag("nav_last_button")
                        ) {
                            Icon(Icons.AutoMirrored.Filled.LastPage, contentDescription = "Última jugada")
                        }

                        // Engine switch
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { viewModel.toggleEngine() }
                                .padding(horizontal = 6.dp)
                                .testTag("engine_toggle_row")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = "Stockfish",
                                tint = if (uiState.isEngineEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Stockfish",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (uiState.isEngineEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Stockfish Live Evaluation Summary Card
                if (uiState.isEngineEnabled) {
                    StockfishStatusCard(uiState = uiState)
                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Paraphrased Master Commentary Card
                MasterCommentaryCard(
                    game = game,
                    currentPly = uiState.currentPly,
                    onHistoricalIntroClick = { viewModel.toggleHistoricalDialog(true) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Interactive Move List Ribbon
                MoveListRibbon(
                    moves = game.moves,
                    currentPly = uiState.currentPly,
                    onMoveClick = { ply -> viewModel.jumpToPly(ply) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Historical Introduction Dialog
    if (uiState.showHistoricalIntroDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.toggleHistoricalDialog(false) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            },
            title = {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "${game.whitePlayer} vs ${game.blackPlayer}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${game.event} (${game.year}) • ${game.eco} ${game.openingName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Tema Estratégico: ${game.strategicTheme}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Introducción Histórica y Contextual:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = game.historicalIntroduction,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Era: ${game.chapter.displayName}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = game.chapter.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.toggleHistoricalDialog(false) },
                    modifier = Modifier.testTag("dialog_close_button")
                ) {
                    Text("Cerrar")
                }
            }
        )
    }

    // Dedicated Board Theme Dialog
    if (uiState.showBoardThemeDialog) {
        BoardThemeDialog(
            currentTheme = uiState.selectedBoardTheme,
            currentStyle = uiState.selectedPieceStyle,
            onSelectTheme = { viewModel.setBoardTheme(it) },
            onResetDefault = { viewModel.resetBoardTheme() },
            onDismiss = { viewModel.toggleBoardThemeDialog(false) }
        )
    }

    // Dedicated Piece Style Dialog
    if (uiState.showPieceStyleDialog) {
        PieceStyleDialog(
            currentStyle = uiState.selectedPieceStyle,
            currentTheme = uiState.selectedBoardTheme,
            onSelectStyle = { viewModel.setPieceStyle(it) },
            onResetDefault = { viewModel.resetPieceStyle() },
            onDismiss = { viewModel.togglePieceStyleDialog(false) }
        )
    }

    // Board & Pieces Customization Dialog (fallback)
    if (uiState.showAppearanceDialog) {
        BoardCustomizationDialog(
            currentTheme = uiState.selectedBoardTheme,
            currentStyle = uiState.selectedPieceStyle,
            onSelectTheme = { viewModel.setBoardTheme(it) },
            onSelectStyle = { viewModel.setPieceStyle(it) },
            onResetDefaults = { viewModel.resetAppearanceDefaults() },
            onDismiss = { viewModel.toggleAppearanceDialog(false) }
        )
    }

    // Congratulatory Dialog upon completing a game study
    if (uiState.showGameCompletedDialog) {
        val savedGameScore = uiState.userProgress.individualGameScores[game.id]?.totalScore ?: 100
        AlertDialog(
            onDismissRequest = { viewModel.dismissGameCompletedDialog() },
            icon = {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = GoldLight,
                    modifier = Modifier.size(36.dp)
                )
            },
            title = {
                Text(
                    text = "¡Partida Superada y Siguiente Desbloqueada! 🏆",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = GoldLight
                    ),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = game.portraitRes),
                        contentDescription = game.whitePlayer,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .border(2.dp, GoldPrimary, CircleShape)
                            .background(Color.Black)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Has completado el estudio de ${game.whitePlayer} vs ${game.blackPlayer} (${game.year}). Tu personaje avanza a la siguiente casilla del Mapa de Viaje.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = GoldPrimary.copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, GoldPrimary)
                    ) {
                        Text(
                            text = "Puntuación Individual Guardada: $savedGameScore pts",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = GoldLight
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.openNextUnlockedGameFromDialog() },
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                ) {
                    Text("Siguiente Partida", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        viewModel.dismissGameCompletedDialog()
                        viewModel.navigateTo(Screen.GAME_LIST)
                    }
                ) {
                    Text("Volver al Viaje", color = GoldLight)
                }
            },
            containerColor = Color(0xFF1E130D),
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
private fun CriticalMomentsBar(
    moments: List<CriticalMoment>,
    activeChallenge: CriticalMoment?,
    solvedChallengeIds: Set<String>,
    onMomentSelected: (CriticalMoment) -> Unit,
    onExitChallenge: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (activeChallenge != null) {
            item {
                ElevatedFilterChip(
                    selected = false,
                    onClick = onExitChallenge,
                    label = { Text("Modo Estudio") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            }
        }
        items(moments, key = { it.id }) { moment ->
            val isSelected = activeChallenge?.id == moment.id
            val isSolved = moment.id in solvedChallengeIds
            val moveNumber = (moment.plyBefore / 2) + 1
            ElevatedFilterChip(
                selected = isSelected,
                onClick = { onMomentSelected(moment) },
                leadingIcon = {
                    Icon(
                        imageVector = if (isSolved) Icons.Default.CheckCircle else Icons.Default.Bolt,
                        contentDescription = null,
                        tint = if (isSolved) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                },
                label = {
                    Text(
                        text = "Reto Jugada $moveNumber: ${moment.title}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = FilterChipDefaults.elevatedFilterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.testTag("moment_chip_${moment.id}")
            )
        }
    }
}

@Composable
fun CriticalChallengePanel(
    moment: CriticalMoment,
    status: ChallengeStatus,
    isWhiteTurn: Boolean,
    showHint: Boolean,
    wrongAttempts: Int,
    earnedXp: Int? = null,
    stars: Int = 3,
    onToggleHint: () -> Unit,
    onRetry: () -> Unit,
    onRevealSolution: () -> Unit,
    onNextChallenge: () -> Unit,
    onExitToAnalysis: () -> Unit,
    modifier: Modifier = Modifier
) {
    val moveNum = (moment.plyBefore / 2) + 1
    val turnLabel = if (isWhiteTurn) "Juegan Blancas (Jugada $moveNum)" else "Juegan Negras (Jugada $moveNum...)"

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (status) {
                ChallengeStatus.SOLVED -> Color(0xFF1B5E20).copy(alpha = 0.25f)
                ChallengeStatus.WRONG_TRY -> Color(0xFFB71C1C).copy(alpha = 0.22f)
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
        ),
        border = BorderStroke(
            width = 1.5.dp,
            color = when (status) {
                ChallengeStatus.SOLVED -> Color(0xFF4CAF50)
                ChallengeStatus.WRONG_TRY -> Color(0xFFEF5350)
                else -> MaterialTheme.colorScheme.primary
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("critical_challenge_panel")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Top Header: Challenge Mode Badge + Engine Locked indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Extension,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = turnLabel,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Sin Ayuda del Módulo",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = moment.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = moment.prompt,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Status-specific content
            when (status) {
                ChallengeStatus.PENDING, ChallengeStatus.WRONG_TRY -> {
                    if (status == ChallengeStatus.WRONG_TRY) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            color = Color(0xFFB71C1C).copy(alpha = 0.25f),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ErrorOutline,
                                    contentDescription = null,
                                    tint = Color(0xFFEF5350),
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Esa jugada no es la continuación magistral (Intentos: $wrongAttempts). ¡Toca la pieza en el tablero para probar otra idea o pide una pista!",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Instrucción: Toca en el tablero la pieza y casilla de destino.",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = GoldPrimary.copy(alpha = 0.2f),
                                border = BorderStroke(0.5.dp, GoldPrimary)
                            ) {
                                Text(
                                    text = "+100 XP (★★★)",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldLight,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    AnimatedVisibility(visible = showHint) {
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Pista: ${moment.hint}",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = onToggleHint,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("challenge_hint_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (showHint) "Ocultar Pista" else "Ver Pista", fontSize = 12.sp)
                        }

                        FilledTonalButton(
                            onClick = onRevealSolution,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("challenge_reveal_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Ver Solución", fontSize = 12.sp)
                        }
                    }
                }

                ChallengeStatus.SOLVED, ChallengeStatus.REVEALED -> {
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = if (status == ChallengeStatus.SOLVED) {
                            Color(0xFF2E7D32).copy(alpha = 0.25f)
                        } else {
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = if (status == ChallengeStatus.SOLVED) EmeraldWin else MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (status == ChallengeStatus.SOLVED) {
                                            "¡Excelente! Encontraste ${moment.solutionSan}"
                                        } else {
                                            "Jugada Magistral: ${moment.solutionSan}"
                                        },
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                if (status == ChallengeStatus.SOLVED) {
                                    Row {
                                        repeat(3) { i ->
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = null,
                                                tint = if (i < stars) AmberStar else Color.Gray.copy(alpha = 0.3f),
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            if (status == ChallengeStatus.SOLVED) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "+${earnedXp ?: 300} pts guardados en tu usuario • ¡Siguiente partida desbloqueada!",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = EmeraldWin,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = moment.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                lineHeight = 19.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = onExitToAnalysis,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Analizar con Motor", fontSize = 12.sp)
                        }

                        Button(
                            onClick = onNextChallenge,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("next_challenge_button")
                        ) {
                            Text("Siguiente Reto", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StockfishStatusCard(
    uiState: ChessUiState,
    modifier: Modifier = Modifier
) {
    val eval = uiState.engineEvaluation

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("stockfish_status_card")
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Análisis Motor Stockfish",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                // Eval Score badge
                if (eval != null) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (eval.scoreCp >= 0) Color(0xFF2E7D32) else Color(0xFFC62828)
                    ) {
                        Text(
                            text = eval.formattedScore,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                } else if (uiState.isEvaluating) {
                    Text(
                        text = "Calculando...",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (eval != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (eval.bestMoveSan.isNotEmpty()) "Línea recomendada: ${eval.bestMoveSan}" else "Sin movimientos válidos",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Profundidad: ${eval.depth} • Nodos: ${eval.nodesEvaluated}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            } else {
                Text(
                    text = "El motor evalúa automáticamente cada movimiento.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun MasterCommentaryCard(
    game: BookGame,
    currentPly: Int,
    onHistoricalIntroClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentMove = if (currentPly in 1..game.moves.size) game.moves[currentPly - 1] else null
    val commentary = currentMove?.commentary

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag("master_commentary_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.HistoryEdu,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Análisis Histórico y Estratégico",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (currentMove != null) {
                    Text(
                        text = currentMove.formattedMove,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                } else {
                    Text(
                        text = "Posición inicial",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (!commentary.isNullOrBlank()) {
                Text(
                    text = "\"$commentary\"",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else if (currentPly == 0) {
                Text(
                    text = "${game.historicalIntroduction.take(190)}...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Toca para leer la introducción histórica completa",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onHistoricalIntroClick() }
                )
            } else {
                Text(
                    text = "Fase de maniobra posicional y preparación estratégica. Puedes activar un Momento Crítico arriba o explorar variantes en el tablero.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun MoveListRibbon(
    moves: List<AnnotatedMove>,
    currentPly: Int,
    onMoveClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(currentPly) {
        if (currentPly > 0 && currentPly <= moves.size) {
            val targetMovePair = (currentPly - 1) / 2
            listState.animateScrollToItem(targetMovePair)
        }
    }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("move_list_ribbon")
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Notación de la Partida",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            val movePairs = remember(moves) {
                val pairs = mutableListOf<Pair<AnnotatedMove, AnnotatedMove?>>()
                var i = 0
                while (i < moves.size) {
                    val white = moves[i]
                    val black = if (i + 1 < moves.size) moves[i + 1] else null
                    pairs.add(Pair(white, black))
                    i += 2
                }
                pairs
            }

            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    val isSelected = currentPly == 0
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                        border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier
                            .clickable { onMoveClick(0) }
                            .testTag("move_item_0")
                    ) {
                        Text(
                            text = "Inicio",
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                        )
                    }
                }

                itemsIndexed(movePairs) { index, pair ->
                    val moveNum = index + 1
                    val whitePly = pair.first.ply
                    val isWhiteSelected = currentPly == whitePly
                    val blackPly = pair.second?.ply
                    val isBlackSelected = blackPly != null && currentPly == blackPly

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "$moveNum.",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (isWhiteSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            modifier = Modifier
                                .clickable { onMoveClick(whitePly) }
                                .testTag("move_item_$whitePly")
                        ) {
                            Text(
                                text = pair.first.san + (pair.first.glyph ?: ""),
                                color = if (isWhiteSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                fontSize = 12.sp,
                                fontWeight = if (pair.first.glyph != null) FontWeight.ExtraBold else FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                            )
                        }

                        if (pair.second != null && blackPly != null) {
                            Spacer(modifier = Modifier.width(2.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = if (isBlackSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                modifier = Modifier
                                    .clickable { onMoveClick(blackPly) }
                                    .testTag("move_item_$blackPly")
                            ) {
                                Text(
                                    text = pair.second!!.san + (pair.second!!.glyph ?: ""),
                                    color = if (isBlackSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                    fontSize = 12.sp,
                                    fontWeight = if (pair.second!!.glyph != null) FontWeight.ExtraBold else FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
