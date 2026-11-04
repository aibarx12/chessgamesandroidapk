package com.example.chess.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.data.BookDatabase
import com.example.chess.data.GameChapter
import com.example.chess.data.portraitRes
import com.example.chess.gamification.GamificationRepository
import com.example.chess.gamification.HistoricalArtifact
import com.example.chess.gamification.UserProgressState
import com.example.chess.gamification.UserRank
import com.example.ui.theme.AmberStar
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.CharcoalVariant
import com.example.ui.theme.EmeraldWin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary

@Composable
fun MuseumProgressScreen(
    userProgress: UserProgressState,
    onOpenRegistrationDialog: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedArtifactForDetail by remember { mutableStateOf<HistoricalArtifact?>(null) }
    val totalGames = BookDatabase.getAllGames().size
    val totalChallenges = BookDatabase.getAllChallenges().size
    val totalStars = userProgress.challengeStars.values.sum()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // =====================================================================
        // 1. TARJETA DE PERFIL Y RANGO DE GRAN MAESTRO
        // =====================================================================
        item {
            GrandmasterRankCard(
                userProgress = userProgress,
                onOpenProfile = { onOpenRegistrationDialog(0) },
                onOpenIndividualScores = { onOpenRegistrationDialog(1) }
            )
        }

        // =====================================================================
        // 2. RESUMEN DE ESTADÍSTICAS RÁPIDAS
        // =====================================================================
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatCard(
                    title = "Partidas",
                    value = "${userProgress.completedGamesCount}/$totalGames",
                    subtitle = "Dominadas",
                    icon = Icons.Default.HistoryEdu,
                    color = GoldPrimary,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Retos",
                    value = "${userProgress.solvedChallengesCount}/$totalChallenges",
                    subtitle = "$totalStars ★ ganadas",
                    icon = Icons.Default.Star,
                    color = AmberStar,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Reliquias",
                    value = "${userProgress.unlockedArtifactIds.size}/${GamificationRepository.ALL_ARTIFACTS.size}",
                    subtitle = "En museo",
                    icon = Icons.Default.MilitaryTech,
                    color = EmeraldWin,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // =====================================================================
        // 3. VITRINA DE ARTEFACTOS Y RELIQUIAS HISTÓRICAS
        // =====================================================================
        item {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MilitaryTech,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Vitrina de Reliquias Históricas",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }

                Text(
                    text = "Colecciona artefactos auténticos de la historia del ajedrez al resolver momentos críticos y completar partidas.",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(GamificationRepository.ALL_ARTIFACTS) { artifact ->
                        val isUnlocked = artifact.id in userProgress.unlockedArtifactIds
                        ArtifactMuseumItem(
                            artifact = artifact,
                            isUnlocked = isUnlocked,
                            onClick = { selectedArtifactForDetail = artifact }
                        )
                    }
                }
            }
        }

        // =====================================================================
        // 4. PROGRESO POR ERAS Y GRANDES MAESTROS
        // =====================================================================
        item {
            Column {
                Text(
                    text = "Progreso por Eras de Maestros",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                GameChapter.entries.forEach { chapter ->
                    val chapterGames = BookDatabase.getGamesByChapter(chapter)
                    val completedInChapter = chapterGames.count { it.id in userProgress.completedGameIds }
                    val progressFraction = if (chapterGames.isNotEmpty()) completedInChapter.toFloat() / chapterGames.size else 0f

                    EraProgressCard(
                        chapter = chapter,
                        completedCount = completedInChapter,
                        totalCount = chapterGames.size,
                        progressFraction = progressFraction
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // =====================================================================
        // 5. SALA DE LOGROS
        // =====================================================================
        item {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Sala de Logros e Insignias",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }

                GamificationRepository.ALL_ACHIEVEMENTS.forEach { achievement ->
                    val isUnlocked = achievement.id in userProgress.unlockedAchievementIds
                    AchievementRowCard(achievement = achievement, isUnlocked = isUnlocked)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // =====================================================================
        // 6. HISTORIAL DE ACTIVIDAD RECIENTE
        // =====================================================================
        if (userProgress.recentLogs.isNotEmpty()) {
            item {
                Column {
                    Text(
                        text = "Historial de Actividad Reciente",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    userProgress.recentLogs.take(6).forEach { log ->
                        RecentActivityItem(log = log)
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }

    // Modal de inspección de artefacto
    selectedArtifactForDetail?.let { artifact ->
        val isUnlocked = artifact.id in userProgress.unlockedArtifactIds
        ArtifactDetailDialog(
            artifact = artifact,
            isUnlocked = isUnlocked,
            onDismiss = { selectedArtifactForDetail = null }
        )
    }
}

@Composable
private fun GrandmasterRankCard(
    userProgress: UserProgressState,
    onOpenProfile: () -> Unit,
    onOpenIndividualScores: () -> Unit
) {
    val rank = userProgress.rank
    val currentXp = userProgress.totalXp
    val xpInLevel = (currentXp - rank.minXp).coerceAtLeast(0)
    val span = (rank.maxXp - rank.minXp).coerceAtLeast(1)
    val progressFraction = (xpInLevel.toFloat() / span).coerceIn(0f, 1f)

    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.6f)),
        colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF332015),
                            Color(0xFF1E130D)
                        )
                    )
                )
                .padding(18.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = userProgress.currentAvatar.drawableRes),
                            contentDescription = userProgress.displayUsername,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary.copy(alpha = 0.2f))
                                .padding(2.dp)
                                .clip(CircleShape)
                                .clickable { onOpenProfile() }
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = GoldPrimary.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "${userProgress.displayUsername.uppercase()} • NIVEL ${rank.level}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldLight,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 0.8.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = rank.title,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Text(
                                text = rank.subtitle,
                                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFD4C8B8))
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Barra de Puntuación Acumulada
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Puntuación Total del Usuario",
                        style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFFD4C8B8))
                    )
                    Text(
                        text = "$currentXp pts ${if (rank.level < 8) "/ ${rank.maxXp + 1} pts" else ""}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = GoldLight,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                LinearProgressIndicator(
                    progress = { progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = GoldPrimary,
                    trackColor = Color(0xFF261810)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onOpenIndividualScores,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldPrimary,
                            contentColor = Color(0xFF1B1008)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1.2f)
                    ) {
                        Text(
                            text = "Puntuaciones Individuales (${userProgress.individualGameScores.size})",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Button(
                        onClick = onOpenProfile,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3E281B),
                            contentColor = GoldLight
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(0.8f)
                    ) {
                        Text(
                            text = if (userProgress.isRegistered) "Cuenta" else "Registrarse",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
        border = BorderStroke(0.5.dp, color.copy(alpha = 0.4f)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ArtifactMuseumItem(
    artifact: HistoricalArtifact,
    isUnlocked: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) CharcoalSurface else Color(0xFF1A120D)
        ),
        border = BorderStroke(
            1.dp,
            if (isUnlocked) GoldPrimary.copy(alpha = 0.7f) else Color.DarkGray.copy(alpha = 0.3f)
        ),
        modifier = Modifier
            .width(160.dp)
            .clickable { onClick() }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp)
                    .background(Color(0xFF0F0804)),
                contentAlignment = Alignment.Center
            ) {
                if (isUnlocked) {
                    Image(
                        painter = painterResource(id = artifact.drawableRes),
                        contentDescription = artifact.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Surface(
                        shape = RoundedCornerShape(bottomStart = 8.dp),
                        color = GoldPrimary,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Text(
                            text = "DESBLOQUEADO",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xCC000000)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Bloqueado",
                                tint = Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "BLOQUEADO",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.LightGray,
                                    fontSize = 9.sp,
                                    letterSpacing = 1.sp
                                )
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = artifact.year,
                    style = MaterialTheme.typography.labelSmall.copy(color = GoldLight)
                )
                Text(
                    text = artifact.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isUnlocked) Color.White else Color.Gray
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = artifact.era,
                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun EraProgressCard(
    chapter: GameChapter,
    completedCount: Int,
    totalCount: Int,
    progressFraction: Float
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
        border = BorderStroke(0.5.dp, Color(0xFF452D1F)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = chapter.portraitRes),
                contentDescription = chapter.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = chapter.displayName,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "$completedCount/$totalCount",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldLight
                        )
                    )
                }

                Text(
                    text = chapter.period,
                    style = MaterialTheme.typography.labelSmall.copy(color = GoldDark)
                )

                Spacer(modifier = Modifier.height(6.dp))

                LinearProgressIndicator(
                    progress = { progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = GoldPrimary,
                    trackColor = Color(0xFF2A1C14)
                )
            }
        }
    }
}

