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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.data.BookDatabase
import com.example.chess.data.BookGame
import com.example.chess.data.GameChallengeItem
import com.example.chess.data.GameChapter
import com.example.chess.data.portraitRes
import com.example.chess.gamification.UserProgressState
import com.example.chess.model.BoardTheme
import com.example.chess.model.PieceStyle
import com.example.ui.theme.AmberStar
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.EmeraldWin
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    viewModel: ChessViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val allGamesCanonical = remember { BookDatabase.getAllGames() }
    val gameIndexMap = remember(allGamesCanonical) {
        allGamesCanonical.mapIndexed { idx, g -> g.id to idx }.toMap()
    }
    val totalGamesCount = allGamesCanonical.size
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
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Grandmaster Journey",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Mis Geniales Predecesores",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                },
                actions = {
                    // Botón de Registro / Usuario Activo
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = GoldPrimary.copy(alpha = 0.16f),
                        border = BorderStroke(1.dp, GoldPrimary),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clickable { viewModel.openRegistrationDialog(0) }
                            .testTag("top_bar_register_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Registro de usuario",
                                tint = GoldLight,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (uiState.userProgress.isRegistered) {
                                    uiState.userProgress.displayUsername
                                } else {
                                    "Registro"
                                },
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = GoldLight
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

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
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1A100B),
                tonalElevation = 8.dp
            ) {
                val navColors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1B1008),
                    selectedTextColor = GoldLight,
                    indicatorColor = GoldPrimary,
                    unselectedIconColor = Color(0xFFB8A898),
                    unselectedTextColor = Color(0xFFB8A898)
                )

                NavigationBarItem(
                    selected = uiState.selectedHomeTab == HomeTab.JOURNEY_MAP,
                    onClick = { viewModel.onHomeTabSelected(HomeTab.JOURNEY_MAP) },
                    icon = { Icon(Icons.Default.Map, contentDescription = "Viaje") },
                    label = { Text("Viaje", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = navColors,
                    modifier = Modifier.testTag("tab_journey_map")
                )
                NavigationBarItem(
                    selected = uiState.selectedHomeTab == HomeTab.EXPLORE_GAMES,
                    onClick = { viewModel.onHomeTabSelected(HomeTab.EXPLORE_GAMES) },
                    icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "Partidas") },
                    label = { Text("Partidas", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = navColors,
                    modifier = Modifier.testTag("tab_explore_games")
                )
                NavigationBarItem(
                    selected = uiState.selectedHomeTab == HomeTab.CRITICAL_CHALLENGES,
                    onClick = { viewModel.onHomeTabSelected(HomeTab.CRITICAL_CHALLENGES) },
                    icon = { Icon(Icons.Default.PsychologyAlt, contentDescription = "Retos") },
                    label = { Text("Retos", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = navColors,
                    modifier = Modifier.testTag("tab_critical_challenges")
                )
                NavigationBarItem(
                    selected = uiState.selectedHomeTab == HomeTab.MUSEUM_PROGRESS,
                    onClick = { viewModel.onHomeTabSelected(HomeTab.MUSEUM_PROGRESS) },
                    icon = { Icon(Icons.Default.MilitaryTech, contentDescription = "Museo") },
                    label = { Text("Museo", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                    colors = navColors,
                    modifier = Modifier.testTag("tab_museum_progress")
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        when (uiState.selectedHomeTab) {
            HomeTab.JOURNEY_MAP -> {
                JourneyMapScreen(
                    userProgress = uiState.userProgress,
                    onOpenGameStudy = { game -> viewModel.openGame(game) },
                    onStartGameChallenge = { game ->
                        game.criticalMoments.firstOrNull()?.let { moment ->
                            viewModel.startChallenge(game, moment)
                        } ?: viewModel.openGame(game)
                    },
                    onOpenRegistrationDialog = { tab -> viewModel.openRegistrationDialog(tab) },
                    onMoveCharacterToHome = { atHome -> viewModel.setCharacterAtHome(atHome) },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            HomeTab.MUSEUM_PROGRESS -> {
                MuseumProgressScreen(
                    userProgress = uiState.userProgress,
                    onOpenRegistrationDialog = { tab -> viewModel.openRegistrationDialog(tab) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            HomeTab.EXPLORE_GAMES, HomeTab.CRITICAL_CHALLENGES -> {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = innerPadding.calculateTopPadding() + 6.dp,
                        bottom = innerPadding.calculateBottomPadding() + 24.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("games_list")
                ) {
                    item {
                        HeroMenuBanner(
                            totalGames = totalGamesCount,
                            totalChallenges = totalChallengesCount,
                            solvedChallenges = solvedCount,
                            userProgress = uiState.userProgress,
                            onRandomChallengeClick = { viewModel.startRandomChallenge() },
                            onOpenScoresClick = { viewModel.openRegistrationDialog(1) }
                        )
                    }

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

                    item {
                        EraSelectorRow(
                            selectedChapter = uiState.selectedChapter,
                            onChapterSelected = { viewModel.onChapterSelected(it) }
                        )
                    }

                    item {
                        AppearanceDualButtonsBar(
                            theme = uiState.selectedBoardTheme,
                            style = uiState.selectedPieceStyle,
                            onChangeBoard = { viewModel.toggleBoardThemeDialog(true) },
                            onChangePieces = { viewModel.togglePieceStyleDialog(true) }
                        )
                    }

                    if (uiState.selectedHomeTab == HomeTab.EXPLORE_GAMES) {
                        items(uiState.games, key = { it.id }) { game ->
                            val idx = gameIndexMap[game.id] ?: 0
                            val isUnlocked = idx <= uiState.userProgress.unlockedUpToIndex
                            GameCard(
                                game = game,
                                journeyNumber = idx + 1,
                                isUnlocked = isUnlocked,
                                userProgress = uiState.userProgress,
                                onOpenGame = {
                                    if (isUnlocked) viewModel.openGame(game)
                                    else viewModel.onHomeTabSelected(HomeTab.JOURNEY_MAP)
                                },
                                onStartChallenge = {
                                    if (isUnlocked) {
                                        game.criticalMoments.firstOrNull()?.let { moment ->
                                            viewModel.startChallenge(game, moment)
                                        } ?: viewModel.openGame(game)
                                    } else {
                                        viewModel.onHomeTabSelected(HomeTab.JOURNEY_MAP)
                                    }
                                }
                            )
                        }
                    } else {
                        items(uiState.challenges, key = { it.moment.id }) { item ->
                            val idx = gameIndexMap[item.game.id] ?: 0
                            val isUnlocked = idx <= uiState.userProgress.unlockedUpToIndex
                            ChallengeCard(
                                item = item,
                                journeyNumber = idx + 1,
                                isUnlocked = isUnlocked,
                                userProgress = uiState.userProgress,
                                onStartChallenge = {
                                    if (isUnlocked) {
                                        viewModel.startChallenge(item.game, item.moment)
                                    } else {
                                        viewModel.onHomeTabSelected(HomeTab.JOURNEY_MAP)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        if (uiState.showRegistrationDialog) {
            UserRegistrationDialog(
                userProgress = uiState.userProgress,
                initialTab = uiState.registrationDialogInitialTab,
                onRegisterUser = { username, email, pin, avatar, migrate ->
                    viewModel.registerUser(username, email, pin, avatar, migrate)
                },
                onLoginUser = { userId, pin -> viewModel.loginUser(userId, pin) },
                onLogout = { viewModel.logoutUser() },
                onSelectAvatar = { avatar -> viewModel.selectPlayerAvatar(avatar) },
                onOpenGameById = { gameId -> viewModel.openGameById(gameId) },
                onDismiss = { viewModel.dismissRegistrationDialog() }
            )
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
    userProgress: UserProgressState,
    onRandomChallengeClick: () -> Unit,
    onOpenScoresClick: () -> Unit,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = userProgress.currentAvatar.drawableRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, GoldPrimary, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = userProgress.displayUsername,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )
                        Text(
                            text = "${userProgress.rank.title} • Desbloqueadas: ${userProgress.unlockedUpToIndex + 1}/$totalGames",
                            style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFC7CBD5))
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = GoldPrimary.copy(alpha = 0.15f),
                    border = BorderStroke(0.5.dp, GoldPrimary),
                    modifier = Modifier.clickable { onOpenScoresClick() }
                ) {
                    Text(
                        text = "${userProgress.totalXp} pts",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = GoldLight
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    StatBadge(
                        value = "${userProgress.unlockedUpToIndex + 1}/$totalGames",
                        label = "Desbloqueadas"
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
                        text = "Reto Desbloqueado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
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
        items(GameChapter.entries) { chapter ->
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
    journeyNumber: Int,
    isUnlocked: Boolean,
    userProgress: UserProgressState,
    onOpenGame: () -> Unit,
    onStartChallenge: () -> Unit,
    modifier: Modifier = Modifier
) {
    val solvedInGame = game.criticalMoments.count { it.id in userProgress.challengeStars.keys }
    val totalInGame = game.criticalMoments.size
    val isCompleted = game.id in userProgress.completedGameIds
    val maxPly = userProgress.gameMaxPly[game.id] ?: 0
    val individualRecord = userProgress.individualGameScores[game.id]

    Card(
        onClick = onOpenGame,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
            } else {
                Color(0xFF1A120E)
            }
        ),
        border = when {
            isCompleted -> BorderStroke(1.dp, EmeraldWin.copy(alpha = 0.6f))
            isUnlocked -> BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.35f))
            else -> BorderStroke(1.dp, Color(0xFF3E2D21))
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (isUnlocked) 1f else 0.72f)
            .testTag("game_card_${game.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = game.portraitRes),
                        contentDescription = game.whitePlayer,
                        contentScale = ContentScale.Crop,
                        colorFilter = if (isUnlocked) null else ColorFilter.colorMatrix(
                            ColorMatrix().apply { setToSaturation(0.1f) }
                        ),
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .border(
                                1.5.dp,
                                if (isUnlocked) GoldPrimary else Color.Gray,
                                CircleShape
                            )
                            .background(Color.Black)
                    )
                    if (!isUnlocked) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Bloqueada",
                            tint = GoldLight,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

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
                                text = "Parada #$journeyNumber • ${game.year}",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        if (individualRecord != null && individualRecord.totalScore > 0) {
                            Surface(
                                color = GoldPrimary.copy(alpha = 0.18f),
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(0.5.dp, GoldPrimary)
                            ) {
                                Text(
                                    text = "${individualRecord.totalScore} pts",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 11.sp,
                                    color = GoldLight,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        } else if (!isUnlocked) {
                            Surface(
                                color = Color(0xFF3E2D21),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "🔒 Bloqueada",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = Color(0xFFCBBBA8),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${game.event} • ${game.eco} ${game.openingName}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = game.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

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

            Text(
                text = game.strategicTheme,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (!isUnlocked) {
                Text(
                    text = "Supera primero la Partida #${journeyNumber - 1} en el Mapa de Viaje para desbloquearla.",
                    style = MaterialTheme.typography.labelSmall.copy(color = GoldLight)
                )
            } else if (isCompleted) {
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
                            text = "Partida Superada • Puntuación guardada: ${individualRecord?.totalScore ?: 100} pts",
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

            if (isUnlocked) {
                Spacer(modifier = Modifier.height(12.dp))
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
                        Text("Estudiar", fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                                text = if (solvedInGame > 0) "Reto ($solvedInGame/$totalInGame)" else "Momento Crítico",
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
}

@Composable
fun ChallengeCard(
    item: GameChallengeItem,
    journeyNumber: Int,
    isUnlocked: Boolean,
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
            containerColor = if (isUnlocked) {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
            } else {
                Color(0xFF1A120E)
            }
        ),
        border = if (isSolved) BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.6f)) else null,
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (isUnlocked) 1f else 0.68f)
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
                            text = if (isWhiteToMove) "Parada #$journeyNumber • Blancas (J.$moveNum)" else "Parada #$journeyNumber • Negras (J.$moveNum...)",
                            color = if (isWhiteToMove) Color.Black else Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                if (!isUnlocked) {
                    Surface(
                        color = Color(0xFF3E2D21),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "🔒 Bloqueado",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFCBBBA8),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                } else if (isSolved) {
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
                        }
                    }
                } else {
                    Surface(
                        color = GoldPrimary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(0.5.dp, GoldPrimary)
                    ) {
                        Text(
                            text = "+300 pts",
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
                    containerColor = when {
                        !isUnlocked -> Color(0xFF3E2D21)
                        isSolved -> MaterialTheme.colorScheme.surfaceVariant
                        else -> MaterialTheme.colorScheme.primary
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (isUnlocked) Icons.Default.Extension else Icons.Default.Lock,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = when {
                        !isUnlocked -> "Desbloquea en el Viaje (Partida #$journeyNumber)"
                        isSolved -> "Mejorar Puntuación (${stars}★)"
                        else -> "Buscar la Mejor Continuación"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}
