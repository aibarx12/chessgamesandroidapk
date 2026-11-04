package com.example.chess.gamification

import androidx.annotation.DrawableRes
import com.example.R

/**
 * Avatares de personaje seleccionables para recorrer el Mapa de Viaje desde HOME.
 */
enum class PlayerAvatar(
    val id: String,
    val displayName: String,
    val roleSubtitle: String,
    @param:DrawableRes val drawableRes: Int
) {
    GOUDA_TRAVELER(
        id = "gouda_traveler",
        displayName = "Viajero del Club",
        roleSubtitle = "Explorador clásico de Grandmaster Journey",
        drawableRes = R.drawable.avatar_gouda
    ),
    PAUL_MORPHY(
        id = "paul_morphy",
        displayName = "Paul Morphy",
        roleSubtitle = "El orgullo y la tristeza del ajedrez",
        drawableRes = R.drawable.portrait_paul_morphy
    ),
    ADOLF_ANDERSSEN(
        id = "adolf_anderssen",
        displayName = "Adolf Anderssen",
        roleSubtitle = "Maestro del sacrificio romántico",
        drawableRes = R.drawable.portrait_adolf_anderssen
    ),
    WILHELM_STEINITZ(
        id = "wilhelm_steinitz",
        displayName = "Wilhelm Steinitz",
        roleSubtitle = "Padre de la estrategia moderna",
        drawableRes = R.drawable.portrait_wilhelm_steinitz
    ),
    EMANUEL_LASKER(
        id = "emanuel_lasker",
        displayName = "Emanuel Lasker",
        roleSubtitle = "27 años en el trono mundial",
        drawableRes = R.drawable.portrait_emanuel_lasker
    ),
    CAPABLANCA(
        id = "capablanca",
        displayName = "J. R. Capablanca",
        roleSubtitle = "La máquina del ajedrez cristalino",
        drawableRes = R.drawable.portrait_jose_raul_capablanca
    ),
    ALEKHINE(
        id = "alekhine",
        displayName = "Alexander Alekhine",
        roleSubtitle = "Artista del ataque dinámico",
        drawableRes = R.drawable.portrait_alexander_alekhine
    ),
    MIKHAIL_TAL(
        id = "mikhail_tal",
        displayName = "Mikhail Tal",
        roleSubtitle = "El Mago de Riga",
        drawableRes = R.drawable.portrait_mikhail_tal
    );

    companion object {
        fun fromId(id: String?): PlayerAvatar {
            return entries.find { it.id == id } ?: GOUDA_TRAVELER
        }
    }
}

/**
 * Cuenta de usuario registrada localmente para guardar progreso y puntuaciones individuales.
 */
data class RegisteredUser(
    val id: String,
    val username: String,
    val emailOrTag: String = "",
    val pinHash: String = "",
    val avatar: PlayerAvatar = PlayerAvatar.GOUDA_TRAVELER,
    val createdAt: Long = System.currentTimeMillis(),
    val totalXp: Int = 0,
    val unlockedUpToIndex: Int = 0,
    val completedGamesCount: Int = 0
) {
    val hasPin: Boolean
        get() = pinHash.isNotEmpty()
}

/**
 * Registro de puntuación individual guardada para cada partida.
 */
data class GameScoreRecord(
    val gameId: String,
    val gameNumber: Int,
    val gameTitle: String,
    val studyScore: Int = 0,       // Hasta 100 pts por reproducir y estudiar toda la partida
    val challengeScore: Int = 0,   // Puntos obtenidos en los momentos críticos de esta partida
    val stars: Int = 0,            // 0..3 estrellas máximas logradas en sus retos
    val attempts: Int = 0,         // Intentos realizados
    val isCompleted: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis()
) {
    val totalScore: Int
        get() = studyScore + challengeScore
}

/**
 * Rangos de Maestría y Títulos honoríficos basados en la puntuación acumulada del usuario.
 */
enum class UserRank(
    val level: Int,
    val title: String,
    val subtitle: String,
    val minXp: Int,
    val maxXp: Int,
    val iconName: String
) {
    NOVICE(1, "Aficionado de Salón", "Partiendo desde Home hacia los clásicos", 0, 299, "♟"),
    ROMANTIC_STUDENT(2, "Estudiante Romántico", "Inspirado por Anderssen y Morphy", 300, 799, "⚔"),
    STEINITZ_STRATEGIST(3, "Estratega de Steinitz", "Dominando los fundamentos posicionales", 800, 1599, "♙"),
    LASKER_FIGHTER(4, "Luchador de Lasker", "Tenacidad psicológica y lucha práctica", 1600, 2799, "♚"),
    CAPABLANCA_VIRTUOSO(5, "Virtuoso de Capablanca", "Técnica cristalina y armonía de finales", 2800, 4499, "♛"),
    ALEKHINE_TACTICIAN(6, "Dinamitero de Alekhine", "Iniciativa arrolladora y juego concreto", 4500, 6999, "⚡"),
    PREDECESSOR_MASTER(7, "Maestro de Predecesores", "Conocedor de las joyas del ajedrez histórico", 7000, 9999, "👑"),
    LEGENDARY_GRANDMASTER(8, "Gran Maestro de Leyenda", "Máxima erudición ajedrecística", 10000, 999999, "⭐");

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
    @param:DrawableRes val drawableRes: Int,
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
    GAME_UNLOCKED,
    USER_REGISTERED,
    ARTIFACT_UNLOCKED,
    ACHIEVEMENT_UNLOCKED,
    LEVEL_UP
}

/**
 * Estado general del progreso del jugador activo y sus puntuaciones individuales.
 */
data class UserProgressState(
    val activeUser: RegisteredUser? = null,
    val guestAvatar: PlayerAvatar = PlayerAvatar.GOUDA_TRAVELER,
    val registeredUsers: List<RegisteredUser> = emptyList(),
    val totalXp: Int = 0,
    val rank: UserRank = UserRank.NOVICE,
    val unlockedUpToIndex: Int = 0, // Índice 0 = Partida #1 desbloqueada; se incrementa de 1 en 1
    val characterAtHome: Boolean = true, // true cuando el personaje está en el nodo inicial HOME
    val solvedChallengesCount: Int = 0,
    val completedGamesCount: Int = 0,
    val challengeStars: Map<String, Int> = emptyMap(), // challengeId -> 1..3
    val completedGameIds: Set<String> = emptySet(),
    val gameMaxPly: Map<String, Int> = emptyMap(),
    val individualGameScores: Map<String, GameScoreRecord> = emptyMap(), // gameId -> puntuación individual
    val unlockedArtifactIds: Set<String> = setOf("artifact_lewis_chessmen"),
    val unlockedAchievementIds: Set<String> = emptySet(),
    val recentLogs: List<ProgressLogItem> = emptyList()
) {
    val currentAvatar: PlayerAvatar
        get() = activeUser?.avatar ?: guestAvatar

    val displayUsername: String
        get() = activeUser?.username ?: "Viajero Invitado"

    val isRegistered: Boolean
        get() = activeUser != null
}
