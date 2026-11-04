package com.example.chess.gamification

import android.content.Context
import android.content.SharedPreferences
import com.example.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

object GamificationRepository {

    private const val PREFS_NAME = "grandmaster_progress_prefs"
    private const val KEY_TOTAL_XP = "key_total_xp"
    private const val KEY_SOLVED_CHALLENGES = "key_solved_challenges"
    private const val KEY_CHALLENGE_STARS_PREFIX = "star_"
    private const val KEY_COMPLETED_GAMES = "key_completed_games"
    private const val KEY_GAME_MAX_PLY_PREFIX = "ply_"
    private const val KEY_UNLOCKED_ARTIFACTS = "key_unlocked_artifacts"
    private const val KEY_UNLOCKED_ACHIEVEMENTS = "key_unlocked_achievements"

    val ALL_ARTIFACTS = listOf(
        HistoricalArtifact(
            id = "artifact_lewis_chessmen",
            name = "Piezas de la Isla de Lewis",
            era = "Época Medieval",
            year = "c. 1150 d.C.",
            description = "Figuras esculpidas en marfil de morsa descubiertas en la bahía de Uig (Escocia). Son las piezas de ajedrez más famosas del mundo medieval.",
            drawableRes = R.drawable.artifact_lewis_chessmen,
            unlockConditionText = "Disponible desde el inicio",
            requiredXp = 0
        ),
        HistoricalArtifact(
            id = "artifact_cafe_regence",
            name = "Mesa del Café de la Régence",
            era = "Era Romántica",
            year = "París, 1851",
            description = "El legendario epicentro del ajedrez parisino donde Philidor, Morphy, Anderssen y Saint-Amant libraron combates memorables.",
            drawableRes = R.drawable.artifact_cafe_regence,
            unlockConditionText = "Completa 2 partidas de la Era Romántica",
            requiredCompletedGames = 2
        ),
        HistoricalArtifact(
            id = "artifact_immortal_scoresheet",
            name = "Planilla de la Partida Inmortal",
            era = "Era Romántica",
            year = "Londres, 1851",
            description = "Registro de la partida entre Adolf Anderssen y Lionel Kieseritzky, donde el blanco sacrificó alfil, dos torres y la dama para dar jaque mate.",
            drawableRes = R.drawable.artifact_immortal_scoresheet,
            unlockConditionText = "Resuelve el momento crítico de la Partida Inmortal",
            requiredXp = 150
        ),
        HistoricalArtifact(
            id = "artifact_staunton_original",
            name = "Juego Staunton Original de Jaques",
            era = "Estandarización Histórica",
            year = "1849",
            description = "Diseñado por Nathaniel Cooke y promovido por Howard Staunton; el estándar universal que transformó la estética del ajedrez moderno.",
            drawableRes = R.drawable.artifact_staunton_original,
            unlockConditionText = "Alcanza 400 puntos de Experiencia (XP)",
            requiredXp = 400
        ),
        HistoricalArtifact(
            id = "artifact_fattorini_clock",
            name = "Primer Reloj Mecánico Fattorini",
            era = "Era de Steinitz",
            year = "Londres, 1883",
            description = "El primer reloj de péndulo oscilante patentado por Thomas Fattorini para regular el tiempo en los torneos profesionales de maestros.",
            drawableRes = R.drawable.artifact_fattorini_clock,
            unlockConditionText = "Completa 4 partidas de Wilhelm Steinitz",
            requiredCompletedGames = 4
        ),
        HistoricalArtifact(
            id = "artifact_hamilton_cup",
            name = "Copa Mundial Hamilton-Russell",
            era = "Era de Capablanca y Olimpiadas",
            year = "Londres, 1927",
            description = "Trofeo perpetuo donado por Frederick Hamilton-Russell disputado por los mejores equipos del planeta en las Olimpiadas de Ajedrez de la FIDE.",
            drawableRes = R.drawable.artifact_hamilton_cup,
            unlockConditionText = "Completa 6 partidas históricas",
            requiredCompletedGames = 6
        ),
        HistoricalArtifact(
            id = "artifact_alekhine_box",
            name = "Cofre Tallado a Mano de Alekhine",
            era = "Era de Alekhine",
            year = "1927",
            description = "El estuche personal de viaje de Alexander Alekhine utilizado durante sus viajes y el Campeonato del Mundo de Buenos Aires.",
            drawableRes = R.drawable.artifact_alekhine_box,
            unlockConditionText = "Resuelve 5 momentos críticos",
            requiredXp = 800
        ),
        HistoricalArtifact(
            id = "artifact_napoleon_set",
            name = "Juego de Viaje de Napoleón",
            era = "Santa Elena",
            year = "1815",
            description = "Juego de ajedrez en madera noble utilizado por Napoleón Bonaparte durante su exilio en la isla de Santa Elena.",
            drawableRes = R.drawable.artifact_napoleon_set,
            unlockConditionText = "Alcanza 1500 XP de Maestría",
            requiredXp = 1500
        )
    )