@Composable
private fun AchievementRowCard(
    achievement: com.example.chess.gamification.Achievement,
    isUnlocked: Boolean
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) CharcoalSurface else Color(0xFF17100B)
        ),
        border = BorderStroke(
            0.5.dp,
            if (isUnlocked) GoldPrimary.copy(alpha = 0.5f) else Color(0xFF332219)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = if (isUnlocked) GoldPrimary.copy(alpha = 0.2f) else Color(0xFF22160F),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = achievement.iconEmoji, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = achievement.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isUnlocked) Color.White else Color.Gray
                    )
                )
                Text(
                    text = achievement.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (isUnlocked) Color(0xFFC7CBD5) else Color.DarkGray
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (isUnlocked) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Conseguido",
                    tint = EmeraldWin,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0xFF2B1C14)
                ) {
                    Text(
                        text = "+${achievement.xpReward} XP",
                        style = MaterialTheme.typography.labelSmall.copy(color = GoldLight),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentActivityItem(log: com.example.chess.gamification.ProgressLogItem) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = CharcoalSurface,
        border = BorderStroke(0.5.dp, Color(0xFF38261B)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = log.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = log.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            if (log.xpDelta > 0) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = GoldPrimary.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "+${log.xpDelta} XP",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GoldLight,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ArtifactDetailDialog(
    artifact: HistoricalArtifact,
    isUnlocked: Boolean,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = artifact.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = GoldLight
                )
            )
        },
        text = {
            Column {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, GoldPrimary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    Image(
                        painter = painterResource(id = artifact.drawableRes),
                        contentDescription = artifact.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Era: ${artifact.era}",
                        style = MaterialTheme.typography.labelMedium.copy(color = GoldDark)
                    )
                    Text(
                        text = artifact.year,
                        style = MaterialTheme.typography.labelMedium.copy(color = GoldLight)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = artifact.description,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (isUnlocked) EmeraldWin.copy(alpha = 0.15f) else Color(0xFF332014),
                    border = BorderStroke(
                        0.5.dp,
                        if (isUnlocked) EmeraldWin else GoldDark
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isUnlocked) "✓ Obtenido para tu Museo Histórico" else "Condición: ${artifact.unlockConditionText}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (isUnlocked) EmeraldWin else GoldLight,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
            ) {
                Text("Cerrar", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        },
        containerColor = CharcoalDark,
        shape = RoundedCornerShape(16.dp)
    )
}
