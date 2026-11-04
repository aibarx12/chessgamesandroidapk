package com.example.chess.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.LinearProgressIndicator
import com.example.chess.data.portraitRes
import com.example.chess.data.dividerRes
import com.example.chess.gamification.UserProgressState
import com.example.ui.theme.AmberStar
import com.example.ui.theme.EmeraldWin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.chess.model.BoardTheme
import com.example.chess.model.PieceStyle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.data.BookDatabase
import com.example.chess.data.BookGame
import com.example.chess.data.GameChallengeItem
import com.example.chess.data.GameChapter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    viewModel: ChessViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val totalGamesCount = BookDatabase.getAllGames().size
    val totalChallengesCount = BookDatabase.getAllChallenges().size
    val solvedCount = uiState.solvedChallengeIds.size

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Grandes Maestros del Ajedrez",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Historia, Análisis Magistral y Momentos Críticos",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.toggleBoardThemeDialog(true) },
                        modifier = Modifier.testTag("game_list_board_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.GridView,
                            contentDescription = "Cambiar tablero",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(
                        onClick = { viewModel.togglePieceStyleDialog(true) },
                        modifier = Modifier.testTag("game_list_pieces_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = "Cambiar piezas",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.testTag("game_list_top_app_bar")
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        if (uiState.selectedHomeTab == HomeTab.MUSEUM_PROGRESS) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                HeroMenuBanner(
                    totalGames = totalGamesCount,
                    totalChallenges = totalChallengesCount,
                    solvedChallenges = solvedCount,
                    selectedTab = uiState.selectedHomeTab,
                    userProgress = uiState.userProgress,
                    onTabSelected = { viewModel.onHomeTabSelected(it) },
                    onRandomChallengeClick = { viewModel.startRandomChallenge() },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                MuseumProgressScreen(
                    userProgress = uiState.userProgress,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding() + 4.dp,
                    bottom = innerPadding.calculateBottomPadding() + 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("games_list")
            ) {
                // Hero Dashboard & Mode Switcher
                item {
                    HeroMenuBanner(
                        totalGames = totalGamesCount,
                        totalChallenges = totalChallengesCount,
                        solvedChallenges = solvedCount,
                        selectedTab = uiState.selectedHomeTab,
                        userProgress = uiState.userProgress,
                        onTabSelected = { viewModel.onHomeTabSelected(it) },
                        onRandomChallengeClick = { viewModel.startRandomChallenge() }
                    )
                }

                // Search Bar
                item {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = { viewModel.onSearchQueryChange(it) },
                        placeholder = {
                            Text(
                                if (uiState.selectedHomeTab == HomeTab.EXPLORE_GAMES) {
                                    "Buscar por campeón, rival, año, apertura o tema..."
                                } else {
                                    "Buscar momento crítico por jugador, año o táctica..."
                                }
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar"
                            )
                        },
                        trailingIcon = {
                            if (uiState.searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("search_field")
                    )
                }

                // Era / Chapter Selector Cards
                item {
                    EraSelectorRow(
                        selectedChapter = uiState.selectedChapter,
                        onChapterSelected = { viewModel.onChapterSelected(it) }
                    )
                }

                // Dos botones dedicados: uno para cambiar el tablero y otro para cambiar las piezas
                item {
                    AppearanceDualButtonsBar(
                        theme = uiState.selectedBoardTheme,
                        style = uiState.selectedPieceStyle,
                        onChangeBoard = { viewModel.toggleBoardThemeDialog(true) },
                        onChangePieces = { viewModel.togglePieceStyleDialog(true) }
                    )
                }

                // Content List: either Games or Critical Challenges
                if (uiState.selectedHomeTab == HomeTab.EXPLORE_GAMES) {
                    items(uiState.games, key = { it.id }) { game ->
                        GameCard(
                            game = game,
                            userProgress = uiState.userProgress,
                            onOpenGame = { viewModel.openGame(game) },
                            onStartChallenge = {
                                game.criticalMoments.firstOrNull()?.let { moment ->
                                    viewModel.startChallenge(game, moment)
                                } ?: viewModel.openGame(game)
                            }
                        )
                    }
                } else {
                    items(uiState.challenges, key = { it.moment.id }) { item ->
                        ChallengeCard(
                            item = item,
                            userProgress = uiState.userProgress,
                            onStartChallenge = { viewModel.startChallenge(item.game, item.moment) }
                        )
                    }
                }
            }
        }

        if (uiState.showBoardThemeDialog) {
            BoardThemeDialog(
                currentTheme = uiState.selectedBoardTheme,
                currentStyle = uiState.selectedPieceStyle,
                onSelectTheme = { viewModel.setBoardTheme(it) },
                onResetDefault = { viewModel.resetBoardTheme() },
                onDismiss = { viewModel.toggleBoardThemeDialog(false) }
            )
        }

        if (uiState.showPieceStyleDialog) {
            PieceStyleDialog(
                currentStyle = uiState.selectedPieceStyle,
                currentTheme = uiState.selectedBoardTheme,
                onSelectStyle = { viewModel.setPieceStyle(it) },
                onResetDefault = { viewModel.resetPieceStyle() },
                onDismiss = { viewModel.togglePieceStyleDialog(false) }
            )
        }

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
    }
}

@Composable
private fun AppearanceDualButtonsBar(
    theme: BoardTheme,
    style: PieceStyle,
    onChangeBoard: () -> Unit,
    onChangePieces: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Botón 1: Cambiar Tablero
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
            modifier = Modifier
                .weight(1f)
                .clickable { onChangeBoard() }
                .testTag("home_button_change_board")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(theme.borderColor)
                        .padding(2.dp)
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

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Tablero",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = theme.displayName,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Botón 2: Cambiar Piezas
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
            modifier = Modifier
                .weight(1f)
                .clickable { onChangePieces() }
                .testTag("home_button_change_pieces")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(26.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Piezas",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = style.displayName,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroMenuBanner(
    totalGames: Int,
    totalChallenges: Int,
    solvedChallenges: Int,
    selectedTab: HomeTab,
    userProgress: UserProgressState,
    onTabSelected: (HomeTab) -> Unit,
    onRandomChallengeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ),
        border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.4f)),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2C1C13),
                            Color(0xFF1E130D)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            // Rank and XP Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = GoldPrimary.copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, GoldPrimary),
                        modifier = Modifier.size(34.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = userProgress.rank.iconName, fontSize = 16.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = userProgress.rank.title,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )
                        Text(
                            text = "Rango de Maestría • Nivel ${userProgress.rank.level}",
                            style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFC7CBD5))
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = GoldPrimary.copy(alpha = 0.15f),
                    border = BorderStroke(0.5.dp, GoldPrimary)
                ) {
                    Text(
                        text = "${userProgress.totalXp} XP",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = GoldLight
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Top stats row + Quick Challenge button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    StatBadge(
                        value = "${userProgress.completedGamesCount}/$totalGames",
                        label = "Partidas Dominadas"
                    )
                    StatBadge(
                        value = "$solvedChallenges/$totalChallenges",
                        label = "Retos Resueltos"
                    )
                }

                FilledTonalButton(
                    onClick = onRandomChallengeClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = GoldPrimary,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.testTag("random_challenge_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Shuffle,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Reto al Azar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Mode Selection Segmented Buttons (3 tabs)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF140C08))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ModeTabButton(
                    selected = selectedTab == HomeTab.EXPLORE_GAMES,
                    title = "Partidas",
                    subtitle = "Análisis",
                    icon = Icons.AutoMirrored.Filled.MenuBook,
                    onClick = { onTabSelected(HomeTab.EXPLORE_GAMES) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("tab_explore_games")
                )
                ModeTabButton(
                    selected = selectedTab == HomeTab.CRITICAL_CHALLENGES,
                    title = "Retos",
                    subtitle = "Táctica",
                    icon = Icons.Default.PsychologyAlt,
                    onClick = { onTabSelected(HomeTab.CRITICAL_CHALLENGES) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("tab_critical_challenges")
                )
                ModeTabButton(
                    selected = selectedTab == HomeTab.MUSEUM_PROGRESS,
                    title = "Museo",
                    subtitle = "Progreso",
                    icon = Icons.Default.MilitaryTech,
                    onClick = { onTabSelected(HomeTab.MUSEUM_PROGRESS) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("tab_museum_progress")
                )
            }
        }
    }
}

@Composable
private fun StatBadge(value: String, label: String) {
    Column {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ModeTabButton(
    selected: Boolean,
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle,
                    fontSize = 10.sp,
                    color = if (selected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f) else MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun EraSelectorRow(
    selectedChapter: GameChapter?,
    onChapterSelected: (GameChapter?) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ElevatedFilterChip(
                selected = selectedChapter == null,
                onClick = { onChapterSelected(null) },
                label = {
                    Text(
                        text = "Todas las Eras",
                        fontWeight = if (selectedChapter == null) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = FilterChipDefaults.elevatedFilterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.testTag("chapter_chip_all")
            )
        }
        items(GameChapter.values()) { chapter ->
            val isSelected = selectedChapter == chapter
            ElevatedFilterChip(
                selected = isSelected,
                onClick = { onChapterSelected(chapter) },
                label = {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Text(
                            text = chapter.displayName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        Text(
                            text = chapter.period,
                            fontSize = 10.sp,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            else MaterialTheme.colorScheme.outline
                        )
                    }
                },
                colors = FilterChipDefaults.elevatedFilterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.testTag("chapter_chip_${chapter.name}")
            )
        }
    }
}

@Composable
fun GameCard(
    game: BookGame,
    userProgress: UserProgressState,
    onOpenGame: () -> Unit,
    onStartChallenge: () -> Unit,
    modifier: Modifier = Modifier
) {
    val solvedInGame = game.criticalMoments.count { it.id in userProgress.challengeStars.keys }
    val totalInGame = game.criticalMoments.size
    val isCompleted = game.id in userProgress.completedGameIds
    val maxPly = userProgress.gameMaxPly[game.id] ?: 0

    Card(
        onClick = onOpenGame,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
        ),
        border = if (isCompleted) BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.6f)) else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag("game_card_${game.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: GM Portrait + Event/Year + Result
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = game.portraitRes),
                    contentDescription = game.whitePlayer,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, GoldPrimary, CircleShape)
                        .background(Color.Black)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "${game.event} • ${game.year}",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Surface(
                            color = when (game.result) {
                                "1-0" -> Color(0xFF2E7D32).copy(alpha = 0.2f)
                                "0-1" -> Color(0xFFC62828).copy(alpha = 0.2f)
                                else -> Color(0xFF1565C0).copy(alpha = 0.2f)
                            },
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = game.result,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp,
                                color = when (game.result) {
                                    "1-0" -> Color(0xFF4CAF50)
                                    "0-1" -> Color(0xFFEF5350)
                                    else -> Color(0xFF42A5F5)
                                },
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${game.eco} ${game.openingName}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Evocative Title
            Text(
                text = game.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Players Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = game.whitePlayer,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "vs",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1E1E1E))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = game.blackPlayer,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Strategic theme
            Text(
                text = game.strategicTheme,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Gamification completion / progress status
            Spacer(modifier = Modifier.height(8.dp))
            if (isCompleted) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = EmeraldWin.copy(alpha = 0.15f),
                    border = BorderStroke(0.5.dp, EmeraldWin)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = EmeraldWin,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Partida Dominada (+50 XP)",
                            color = EmeraldWin,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else if (maxPly > 0 && game.moves.isNotEmpty()) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Progreso de estudio",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                        Text(
                            text = "$maxPly/${game.moves.size} jugadas",
                            style = MaterialTheme.typography.labelSmall.copy(color = GoldLight, fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    LinearProgressIndicator(
                        progress = { maxPly.toFloat() / game.moves.size },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = GoldPrimary,
                        trackColor = Color(0xFF332015)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons: Study Game vs Critical Challenge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(
                    onClick = onOpenGame,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ver Partida", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                if (totalInGame > 0) {
                    OutlinedButton(
                        onClick = onStartChallenge,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .weight(1.15f)
                            .testTag("challenge_btn_${game.id}")
                    ) {
                        Icon(
                            imageVector = if (solvedInGame == totalInGame) Icons.Default.CheckCircle else Icons.Default.Bolt,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (solvedInGame > 0) "Momento Crítico ($solvedInGame/$totalInGame)" else "Momento Crítico",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChallengeCard(
    item: GameChallengeItem,
    userProgress: UserProgressState,
    onStartChallenge: () -> Unit,
    modifier: Modifier = Modifier
) {
    val game = item.game
    val moment = item.moment
    val isWhiteToMove = moment.plyBefore % 2 == 0
    val moveNum = (moment.plyBefore / 2) + 1
    val stars = userProgress.challengeStars[moment.id] ?: 0
    val isSolved = stars > 0

    Card(
        onClick = onStartChallenge,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ),
        border = if (isSolved) BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.6f)) else null,
        modifier = modifier
            .fillMaxWidth()
            .testTag("challenge_card_${moment.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = game.portraitRes),
                        contentDescription = game.whitePlayer,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .border(1.dp, GoldPrimary, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = if (isWhiteToMove) Color.White else Color(0xFF212121),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Text(
                            text = if (isWhiteToMove) "Blancas (J. $moveNum)" else "Negras (J. $moveNum...)",
                            color = if (isWhiteToMove) Color.Black else Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                if (isSolved) {
                    Surface(
                        color = GoldPrimary.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(0.5.dp, GoldPrimary)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            repeat(3) { i ->
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = if (i < stars) AmberStar else Color.Gray.copy(alpha = 0.35f),
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Resuelto",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        }
                    }
                } else {
                    Surface(
                        color = GoldPrimary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(0.5.dp, GoldPrimary)
                    ) {
                        Text(
                            text = "+100 XP",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = moment.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "${game.whitePlayer} vs ${game.blackPlayer} • ${game.event} (${game.year})",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = moment.prompt,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onStartChallenge,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSolved) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Extension,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isSolved) "Repetir Reto Crítico (${stars}★)" else "Buscar la Mejor Continuación",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}