    val ALL_ACHIEVEMENTS = listOf(
        Achievement(
            id = "ach_first_blood",
            title = "Primer Golpe Táctico",
            description = "Resuelve con éxito tu primer momento crítico histórico.",
            iconEmoji = "⚡",
            xpReward = 50
        ),
        Achievement(
            id = "ach_three_stars",
            title = "Precisión Impecable",
            description = "Resuelve un momento crítico al primer intento sin usar pistas (3 estrellas).",
            iconEmoji = "⭐",
            xpReward = 75
        ),
        Achievement(
            id = "ach_game_completed",
            title = "Estudioso de los Clásicos",
            description = "Reproduce y analiza una partida histórica completa hasta el final.",
            iconEmoji = "📖",
            xpReward = 50
        ),
        Achievement(
            id = "ach_steinitz_disciple",
            title = "Escuela Posicional",
            description = "Completa al menos 3 partidas de Wilhelm Steinitz.",
            iconEmoji = "🏛",
            xpReward = 100
        ),
        Achievement(
            id = "ach_tactical_master",
            title = "Ojo de Halcón",
            description = "Resuelve 5 momentos críticos históricos con al menos 2 estrellas.",
            iconEmoji = "🎯",
            xpReward = 150
        ),
        Achievement(
            id = "ach_ten_games",
            title = "Biblioteca Viviente",
            description = "Completa el estudio de 10 partidas de grandes maestros.",
            iconEmoji = "👑",
            xpReward = 200
        ),
        Achievement(
            id = "ach_thousand_xp",
            title = "Gran Erudito del Tablero",
            description = "Supera los 1000 puntos de Experiencia histórica.",
            iconEmoji = "🏆",
            xpReward = 250
        )
    )

    private val _progressState = MutableStateFlow(UserProgressState())
    val progressState: StateFlow<UserProgressState> = _progressState.asStateFlow()

    private var prefs: SharedPreferences? = null

    fun initialize(context: Context) {
        if (prefs != null) return
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadFromPrefs()
    }

    private fun loadFromPrefs() {
        val p = prefs ?: return
        val xp = p.getInt(KEY_TOTAL_XP, 0)
        val solvedIds = p.getStringSet(KEY_SOLVED_CHALLENGES, emptySet()) ?: emptySet()
        val completedGameIds = p.getStringSet(KEY_COMPLETED_GAMES, emptySet()) ?: emptySet()
        val unlockedArtifacts = (p.getStringSet(KEY_UNLOCKED_ARTIFACTS, setOf("artifact_lewis_chessmen")) ?: setOf("artifact_lewis_chessmen")).toMutableSet()
        val unlockedAchievements = (p.getStringSet(KEY_UNLOCKED_ACHIEVEMENTS, emptySet()) ?: emptySet()).toMutableSet()

        val starsMap = mutableMapOf<String, Int>()
        for (id in solvedIds) {
            starsMap[id] = p.getInt(KEY_CHALLENGE_STARS_PREFIX + id, 3)
        }

        val maxPlyMap = mutableMapOf<String, Int>()
        for (gameId in completedGameIds) {
            maxPlyMap[gameId] = p.getInt(KEY_GAME_MAX_PLY_PREFIX + gameId, 0)
        }

        _progressState.value = UserProgressState(
            totalXp = xp,
            rank = UserRank.fromXp(xp),
            solvedChallengesCount = solvedIds.size,
            completedGamesCount = completedGameIds.size,
            challengeStars = starsMap,
            completedGameIds = completedGameIds,
            gameMaxPly = maxPlyMap,
            unlockedArtifactIds = unlockedArtifacts,
            unlockedAchievementIds = unlockedAchievements,
            recentLogs = emptyList()
        )

        // Evaluate passive unlocks
        checkUnlocks()
    }

