package com.example.chess.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.chess.data.BookDatabase
import com.example.chess.data.BookGame
import com.example.chess.data.GameChapter
import com.example.chess.data.dividerRes
import com.example.chess.data.portraitRes
import com.example.chess.gamification.GameScoreRecord
import com.example.chess.gamification.UserProgressState
import com.example.ui.theme.AmberStar
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.EmeraldWin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import kotlinx.coroutines.launch

private sealed class JourneyMapItem {
    data object HomeBase : JourneyMapItem()
    data class EraDivider(val chapter: GameChapter, val firstGameNumber: Int) : JourneyMapItem()
    data class GameNode(
        val game: BookGame,
        val gameIndex: Int, // 0-based
        @param:DrawableRes val decorRes: Int?
    ) : JourneyMapItem()
}

private val JOURNEY_DECOR_DRAWABLES = listOf(
    R.drawable.decor_hot_air_balloon,
    R.drawable.decor_pocket_compass,
    R.drawable.decor_big_ben,
    R.drawable.decor_sailing_ship,
    R.drawable.decor_steam_train,
    R.drawable.decor_chess_clock,
    R.drawable.decor_fountain_pen,
    R.drawable.decor_antique_globe,
    R.drawable.decor_gramophone,
    R.drawable.decor_hourglass
)

/**
 * Posición horizontal normalizada (0f..1f) del nodo i a lo largo del sendero serpenteante.
 * i = -1 corresponde al nodo inicial HOME (centrado en 0.5f).
 */
private fun nodeXFraction(gameIndex: Int): Float {
    if (gameIndex < 0) return 0.5f
    return when (gameIndex % 4) {
        0 -> 0.50f
        1 -> 0.74f
        2 -> 0.50f
        else -> 0.26f
    }
}

