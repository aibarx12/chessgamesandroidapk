package com.example.chess.gamification

import androidx.annotation.DrawableRes
import com.example.R

/**
 * Rangos de Maestría y Títulos honoríficos basados en la experiencia histórica acumulada.
 */
enum class UserRank(
    val level: Int,
    val title: String,
    val subtitle: String,
    val minXp: Int,
    val maxXp: Int,
    val iconName: String
) {
    NOVICE(1, "Aficionado de Salón", "Iniciando el viaje por los clásicos", 0, 249, "♟"),
    ROMANTIC_STUDENT(2, "Estudiante Romántico", "Inspirado por Anderssen y Morphy", 250, 599, "⚔"),
    STEINITZ_STRATEGIST(3, "Estratega de Steinitz", "Dominando los fundamentos posicionales", 600, 1199, "♙"),
    LASKER_FIGHTER(4, "Luchador de Lasker", "Tenacidad psicológica y lucha práctica", 1200, 1999, "♚"),
    CAPABLANCA_VIRTUOSO(5, "Virtuoso de Capablanca", "Técnica cristalina y armonía de finales", 2000, 3199, "♛"),
    ALEKHINE_TACTICIAN(6, "Dinamitero de Alekhine", "Iniciativa arrolladora y juego concreto", 3200, 4799, "⚡"),
    PREDECESSOR_MASTER(7, "Maestro de Predecesores", "Conocedor de las joyas del ajedrez histórico", 4800, 6999, "👑"),
    LEGENDARY_GRANDMASTER(8, "Gran Maestro de Leyenda", "Máxima erudición ajedrecística", 7000, 99999, "⭐");

    companion object {
        fun fromXp(xp: Int): UserRank {
            return entries.lastOrNull { xp >= it.minXp } ?: NOVICE
        }
    }
}

/**
 * Reliquias y artefactos históricos del ajedrez desbloqueables mediante progreso y retos.
 */
data class HistoricalArtifact(
    val id: String,
    val name: String,
    val era: String,
    val year: String,
    val description: String,
    @DrawableRes val drawableRes: Int,
    val unlockConditionText: String,
    val requiredXp: Int = 0,
    val requiredCompletedGames: Int = 0
)

/**
 * Logros / Insignias desbloqueables.
 */
data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val iconEmoji: String,
    val xpReward: Int,
    val isUnlocked: Boolean = false
)

/**
 * Registro de una acción relevante en el historial del usuario.
 */
data class ProgressLogItem(
    val id: String,
    val timestamp: Long,
    val title: String,
    val description: String,
    val xpDelta: Int,
    val category: LogCategory
)

enum class LogCategory {
    CHALLENGE_SOLVED,
    GAME_COMPLETED,
    ARTIFACT_UNLOCKED,
    ACHIEVEMENT_UNLOCKED,
    LEVEL_UP
}

/**
 * Estado general del progreso del jugador.
 */
data class UserProgressState(
    val totalXp: Int = 0,
    val rank: UserRank = UserRank.NOVICE,
    val solvedChallengesCount: Int = 0,
    val completedGamesCount: Int = 0,
    val challengeStars: Map<String, Int> = emptyMap(), // challengeId -> 1..3
    val completedGameIds: Set<String> = emptySet(),
    val gameMaxPly: Map<String, Int> = emptyMap(),
    val unlockedArtifactIds: Set<String> = emptySet(),
    val unlockedAchievementIds: Set<String> = emptySet(),
    val recentLogs: List<ProgressLogItem> = emptyList()
)