    fun recordChallengeResult(
        momentId: String,
        gameTitle: String,
        wrongAttempts: Int,
        hintUsed: Boolean,
        revealed: Boolean
    ): Int {
        val currentStars = _progressState.value.challengeStars[momentId] ?: 0
        if (revealed) {
            val awardedXp = if (currentStars == 0) 10 else 0
            if (awardedXp > 0) {
                addXp(awardedXp, "Solución consultada", "Momento crítico: $gameTitle", LogCategory.CHALLENGE_SOLVED)
            }
            return awardedXp
        }

        val stars = when {
            wrongAttempts == 0 && !hintUsed -> 3
            wrongAttempts <= 2 && !hintUsed -> 2
            else -> 1
        }

        val baseXp = when (stars) {
            3 -> 100
            2 -> 65
            else -> 40
        }

        // Only award XP if it improves previous performance or first time
        val alreadySolved = currentStars > 0
        val earnedXp = if (!alreadySolved) {
            baseXp
        } else if (stars > currentStars) {
            baseXp / 2
        } else {
            0
        }

        val updatedStars = _progressState.value.challengeStars.toMutableMap()
        if (stars > currentStars) {
            updatedStars[momentId] = stars
            prefs?.edit()?.putInt(KEY_CHALLENGE_STARS_PREFIX + momentId, stars)?.apply()
        }

        val updatedSolved = _progressState.value.challengeStars.keys + momentId
        prefs?.edit()?.putStringSet(KEY_SOLVED_CHALLENGES, updatedSolved)?.apply()

        _progressState.value = _progressState.value.copy(
            challengeStars = updatedStars,
            solvedChallengesCount = updatedSolved.size
        )

        if (earnedXp > 0) {
            addXp(
                earnedXp,
                "¡Momento Crítico Resuelto! (${stars}★)",
                "$gameTitle (+${earnedXp} XP)",
                LogCategory.CHALLENGE_SOLVED
            )
        }

        checkUnlocks()
        return earnedXp
    }

    fun recordGamePlyProgress(gameId: String, gameTitle: String, currentPly: Int, totalPlies: Int) {
        val currentMax = _progressState.value.gameMaxPly[gameId] ?: 0
        if (currentPly > currentMax) {
            val updatedMap = _progressState.value.gameMaxPly.toMutableMap()
            updatedMap[gameId] = currentPly
            prefs?.edit()?.putInt(KEY_GAME_MAX_PLY_PREFIX + gameId, currentPly)?.apply()
            _progressState.value = _progressState.value.copy(gameMaxPly = updatedMap)
        }

        if (totalPlies > 0 && currentPly >= totalPlies && gameId !in _progressState.value.completedGameIds) {
            val updatedCompleted = _progressState.value.completedGameIds + gameId
            prefs?.edit()?.putStringSet(KEY_COMPLETED_GAMES, updatedCompleted)?.apply()
            _progressState.value = _progressState.value.copy(
                completedGameIds = updatedCompleted,
                completedGamesCount = updatedCompleted.size
            )
            addXp(
                50,
                "Partida Histórica Completada",
                "$gameTitle dominada de principio a fin.",
                LogCategory.GAME_COMPLETED
            )
            checkUnlocks()
        }
    }