@Composable
fun JourneyMapScreen(
    userProgress: UserProgressState,
    onOpenGameStudy: (BookGame) -> Unit,
    onStartGameChallenge: (BookGame) -> Unit,
    onOpenRegistrationDialog: (initialTab: Int) -> Unit,
    onMoveCharacterToHome: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val allGames = remember { BookDatabase.getAllGames() }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var selectedUnlockedGame by remember { mutableStateOf<Pair<BookGame, Int>?>(null) }
    var lockedNodeAttemptIndex by remember { mutableStateOf<Int?>(null) }

    // Construimos la secuencia completa del viaje: HOME -> Eras y Nodos 1 a 1
    val mapItems = remember(allGames) {
        val list = mutableListOf<JourneyMapItem>()
        list.add(JourneyMapItem.HomeBase)
        var lastChapter: GameChapter? = null
        allGames.forEachIndexed { idx, game ->
            if (game.chapter != lastChapter) {
                lastChapter = game.chapter
                list.add(JourneyMapItem.EraDivider(chapter = game.chapter, firstGameNumber = idx + 1))
            }
            // Colocamos ilustraciones decorativas en las curvas laterales (índices impares)
            val decor = if (idx % 2 == 1) {
                JOURNEY_DECOR_DRAWABLES[(idx / 2) % JOURNEY_DECOR_DRAWABLES.size]
            } else null
            list.add(JourneyMapItem.GameNode(game = game, gameIndex = idx, decorRes = decor))
        }
        list
    }

    // Índice dentro de LazyColumn donde se encuentra la partida actual desbloqueada
    val frontierListIndex = remember(mapItems, userProgress.unlockedUpToIndex) {
        mapItems.indexOfFirst {
            it is JourneyMapItem.GameNode && it.gameIndex == userProgress.unlockedUpToIndex
        }.coerceAtLeast(0)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "journey_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    val bobOffsetDp by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(950, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "avatar_bob"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF170E09),
                        Color(0xFF21140D),
                        Color(0xFF150C08)
                    )
                )
            )
            .testTag("journey_map_screen")
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Barra fija superior de Usuario, Registro y Puntuación Total
            PlayerJourneyStatusBar(
                userProgress = userProgress,
                totalGames = allGames.size,
                onProfileClick = { onOpenRegistrationDialog(0) },
                onScoresClick = { onOpenRegistrationDialog(1) }
            )

            // Mapa vertical serpenteante estilo Grandmaster Journey
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 96.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .testTag("journey_lazy_column")
            ) {
                items(
                    count = mapItems.size,
                    key = { i ->
                        when (val item = mapItems[i]) {
                            is JourneyMapItem.HomeBase -> "journey_home_base"
                            is JourneyMapItem.EraDivider -> "era_${item.chapter.name}"
                            is JourneyMapItem.GameNode -> "node_${item.game.id}"
                        }
                    }
                ) { i ->
                    when (val item = mapItems[i]) {
                        is JourneyMapItem.HomeBase -> {
                            HomeStartingPavilion(
                                userProgress = userProgress,
                                totalGames = allGames.size,
                                bobOffsetDp = bobOffsetDp,
                                onRegisterOrProfileClick = { onOpenRegistrationDialog(0) },
                                onIndividualScoresClick = { onOpenRegistrationDialog(1) },
                                onToggleCharacterAtHome = {
                                    if (!userProgress.characterAtHome) {
                                        onMoveCharacterToHome(true)
                                    } else {
                                        onMoveCharacterToHome(false)
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(frontierListIndex)
                                        }
                                    }
                                },
                                onStartNextUnlockedClick = {
                                    onMoveCharacterToHome(false)
                                    val targetGame = allGames.getOrNull(userProgress.unlockedUpToIndex)
                                    if (targetGame != null) {
                                        selectedUnlockedGame = targetGame to userProgress.unlockedUpToIndex
                                    }
                                }
                            )
                        }

                        is JourneyMapItem.EraDivider -> {
                            val prevGameIdx = item.firstGameNumber - 2
                            val nextGameIdx = item.firstGameNumber - 1
                            val isReached = userProgress.unlockedUpToIndex >= nextGameIdx
                            EraChapterBannerNode(
                                chapter = item.chapter,
                                isReached = isReached,
                                topSeamXFraction = (nodeXFraction(prevGameIdx) + 0.5f) / 2f,
                                bottomSeamXFraction = (0.5f + nodeXFraction(nextGameIdx)) / 2f
                            )
                        }

                        is JourneyMapItem.GameNode -> {
                            val idx = item.gameIndex
                            val isUnlocked = idx <= userProgress.unlockedUpToIndex
                            val isCompleted = item.game.id in userProgress.completedGameIds
                            val isCurrentFrontier = idx == userProgress.unlockedUpToIndex
                            val showCharacterHere = isCurrentFrontier && !userProgress.characterAtHome
                            val scoreRecord = userProgress.individualGameScores[item.game.id]

                            // Comprobar si justo antes hubo un EraDivider para ajustar la costura de la curva
                            val prevItem = mapItems.getOrNull(i - 1)
                            val topSeamX = if (prevItem is JourneyMapItem.EraDivider) {
                                (0.5f + nodeXFraction(idx)) / 2f
                            } else {
                                (nodeXFraction(idx - 1) + nodeXFraction(idx)) / 2f
                            }

                            val nextItem = mapItems.getOrNull(i + 1)
                            val isLastNode = idx == allGames.lastIndex
                            val bottomSeamX = when {
                                isLastNode -> nodeXFraction(idx)
                                nextItem is JourneyMapItem.EraDivider -> (nodeXFraction(idx) + 0.5f) / 2f
                                else -> (nodeXFraction(idx) + nodeXFraction(idx + 1)) / 2f
                            }

                            JourneySnakeGameRow(
                                game = item.game,
                                gameIndex = idx,
                                xFraction = nodeXFraction(idx),
                                topSeamXFraction = topSeamX,
                                bottomSeamXFraction = bottomSeamX,
                                isLastNode = isLastNode,
                                isUnlocked = isUnlocked,
                                isCompleted = isCompleted,
                                isCurrentFrontier = isCurrentFrontier,
                                nextSegmentUnlocked = (idx + 1) <= userProgress.unlockedUpToIndex,
                                showCharacterHere = showCharacterHere,
                                characterAvatarRes = userProgress.currentAvatar.drawableRes,
                                characterName = userProgress.displayUsername,
                                scoreRecord = scoreRecord,
                                decorRes = item.decorRes,
                                pulseScale = if (isCurrentFrontier) pulseScale else 1f,
                                bobOffsetDp = bobOffsetDp,
                                onNodeClick = {
                                    if (isUnlocked) {
                                        if (userProgress.characterAtHome) {
                                            onMoveCharacterToHome(false)
                                        }
                                        selectedUnlockedGame = item.game to idx
                                    } else {
                                        lockedNodeAttemptIndex = idx
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        // Botones flotantes rápidos: Ir a HOME o Ir al Personaje / Partida Actual
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = CharcoalSurface.copy(alpha = 0.95f),
                border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.65f)),
                shadowElevation = 6.dp,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Ir a Home",
                        tint = GoldLight,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldLight
                        )
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(24.dp),
                color = GoldPrimary,
                border = BorderStroke(1.dp, GoldLight),
                shadowElevation = 8.dp,
                modifier = Modifier.clickable {
                    onMoveCharacterToHome(false)
                    coroutineScope.launch {
                        listState.animateScrollToItem(frontierListIndex)
                    }
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MyLocation,
                        contentDescription = "Ir a partida actual",
                        tint = Color(0xFF1A1008),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Partida #${userProgress.unlockedUpToIndex + 1}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF1A1008)
                        )
                    )
                }
            }
        }
    }

    // Modal de Partida Desbloqueada seleccionada en el Mapa
    selectedUnlockedGame?.let { (game, idx) ->
        val record = userProgress.individualGameScores[game.id]
        JourneyGameMissionDialog(
            game = game,
            gameNumber = idx + 1,
            scoreRecord = record,
            isCompleted = game.id in userProgress.completedGameIds,
            onPlayCriticalMoment = {
                selectedUnlockedGame = null
                onStartGameChallenge(game)
            },
            onStudyFullGame = {
                selectedUnlockedGame = null
                onOpenGameStudy(game)
            },
            onDismiss = { selectedUnlockedGame = null }
        )
    }

    // Aviso al pulsar sobre una partida aún bloqueada
    lockedNodeAttemptIndex?.let { lockedIdx ->
        val requiredGameNumber = userProgress.unlockedUpToIndex + 1
        AlertDialog(
            onDismissRequest = { lockedNodeAttemptIndex = null },
            containerColor = CharcoalSurface,
            icon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = GoldPrimary,
                    modifier = Modifier.size(34.dp)
                )
            },
            title = {
                Text(
                    text = "Partida #${lockedIdx + 1} Bloqueada",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = GoldLight
                    )
                )
            },
            text = {
                Text(
                    text = "En el modo Viaje vas desbloqueando las partidas 1 a 1.\n\nSupera primero la Partida #$requiredGameNumber (resolviendo su momento crítico o completando su estudio) para que tu personaje avance por el camino.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFFE0D4C4),
                        lineHeight = 20.sp
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        lockedNodeAttemptIndex = null
                        coroutineScope.launch {
                            listState.animateScrollToItem(frontierListIndex)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = Color(0xFF1B1008)
                    )
                ) {
                    Text("Ir a Partida #$requiredGameNumber", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { lockedNodeAttemptIndex = null }) {
                    Text("Cerrar", color = Color(0xFFD8C8B4))
                }
            }
        )
    }
}