    private fun addXp(amount: Int, title: String, description: String, category: LogCategory) {
        val oldXp = _progressState.value.totalXp
        val newXp = oldXp + amount
        val oldRank = _progressState.value.rank
        val newRank = UserRank.fromXp(newXp)

        prefs?.edit()?.putInt(KEY_TOTAL_XP, newXp)?.apply()

        val logItem = ProgressLogItem(
            id = UUID.randomUUID().toString(),
            timestamp = System.currentTimeMillis(),
            title = title,
            description = description,
            xpDelta = amount,
            category = category
        )

        val updatedLogs = (listOf(logItem) + _progressState.value.recentLogs).take(25)

        _progressState.value = _progressState.value.copy(
            totalXp = newXp,
            rank = newRank,
            recentLogs = updatedLogs
        )

        if (newRank.level > oldRank.level) {
            val levelUpLog = ProgressLogItem(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis(),
                title = "¡Ascenso de Rango: ${newRank.title}!",
                description = newRank.subtitle,
                xpDelta = 0,
                category = LogCategory.LEVEL_UP
            )
            _progressState.value = _progressState.value.copy(
                recentLogs = (listOf(levelUpLog) + _progressState.value.recentLogs).take(25)
            )
        }
    }

    private fun checkUnlocks() {
        val state = _progressState.value
        val newlyUnlockedArtifacts = state.unlockedArtifactIds.toMutableSet()
        var changedArtifacts = false

        for (art in ALL_ARTIFACTS) {
            if (art.id !in newlyUnlockedArtifacts) {
                val xpOk = state.totalXp >= art.requiredXp
                val gamesOk = state.completedGamesCount >= art.requiredCompletedGames
                val specialOk = when (art.id) {
                    "artifact_immortal_scoresheet" -> state.solvedChallengesCount >= 1
                    "artifact_cafe_regence" -> state.completedGamesCount >= 2
                    "artifact_alekhine_box" -> state.solvedChallengesCount >= 4
                    else -> true
                }
                if (xpOk && gamesOk && specialOk) {
                    newlyUnlockedArtifacts.add(art.id)
                    changedArtifacts = true
                    addXp(
                        35,
                        "¡Reliquia Desbloqueada!",
                        "Has obtenido '${art.name}' para tu museo.",
                        LogCategory.ARTIFACT_UNLOCKED
                    )
                }
            }
        }

        if (changedArtifacts) {
            prefs?.edit()?.putStringSet(KEY_UNLOCKED_ARTIFACTS, newlyUnlockedArtifacts)?.apply()
            _progressState.value = _progressState.value.copy(unlockedArtifactIds = newlyUnlockedArtifacts)
        }

        val newlyUnlockedAchievements = state.unlockedAchievementIds.toMutableSet()
        var changedAchievements = false

        for (ach in ALL_ACHIEVEMENTS) {
            if (ach.id !in newlyUnlockedAchievements) {
                val shouldUnlock = when (ach.id) {
                    "ach_first_blood" -> state.solvedChallengesCount >= 1
                    "ach_three_stars" -> state.challengeStars.values.any { it >= 3 }
                    "ach_game_completed" -> state.completedGamesCount >= 1
                    "ach_steinitz_disciple" -> state.completedGamesCount >= 3
                    "ach_tactical_master" -> state.challengeStars.values.count { it >= 2 } >= 5
                    "ach_ten_games" -> state.completedGamesCount >= 10
                    "ach_thousand_xp" -> state.totalXp >= 1000
                    else -> false
                }
                if (shouldUnlock) {
                    newlyUnlockedAchievements.add(ach.id)
                    changedAchievements = true
                    addXp(
                        ach.xpReward,
                        "¡Logro Desbloqueado! ${ach.iconEmoji}",
                        "${ach.title}: ${ach.description}",
                        LogCategory.ACHIEVEMENT_UNLOCKED
                    )
                }
            }
        }

        if (changedAchievements) {
            prefs?.edit()?.putStringSet(KEY_UNLOCKED_ACHIEVEMENTS, newlyUnlockedAchievements)?.apply()
            _progressState.value = _progressState.value.copy(unlockedAchievementIds = newlyUnlockedAchievements)
        }
    }
}