@Composable
private fun PlayerJourneyStatusBar(
    userProgress: UserProgressState,
    totalGames: Int,
    onProfileClick: () -> Unit,
    onScoresClick: () -> Unit
) {
    Surface(
        color = Color(0xFF21140D),
        border = BorderStroke(0.8.dp, GoldPrimary.copy(alpha = 0.35f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            // Perfil / Botón de Registro
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onProfileClick() }
                    .padding(vertical = 2.dp)
                    .testTag("journey_profile_button")
            ) {
                Box {
                    Image(
                        painter = painterResource(id = userProgress.currentAvatar.drawableRes),
                        contentDescription = userProgress.displayUsername,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .border(2.dp, GoldPrimary, CircleShape)
                    )
                    Surface(
                        shape = CircleShape,
                        color = if (userProgress.isRegistered) EmeraldWin else GoldPrimary,
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = if (userProgress.isRegistered) Icons.Default.CheckCircle else Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = Color(0xFF120B07),
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = userProgress.displayUsername,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (!userProgress.isRegistered) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = GoldPrimary.copy(alpha = 0.25f)
                            ) {
                                Text(
                                    text = "REGISTRAR",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldLight,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 9.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }
                    }
                    Text(
                        text = "${userProgress.rank.title} • Desbloqueadas: ${userProgress.unlockedUpToIndex + 1}/$totalGames",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFFCBBBA8),
                            fontSize = 11.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Chip de Puntuación Acumulada + acceso al registro individual
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF332014),
                border = BorderStroke(1.dp, GoldPrimary),
                modifier = Modifier
                    .clickable { onScoresClick() }
                    .testTag("journey_scores_chip")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = GoldLight,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "${userProgress.totalXp} pts",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = GoldLight
                            )
                        )
                        Text(
                            text = "Ver registro",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                color = Color(0xFFD8C8B4)
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeStartingPavilion(
    userProgress: UserProgressState,
    totalGames: Int,
    bobOffsetDp: Float,
    onRegisterOrProfileClick: () -> Unit,
    onIndividualScoresClick: () -> Unit,
    onToggleCharacterAtHome: () -> Unit,
    onStartNextUnlockedClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
    ) {
        // Tarjeta del Punto de Partida "HOME"
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
            border = BorderStroke(1.5.dp, GoldPrimary),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .testTag("journey_home_node")
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.gmj_heroes_trio),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .alpha(0.14f)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xCC2C1B11),
                                    Color(0xF21B110B)
                                )
                            )
                        )
                        .padding(16.dp)
                ) {
                    // Emblema superior HOME + Personaje si se encuentra en Home
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = GoldPrimary.copy(alpha = 0.22f),
                            border = BorderStroke(2.dp, GoldLight),
                            modifier = Modifier
                                .size(54.dp)
                                .clickable { onToggleCharacterAtHome() }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Punto de partida Home",
                                    tint = GoldLight,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }

                        if (userProgress.characterAtHome) {
                            Spacer(modifier = Modifier.width(12.dp))
                            CharacterTokenBadge(
                                avatarRes = userProgress.currentAvatar.drawableRes,
                                label = userProgress.displayUsername,
                                bobOffsetDp = bobOffsetDp,
                                onClick = onRegisterOrProfileClick
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = GoldPrimary.copy(alpha = 0.2f),
                        border = BorderStroke(0.8.dp, GoldPrimary.copy(alpha = 0.6f))
                    ) {
                        Text(
                            text = "PUNTO DE PARTIDA • HOME",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GoldLight,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.2.sp
                            ),
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "El Viaje de los Grandes Maestros",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = if (userProgress.characterAtHome) {
                            "Tu personaje está en Home listo para partir. Supera cada partida para desbloquear la siguiente 1 a 1."
                        } else {
                            "Tu personaje avanza por la Partida #${userProgress.unlockedUpToIndex + 1} de $totalGames. Cada victoria suma puntos a tu usuario."
                        },
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFFD6C6B4),
                            lineHeight = 18.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = if (userProgress.isRegistered) onIndividualScoresClick else onRegisterOrProfileClick,
                            border = BorderStroke(1.dp, GoldPrimary),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = if (userProgress.isRegistered) Icons.Default.EmojiEvents else Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = GoldLight,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = if (userProgress.isRegistered) "Mis Puntos" else "Registrarse",
                                color = GoldLight,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = onStartNextUnlockedClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = Color(0xFF1A1008)
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(17.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Ir a Partida #${userProgress.unlockedUpToIndex + 1}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }
            }
        }

        // Conector dorado saliendo desde HOME hacia el primer capítulo/nodo
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
        ) {
            val cx = size.width * 0.5f
            drawLine(
                color = GoldPrimary.copy(alpha = 0.3f),
                start = Offset(cx, 0f),
                end = Offset(cx, size.height),
                strokeWidth = 14.dp.toPx(),
                cap = StrokeCap.Round
            )
            drawLine(
                color = GoldLight,
                start = Offset(cx, 0f),
                end = Offset(cx, size.height),
                strokeWidth = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
private fun EraChapterBannerNode(
    chapter: GameChapter,
    isReached: Boolean,
    topSeamXFraction: Float,
    bottomSeamXFraction: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Tramo superior hacia el banner de capítulo
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(22.dp)
        ) {
            val startX = size.width * topSeamXFraction
            val endX = size.width * 0.5f
            val path = Path().apply {
                moveTo(startX, 0f)
                cubicTo(startX, size.height * 0.5f, endX, size.height * 0.5f, endX, size.height)
            }
            drawJourneyPathSegment(path = path, isActive = isReached)
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isReached) Color(0xFF2B1A10) else Color(0xFF1A120E)
            ),
            border = BorderStroke(
                1.dp,
                if (isReached) GoldPrimary.copy(alpha = 0.7f) else Color(0xFF4A382B)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(id = chapter.dividerRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .alpha(if (isReached) 0.22f else 0.08f)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = chapter.portraitRes),
                        contentDescription = chapter.displayName,
                        contentScale = ContentScale.Crop,
                        colorFilter = if (isReached) null else ColorFilter.colorMatrix(
                            ColorMatrix().apply { setToSaturation(0f) }
                        ),
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .border(
                                1.5.dp,
                                if (isReached) GoldPrimary else Color.Gray,
                                CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = chapter.period.uppercase(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (isReached) GoldLight else Color(0xFF8D7B6E),
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        )
                        Text(
                            text = chapter.displayName,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = if (isReached) Color.White else Color(0xFF9E8E82)
                            )
                        )
                        Text(
                            text = chapter.subtitle,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFC4B4A4),
                                fontSize = 11.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        // Tramo inferior saliendo del banner hacia el nodo
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(22.dp)
        ) {
            val startX = size.width * 0.5f
            val endX = size.width * bottomSeamXFraction
            val path = Path().apply {
                moveTo(startX, 0f)
                cubicTo(startX, size.height * 0.5f, endX, size.height * 0.5f, endX, size.height)
            }
            drawJourneyPathSegment(path = path, isActive = isReached)
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawJourneyPathSegment(
    path: Path,
    isActive: Boolean
) {
    if (isActive) {
        drawPath(
            path = path,
            color = GoldPrimary.copy(alpha = 0.26f),
            style = Stroke(width = 14.dp.toPx(), cap = StrokeCap.Round)
        )
        drawPath(
            path = path,
            color = GoldLight,
            style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
        )
    } else {
        drawPath(
            path = path,
            color = Color(0xFF4B382A),
            style = Stroke(
                width = 4.dp.toPx(),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 14f), 0f)
            )
        )
    }
}

@Composable
private fun JourneySnakeGameRow(
    game: BookGame,
    gameIndex: Int,
    xFraction: Float,
    topSeamXFraction: Float,
    bottomSeamXFraction: Float,
    isLastNode: Boolean,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    isCurrentFrontier: Boolean,
    nextSegmentUnlocked: Boolean,
    showCharacterHere: Boolean,
    @DrawableRes characterAvatarRes: Int,
    characterName: String,
    scoreRecord: GameScoreRecord?,
    @DrawableRes decorRes: Int?,
    pulseScale: Float,
    bobOffsetDp: Float,
    onNodeClick: () -> Unit
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val nodeCenterOffsetDp = (screenWidthDp * (xFraction - 0.5f))
    val rowHeight = 166.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(rowHeight)
    ) {
        // 1. Sendero curvo Bézier ("SnakeCubic") en el fondo
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val midY = h * 0.5f
            val topX = w * topSeamXFraction
            val nodeX = w * xFraction
            val botX = w * bottomSeamXFraction

            val topHalf = Path().apply {
                moveTo(topX, 0f)
                cubicTo(
                    topX,
                    midY * 0.45f,
                    nodeX,
                    midY * 0.55f,
                    nodeX,
                    midY
                )
            }
            drawJourneyPathSegment(path = topHalf, isActive = isUnlocked)

            if (!isLastNode) {
                val bottomHalf = Path().apply {
                    moveTo(nodeX, midY)
                    cubicTo(
                        nodeX,
                        midY + (h - midY) * 0.45f,
                        botX,
                        midY + (h - midY) * 0.55f,
                        botX,
                        h
                    )
                }
                drawJourneyPathSegment(path = bottomHalf, isActive = nextSegmentUnlocked)
            }
        }

        // 2. Ilustración histórica decorativa de Grandmaster Journey en el margen opuesto de la curva
        if (decorRes != null) {
            val placeOnLeft = xFraction >= 0.5f
            Image(
                painter = painterResource(id = decorRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(if (placeOnLeft) Alignment.CenterStart else Alignment.CenterEnd)
                    .padding(horizontal = 18.dp)
                    .size(96.dp)
                    .alpha(if (isUnlocked) 0.72f else 0.28f)
            )
        }

        // 3. Nodo interactivo de la partida + Personaje si está en esta casilla
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = nodeCenterOffsetDp)
                .width(196.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .scale(pulseScale)
                    .clickable { onNodeClick() }
                    .testTag("journey_game_node_${gameIndex + 1}")
            ) {
                // Halo exterior brillante en la partida actual desbloqueada
                if (isCurrentFrontier) {
                    Box(
                        modifier = Modifier
                            .size(86.dp)
                            .clip(CircleShape)
                            .background(GoldPrimary.copy(alpha = 0.24f))
                    )
                }

                // Medallón principal del Gran Maestro
                val borderColor = when {
                    isCurrentFrontier -> GoldLight
                    isCompleted -> EmeraldWin
                    isUnlocked -> GoldPrimary
                    else -> Color(0xFF564438)
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF24160E))
                        .border(3.dp, borderColor, CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = game.portraitRes),
                        contentDescription = game.title,
                        contentScale = ContentScale.Crop,
                        colorFilter = if (isUnlocked) null else ColorFilter.colorMatrix(
                            ColorMatrix().apply { setToSaturation(0.1f) }
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(if (isUnlocked) 1f else 0.35f)
                    )

                    if (!isUnlocked) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xAA120B07))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Bloqueada",
                                tint = Color(0xFFCBBBA8),
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }
                }

                // Insignia de número de partida (#1, #2, #3...)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when {
                        isCompleted -> EmeraldWin
                        isCurrentFrontier -> GoldLight
                        isUnlocked -> GoldPrimary
                        else -> Color(0xFF3E2D21)
                    },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = (-6).dp, y = (-2).dp)
                ) {
                    Text(
                        text = "#${gameIndex + 1}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = if (isUnlocked) Color(0xFF160D07) else Color(0xFFB8A898),
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                // Check de partida superada
                if (isCompleted) {
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFF120B07),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = 2.dp, y = 2.dp)
                            .size(22.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Superada",
                            tint = EmeraldWin,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Ficha flotante del PERSONAJE DEL USUARIO ("TÚ") sobre la partida activa
                if (showCharacterHere) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(x = 46.dp, y = (-6).dp)
                    ) {
                        CharacterTokenBadge(
                            avatarRes = characterAvatarRes,
                            label = "TÚ",
                            bobOffsetDp = bobOffsetDp,
                            compact = true,
                            onClick = onNodeClick
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            // Cartela inferior con nombre de maestros, estrellas y puntuación individual
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isCurrentFrontier) Color(0xEE332013) else Color(0xDD1E130D),
                border = BorderStroke(
                    0.8.dp,
                    when {
                        isCurrentFrontier -> GoldLight
                        isCompleted -> EmeraldWin.copy(alpha = 0.6f)
                        isUnlocked -> GoldPrimary.copy(alpha = 0.4f)
                        else -> Color(0xFF3E2D21)
                    }
                ),
                modifier = Modifier.clickable { onNodeClick() }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${shortPlayerSurname(game.whitePlayer)} vs ${shortPlayerSurname(game.blackPlayer)}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isUnlocked) Color.White else Color(0xFF8C7B6E),
                            fontSize = 11.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (!isUnlocked) {
                            Text(
                                text = "Bloqueada • ${game.year}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFF7B6B5E),
                                    fontSize = 9.sp
                                )
                            )
                        } else if (scoreRecord != null && scoreRecord.totalScore > 0) {
                            repeat(3) { s ->
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = if (s < scoreRecord.stars) AmberStar else Color.Gray.copy(alpha = 0.35f),
                                    modifier = Modifier.size(11.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${scoreRecord.totalScore} pts",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GoldLight,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 10.sp
                                )
                            )
                        } else {
                            Text(
                                text = if (isCurrentFrontier) "¡JUGAR AHORA! • ${game.year}" else "Disponible • ${game.year}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = if (isCurrentFrontier) GoldLight else Color(0xFFCBBBA8),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.5.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterTokenBadge(
    @DrawableRes avatarRes: Int,
    label: String,
    bobOffsetDp: Float,
    compact: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .offset(y = bobOffsetDp.dp)
            .clickable { onClick() }
            .testTag("journey_character_token")
    ) {
        Surface(
            shape = CircleShape,
            color = GoldLight,
            shadowElevation = 8.dp,
            modifier = Modifier.size(if (compact) 42.dp else 52.dp)
        ) {
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = "Personaje del jugador",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(2.5.dp)
                    .clip(CircleShape)
            )
        }
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = GoldPrimary,
            border = BorderStroke(0.8.dp, GoldLight),
            modifier = Modifier.offset(y = (-4).dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1A1008),
                    fontSize = 9.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
            )
        }
    }
}

@Composable
private fun JourneyGameMissionDialog(
    game: BookGame,
    gameNumber: Int,
    scoreRecord: GameScoreRecord?,
    isCompleted: Boolean,
    onPlayCriticalMoment: () -> Unit,
    onStudyFullGame: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CharcoalDark,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = game.portraitRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .border(2.dp, GoldPrimary, CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "PARADA #$gameNumber • ${game.year}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GoldLight,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        )
                    )
                    Text(
                        text = "${game.whitePlayer} vs ${game.blackPlayer}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    )
                }
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFE6D7C3)
                    )
                )
                Text(
                    text = "Tema: ${game.strategicTheme}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFC4B4A4)
                    )
                )

                // Tarjeta de puntuación individual guardada para esta partida
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = CharcoalSurface,
                    border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.45f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Tu Puntuación Individual:",
                                style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFFD8C8B4))
                            )
                            Text(
                                text = "${scoreRecord?.totalScore ?: 0} pts",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = GoldLight
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        HorizontalDivider(color = GoldPrimary.copy(alpha = 0.18f))
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Estudio: ${scoreRecord?.studyScore ?: 0}/100 pts",
                                style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFC4B4A4))
                            )
                            Text(
                                text = "Reto crítico: ${scoreRecord?.challengeScore ?: 0} pts",
                                style = MaterialTheme.typography.labelSmall.copy(color = EmeraldWin)
                            )
                        }
                    }
                }

                if (!isCompleted) {
                    Text(
                        text = "💡 Supera el Momento Crítico o completa el estudio hasta el final para desbloquear la Partida #${gameNumber + 1}.",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GoldLight,
                            lineHeight = 16.sp
                        )
                    )
                }
            }
        },
        confirmButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (game.criticalMoments.isNotEmpty()) {
                    Button(
                        onClick = onPlayCriticalMoment,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldPrimary,
                            contentColor = Color(0xFF1B1008)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Jugar Momento Crítico (+300 pts)", fontWeight = FontWeight.ExtraBold)
                    }
                }

                OutlinedButton(
                    onClick = onStudyFullGame,
                    border = BorderStroke(1.dp, GoldPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        tint = GoldLight,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Estudiar Partida Completa (+100 pts)", color = GoldLight, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

private fun shortPlayerSurname(fullName: String): String {
    val clean = fullName.trim()
    val parts = clean.split(" ")
    return if (parts.size <= 1) clean else parts.last()
}
