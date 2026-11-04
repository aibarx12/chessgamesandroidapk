package com.example.chess.gamification

import android.content.Context
import android.content.SharedPreferences
import com.example.R
import com.example.chess.data.BookDatabase
import com.example.chess.data.BookGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject
import java.security.MessageDigest
import java.util.UUID

sealed class AuthResult {
    data class Success(val user: RegisteredUser) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

object GamificationRepository {

    private const val PREFS_NAME = "grandmaster_progress_prefs"

    // Global keys (account registry)
    private const val KEY_USERS_JSON = "global_registered_users_json"
    private const val KEY_ACTIVE_USER_ID = "global_active_user_id"

    // Per-namespace keys (prefixed by ns, e.g. "guest_" or "u_<id>_")
    private const val SUFFIX_TOTAL_XP = "total_xp"
    private const val SUFFIX_UNLOCKED_INDEX = "unlocked_up_to_index"
    private const val SUFFIX_CHAR_AT_HOME = "char_at_home"
    private const val SUFFIX_GUEST_AVATAR = "guest_avatar"
    private const val SUFFIX_SOLVED_CHALLENGES = "solved_challenges"
    private const val SUFFIX_REVEALED_CHALLENGES = "revealed_challenges"
    private const val SUFFIX_CHALLENGE_STARS_PREFIX = "star_"
    private const val SUFFIX_COMPLETED_GAMES = "completed_games"
    private const val SUFFIX_GAME_MAX_PLY_PREFIX = "ply_"
    private const val SUFFIX_SCORES_JSON = "individual_scores_json"
    private const val SUFFIX_LOGS_JSON = "recent_logs_json"
    private const val SUFFIX_UNLOCKED_ARTIFACTS = "unlocked_artifacts"
    private const val SUFFIX_UNLOCKED_ACHIEVEMENTS = "unlocked_achievements"

    val ALL_ARTIFACTS = listOf(
        HistoricalArtifact(
            id = "artifact_lewis_chessmen",
            name = "Piezas de la Isla de Lewis",
            era = "Época Medieval",
            year = "c. 1150 d.C.",
            description = "Figuras esculpidas en marfil de morsa descubiertas en la bahía de Uig (Escocia). Son las piezas de ajedrez más famosas del mundo medieval.",
            drawableRes = R.drawable.artifact_lewis_chessmen,
            unlockConditionText = "Disponible desde el inicio en Home",
            requiredXp = 0
        ),
        HistoricalArtifact(
            id = "artifact_cafe_regence",
            name = "Mesa del Café de la Régence",
            era = "Era Romántica",
            year = "París, 1851",
            description = "El legendario epicentro del ajedrez parisino donde Philidor, Morphy, Anderssen y Saint-Amant libraron combates memorables.",
            drawableRes = R.drawable.artifact_cafe_regence,
            unlockConditionText = "Supera 2 partidas en el Viaje",
            requiredCompletedGames = 2
        ),
        HistoricalArtifact(
            id = "artifact_immortal_scoresheet",
            name = "Planilla de la Partida Inmortal",
            era = "Era Romántica",
            year = "Londres, 1851",
            description = "Registro de la partida entre Adolf Anderssen y Lionel Kieseritzky, donde el blanco sacrificó alfil, dos torres y la dama para dar jaque mate.",
            drawableRes = R.drawable.artifact_immortal_scoresheet,
            unlockConditionText = "Resuelve 1 momento crítico y suma 250 pts",
            requiredXp = 250
        ),
        HistoricalArtifact(
            id = "artifact_staunton_original",
            name = "Juego Staunton Original de Jaques",
            era = "Estandarización Histórica",
            year = "1849",
            description = "Diseñado por Nathaniel Cooke y promovido por Howard Staunton; el estándar universal que transformó la estética del ajedrez moderno.",
            drawableRes = R.drawable.artifact_staunton_original,
            unlockConditionText = "Alcanza 600 puntos en tu usuario",
            requiredXp = 600
        ),
        HistoricalArtifact(
            id = "artifact_fattorini_clock",
            name = "Primer Reloj Mecánico Fattorini",
            era = "Era de Steinitz",
            year = "Londres, 1883",
            description = "El primer reloj de péndulo oscilante patentado por Thomas Fattorini para regular el tiempo en los torneos profesionales de maestros.",
            drawableRes = R.drawable.artifact_fattorini_clock,
            unlockConditionText = "Desbloquea y supera 4 partidas",
            requiredCompletedGames = 4
        ),
        HistoricalArtifact(
            id = "artifact_hamilton_cup",
            name = "Copa Mundial Hamilton-Russell",
            era = "Era de Capablanca y Olimpiadas",
            year = "Londres, 1927",
            description = "Trofeo perpetuo donado por Frederick Hamilton-Russell disputado por los mejores equipos del planeta en las Olimpiadas de Ajedrez de la FIDE.",
            drawableRes = R.drawable.artifact_hamilton_cup,
            unlockConditionText = "Supera 6 partidas históricas",
            requiredCompletedGames = 6
        ),
        HistoricalArtifact(
            id = "artifact_alekhine_box",
            name = "Cofre Tallado a Mano de Alekhine",
            era = "Era de Alekhine",
            year = "1927",
            description = "El estuche personal de viaje de Alexander Alekhine utilizado durante sus viajes y el Campeonato del Mundo de Buenos Aires.",
            drawableRes = R.drawable.artifact_alekhine_box,
            unlockConditionText = "Resuelve 4 momentos críticos y alcanza 1200 pts",
            requiredXp = 1200
        ),
        HistoricalArtifact(
            id = "artifact_napoleon_set",
            name = "Juego de Viaje de Napoleón",
            era = "Santa Elena",
            year = "1815",
            description = "Juego de ajedrez en madera noble utilizado por Napoleón Bonaparte durante su exilio en la isla de Santa Elena.",
            drawableRes = R.drawable.artifact_napoleon_set,
            unlockConditionText = "Alcanza 2200 puntos de Maestría",
            requiredXp = 2200
        )
    )

    val ALL_ACHIEVEMENTS = listOf(
        Achievement(
            id = "ach_first_blood",
            title = "Primer Paso desde Home",
            description = "Resuelve con éxito tu primer momento crítico y desbloquea la siguiente partida.",
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
            title = "Camino en Marcha",
            description = "Supera y desbloquea al menos 3 partidas consecutivas en el Viaje.",
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
            description = "Supera 10 partidas históricas en tu recorrido.",
            iconEmoji = "👑",
            xpReward = 200
        ),
        Achievement(
            id = "ach_thousand_xp",
            title = "Gran Erudito del Tablero",
            description = "Supera los 1500 puntos guardados en tu perfil.",
            iconEmoji = "🏆",
            xpReward = 250
        )
    )

    private val _progressState = MutableStateFlow(UserProgressState())
    val progressState: StateFlow<UserProgressState> = _progressState.asStateFlow()

    private var prefs: SharedPreferences? = null
    private var revealedChallengeIdsInMemory: MutableSet<String> = mutableSetOf()

    fun initialize(context: Context) {
        if (prefs != null) return
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadActiveProfileFromPrefs()
    }

    /**
     * Reinicia el estado en memoria y preferencias (útil para tests unitarios).
     */
    fun resetAllDataForTesting(context: Context? = null) {
        if (context != null) {
            prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }
        prefs?.edit()?.clear()?.commit()
        revealedChallengeIdsInMemory.clear()
        _progressState.value = UserProgressState()
    }

    private fun currentNamespace(activeUserId: String? = _progressState.value.activeUser?.id): String {
        return if (activeUserId != null) "u_${activeUserId}_" else "guest_"
    }

    fun isGameUnlocked(gameId: String): Boolean {
        val allGames = BookDatabase.getAllGames()
        val index = allGames.indexOfFirst { it.id == gameId }
        if (index < 0) return true
        return index <= _progressState.value.unlockedUpToIndex
    }

    fun getGameJourneyIndex(gameId: String): Int {
        return BookDatabase.getAllGames().indexOfFirst { it.id == gameId }
    }

    // =========================================================================
    // REGISTRO DE USUARIOS, PERFILES Y AVATARES
    // =========================================================================

    fun registerUser(
        username: String,
        emailOrTag: String = "",
        pin: String = "",
        avatar: PlayerAvatar = PlayerAvatar.GOUDA_TRAVELER,
        migrateGuestProgress: Boolean = true
    ): AuthResult {
        val cleanName = username.trim()
        if (cleanName.length < 2) {
            return AuthResult.Error("El nombre de usuario debe tener al menos 2 caracteres.")
        }
        val existingUsers = _progressState.value.registeredUsers
        if (existingUsers.any { it.username.equals(cleanName, ignoreCase = true) }) {
            return AuthResult.Error("Ya existe un jugador registrado con el nombre '$cleanName'.")
        }

        val wasGuest = _progressState.value.activeUser == null
        val shouldMigrate = migrateGuestProgress && wasGuest &&
            (_progressState.value.totalXp > 0 || _progressState.value.individualGameScores.isNotEmpty())

        val newId = UUID.randomUUID().toString().take(8)
        val initialXp = if (shouldMigrate) _progressState.value.totalXp else 0
        val initialUnlocked = if (shouldMigrate) _progressState.value.unlockedUpToIndex else 0
        val initialCompleted = if (shouldMigrate) _progressState.value.completedGamesCount else 0

        val newUser = RegisteredUser(
            id = newId,
            username = cleanName,
            emailOrTag = emailOrTag.trim(),
            pinHash = if (pin.isBlank()) "" else hashPin(pin.trim()),
            avatar = avatar,
            createdAt = System.currentTimeMillis(),
            totalXp = initialXp,
            unlockedUpToIndex = initialUnlocked,
            completedGamesCount = initialCompleted
        )

        val updatedUsers = existingUsers + newUser
        saveRegisteredUsers(updatedUsers)
        prefs?.edit()?.putString(KEY_ACTIVE_USER_ID, newUser.id)?.apply()

        if (shouldMigrate) {
            // Copiar todo el progreso actual de invitado al espacio del nuevo usuario
            val migratedLog = ProgressLogItem(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis(),
                title = "¡Perfil Registrado: ${newUser.username}!",
                description = "Tus puntuaciones individuales se han vinculado a tu cuenta.",
                xpDelta = 0,
                category = LogCategory.USER_REGISTERED
            )
            val newState = _progressState.value.copy(
                activeUser = newUser,
                registeredUsers = updatedUsers,
                recentLogs = (listOf(migratedLog) + _progressState.value.recentLogs).take(30)
            )
            _progressState.value = newState
            persistFullNamespaceState(newState)
        } else {
            // Iniciar un viaje limpio desde HOME para el nuevo jugador
            revealedChallengeIdsInMemory.clear()
            val welcomeLog = ProgressLogItem(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis(),
                title = "¡Bienvenido a Home, ${newUser.username}!",
                description = "Comienza tu viaje desbloqueando las partidas 1 a 1.",
                xpDelta = 0,
                category = LogCategory.USER_REGISTERED
            )
            val freshState = UserProgressState(
                activeUser = newUser,
                registeredUsers = updatedUsers,
                totalXp = 0,
                rank = UserRank.NOVICE,
                unlockedUpToIndex = 0,
                characterAtHome = true,
                unlockedArtifactIds = setOf("artifact_lewis_chessmen"),
                recentLogs = listOf(welcomeLog)
            )
            _progressState.value = freshState
            persistFullNamespaceState(freshState)
        }

        return AuthResult.Success(newUser)
    }

    fun loginUser(userId: String, pin: String = ""): AuthResult {
        val target = _progressState.value.registeredUsers.find { it.id == userId }
            ?: return AuthResult.Error("Usuario no encontrado.")

        if (target.hasPin && target.pinHash != hashPin(pin.trim())) {
            return AuthResult.Error("Contraseña o PIN incorrecto.")
        }

        prefs?.edit()?.putString(KEY_ACTIVE_USER_ID, target.id)?.apply()
        if (prefs != null) {
            loadActiveProfileFromPrefs()
        } else {
            _progressState.value = _progressState.value.copy(activeUser = target)
        }
        return AuthResult.Success(_progressState.value.activeUser ?: target)
    }

    fun logoutToGuest() {
        prefs?.edit()?.remove(KEY_ACTIVE_USER_ID)?.apply()
        if (prefs != null) {
            loadActiveProfileFromPrefs()
        } else {
            _progressState.value = UserProgressState(
                registeredUsers = _progressState.value.registeredUsers
            )
        }
    }

    fun setPlayerAvatar(avatar: PlayerAvatar) {
        val current = _progressState.value
        val active = current.activeUser
        if (active != null) {
            val updatedUser = active.copy(avatar = avatar)
            val updatedList = current.registeredUsers.map { if (it.id == updatedUser.id) updatedUser else it }
            saveRegisteredUsers(updatedList)
            _progressState.value = current.copy(
                activeUser = updatedUser,
                registeredUsers = updatedList
            )
        } else {
            val ns = currentNamespace(null)
            prefs?.edit()?.putString(ns + SUFFIX_GUEST_AVATAR, avatar.id)?.apply()
            _progressState.value = current.copy(guestAvatar = avatar)
        }
    }

    fun setCharacterAtHome(atHome: Boolean) {
        val ns = currentNamespace()
        prefs?.edit()?.putBoolean(ns + SUFFIX_CHAR_AT_HOME, atHome)?.apply()
        _progressState.value = _progressState.value.copy(characterAtHome = atHome)
    }

    // =========================================================================
    // PUNTUACIÓN INDIVIDUAL POR PARTIDA Y DESBLOQUEO SECUENCIAL 1 A 1
    // =========================================================================

    fun recordChallengeResult(
        momentId: String,
        gameTitle: String,
        wrongAttempts: Int,
        hintUsed: Boolean,
        revealed: Boolean
    ): Int {
        val allGames = BookDatabase.getAllGames()
        val game = allGames.find { g -> g.criticalMoments.any { it.id == momentId } }
        val gameIndex = if (game != null) allGames.indexOf(game) else -1
        val ns = currentNamespace()

        // El personaje sale de Home al jugar en el camino
        if (_progressState.value.characterAtHome) {
            setCharacterAtHome(false)
        }

        val currentStars = _progressState.value.challengeStars[momentId] ?: 0
        if (revealed) {
            val alreadyRevealed = momentId in revealedChallengeIdsInMemory
            revealedChallengeIdsInMemory.add(momentId)
            prefs?.edit()?.putStringSet(ns + SUFFIX_REVEALED_CHALLENGES, revealedChallengeIdsInMemory.toSet())?.apply()

            val delta = if (currentStars == 0 && !alreadyRevealed && game != null) {
                recalculateAndSaveGameScore(
                    game = game,
                    gameIndex = gameIndex,
                    updatedStars = _progressState.value.challengeStars,
                    maxPlyMap = _progressState.value.gameMaxPly,
                    incrementAttempt = true,
                    markGameConquered = false
                )
            } else 0

            if (delta > 0) {
                addXpInternal(
                    amount = delta,
                    title = "Solución consultada",
                    description = "Momento crítico: $gameTitle (+$delta pts)",
                    category = LogCategory.CHALLENGE_SOLVED
                )
            }
            return delta
        }

        val stars = when {
            wrongAttempts == 0 && !hintUsed -> 3
            wrongAttempts <= 2 && !hintUsed -> 2
            else -> 1
        }

        val updatedStars = _progressState.value.challengeStars.toMutableMap()
        if (stars > currentStars) {
            updatedStars[momentId] = stars
            prefs?.edit()?.putInt(ns + SUFFIX_CHALLENGE_STARS_PREFIX + momentId, stars)?.apply()
        } else if (momentId !in updatedStars) {
            updatedStars[momentId] = stars
            prefs?.edit()?.putInt(ns + SUFFIX_CHALLENGE_STARS_PREFIX + momentId, stars)?.apply()
        }

        val updatedSolved = updatedStars.keys.toSet()
        prefs?.edit()?.putStringSet(ns + SUFFIX_SOLVED_CHALLENGES, updatedSolved)?.apply()

        _progressState.value = _progressState.value.copy(
            challengeStars = updatedStars,
            solvedChallengesCount = updatedSolved.size
        )

        var earnedPoints = 0
        if (game != null) {
            earnedPoints = recalculateAndSaveGameScore(
                game = game,
                gameIndex = gameIndex,
                updatedStars = updatedStars,
                maxPlyMap = _progressState.value.gameMaxPly,
                incrementAttempt = true,
                markGameConquered = true
            )
            // Resolver el momento crítico supera la partida y desbloquea la siguiente 1 a 1
            unlockNextGameIfNeeded(game, gameIndex)
        } else {
            earnedPoints = when (stars) {
                3 -> if (currentStars == 0) 300 else 0
                2 -> if (currentStars == 0) 200 else 0
                else -> if (currentStars == 0) 120 else 0
            }
        }

        if (earnedPoints > 0) {
            addXpInternal(
                amount = earnedPoints,
                title = "¡Momento Crítico Superado! (${stars}★)",
                description = "$gameTitle (+$earnedPoints pts guardados)",
                category = LogCategory.CHALLENGE_SOLVED
            )
        }

        checkUnlocks()
        syncActiveUserSummary()
        return earnedPoints
    }

    fun recordGamePlyProgress(gameId: String, gameTitle: String, currentPly: Int, totalPlies: Int) {
        val allGames = BookDatabase.getAllGames()
        val gameIndex = allGames.indexOfFirst { it.id == gameId }
        val game = if (gameIndex >= 0) allGames[gameIndex] else null
        val ns = currentNamespace()

        if (currentPly > 0 && _progressState.value.characterAtHome) {
            setCharacterAtHome(false)
        }

        val currentMax = _progressState.value.gameMaxPly[gameId] ?: 0
        val updatedPlyMap = _progressState.value.gameMaxPly.toMutableMap()
        if (currentPly > currentMax) {
            updatedPlyMap[gameId] = currentPly
            prefs?.edit()?.putInt(ns + SUFFIX_GAME_MAX_PLY_PREFIX + gameId, currentPly)?.apply()
            _progressState.value = _progressState.value.copy(gameMaxPly = updatedPlyMap)
        }

        val reachedEnd = totalPlies > 0 && currentPly >= totalPlies
        if (game != null && (currentPly > currentMax || reachedEnd)) {
            val scoreDelta = recalculateAndSaveGameScore(
                game = game,
                gameIndex = gameIndex,
                updatedStars = _progressState.value.challengeStars,
                maxPlyMap = updatedPlyMap,
                incrementAttempt = false,
                markGameConquered = reachedEnd
            )
            if (scoreDelta > 0) {
                val logTitle = if (reachedEnd) {
                    "Partida #${gameIndex + 1} Completada"
                } else {
                    "Progreso en Partida #${gameIndex + 1}"
                }
                addXpInternal(
                    amount = scoreDelta,
                    title = logTitle,
                    description = "$gameTitle (+$scoreDelta pts individuales)",
                    category = LogCategory.GAME_COMPLETED
                )
            }
        }

        if (reachedEnd && game != null) {
            unlockNextGameIfNeeded(game, gameIndex)
            checkUnlocks()
            syncActiveUserSummary()
        } else if (scoreDeltaNonZeroCheck()) {
            syncActiveUserSummary()
        }
    }

    private fun scoreDeltaNonZeroCheck(): Boolean = true

    /**
     * Calcula la puntuación individual exacta de una partida (Estudio + Momentos Críticos),
     * la guarda en el registro individual del usuario y devuelve el incremento de puntos logrado.
     */
    private fun recalculateAndSaveGameScore(
        game: BookGame,
        gameIndex: Int,
        updatedStars: Map<String, Int>,
        maxPlyMap: Map<String, Int>,
        incrementAttempt: Boolean,
        markGameConquered: Boolean
    ): Int {
        val previousRecord = _progressState.value.individualGameScores[game.id]
        val previousTotal = previousRecord?.totalScore ?: 0

        val totalPlies = game.moves.size.coerceAtLeast(1)
        val maxPly = (maxPlyMap[game.id] ?: 0).coerceIn(0, totalPlies)
        val studyScore = ((maxPly.toFloat() / totalPlies.toFloat()) * 100f).toInt().coerceIn(0, 100)

        var challengeScore = 0
        var bestStars = previousRecord?.stars ?: 0
        for (moment in game.criticalMoments) {
            val stars = updatedStars[moment.id] ?: 0
            if (stars > bestStars) bestStars = stars
            challengeScore += when (stars) {
                3 -> 300
                2 -> 200
                1 -> 120
                else -> if (moment.id in revealedChallengeIdsInMemory) 25 else 0
            }
        }

        val isNowCompleted = (previousRecord?.isCompleted == true) || markGameConquered
        val newRecord = GameScoreRecord(
            gameId = game.id,
            gameNumber = (gameIndex + 1).coerceAtLeast(1),
            gameTitle = "${game.whitePlayer} vs ${game.blackPlayer} (${game.year})",
            studyScore = maxOf(previousRecord?.studyScore ?: 0, studyScore),
            challengeScore = maxOf(previousRecord?.challengeScore ?: 0, challengeScore),
            stars = bestStars,
            attempts = (previousRecord?.attempts ?: 0) + (if (incrementAttempt) 1 else 0),
            isCompleted = isNowCompleted,
            updatedAt = System.currentTimeMillis()
        )

        val updatedScores = _progressState.value.individualGameScores.toMutableMap()
        updatedScores[game.id] = newRecord

        val ns = currentNamespace()
        prefs?.edit()?.putString(ns + SUFFIX_SCORES_JSON, serializeGameScores(updatedScores))?.apply()

        _progressState.value = _progressState.value.copy(
            individualGameScores = updatedScores
        )

        return (newRecord.totalScore - previousTotal).coerceAtLeast(0)
    }

    /**
     * Marca la partida como superada y desbloquea estrictamente la siguiente partida (1 a 1).
     */
    private fun unlockNextGameIfNeeded(game: BookGame, gameIndex: Int) {
        val ns = currentNamespace()
        val allGames = BookDatabase.getAllGames()
        val currentState = _progressState.value

        val updatedCompleted = currentState.completedGameIds + game.id
        prefs?.edit()?.putStringSet(ns + SUFFIX_COMPLETED_GAMES, updatedCompleted)?.apply()

        val previousUnlocked = currentState.unlockedUpToIndex
        // Solo se desbloquea la siguiente partida si hemos superado una partida dentro de nuestro alcance actual
        val targetNextUnlocked = if (gameIndex in 0..previousUnlocked) {
            maxOf(previousUnlocked, (gameIndex + 1).coerceAtMost((allGames.size - 1).coerceAtLeast(0)))
        } else {
            previousUnlocked
        }

        prefs?.edit()?.putInt(ns + SUFFIX_UNLOCKED_INDEX, targetNextUnlocked)?.apply()
        prefs?.edit()?.putBoolean(ns + SUFFIX_CHAR_AT_HOME, false)?.apply()

        _progressState.value = _progressState.value.copy(
            completedGameIds = updatedCompleted,
            completedGamesCount = updatedCompleted.size,
            unlockedUpToIndex = targetNextUnlocked,
            characterAtHome = false
        )

        if (targetNextUnlocked > previousUnlocked && targetNextUnlocked < allGames.size) {
            val nextGame = allGames[targetNextUnlocked]
            val unlockLog = ProgressLogItem(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis(),
                title = "¡Partida #${targetNextUnlocked + 1} Desbloqueada!",
                description = "Siguiente parada: ${nextGame.whitePlayer} vs ${nextGame.blackPlayer} (${nextGame.year})",
                xpDelta = 0,
                category = LogCategory.GAME_UNLOCKED
            )
            appendLogItem(unlockLog)
        }
    }

    private fun addXpInternal(amount: Int, title: String, description: String, category: LogCategory) {
        val ns = currentNamespace()
        val oldXp = _progressState.value.totalXp
        val newXp = oldXp + amount
        val oldRank = _progressState.value.rank
        val newRank = UserRank.fromXp(newXp)

        prefs?.edit()?.putInt(ns + SUFFIX_TOTAL_XP, newXp)?.apply()

        val logItem = ProgressLogItem(
            id = UUID.randomUUID().toString(),
            timestamp = System.currentTimeMillis(),
            title = title,
            description = description,
            xpDelta = amount,
            category = category
        )

        val updatedLogs = (listOf(logItem) + _progressState.value.recentLogs).take(30)
        prefs?.edit()?.putString(ns + SUFFIX_LOGS_JSON, serializeLogs(updatedLogs))?.apply()

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
            appendLogItem(levelUpLog)
        }
    }

    private fun appendLogItem(item: ProgressLogItem) {
        val ns = currentNamespace()
        val updatedLogs = (listOf(item) + _progressState.value.recentLogs).take(30)
        prefs?.edit()?.putString(ns + SUFFIX_LOGS_JSON, serializeLogs(updatedLogs))?.apply()
        _progressState.value = _progressState.value.copy(recentLogs = updatedLogs)
    }

    private fun checkUnlocks() {
        val ns = currentNamespace()
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
                    addXpInternal(
                        35,
                        "¡Reliquia Desbloqueada!",
                        "Has obtenido '${art.name}' para tu museo.",
                        LogCategory.ARTIFACT_UNLOCKED
                    )
                }
            }
        }

        if (changedArtifacts) {
            prefs?.edit()?.putStringSet(ns + SUFFIX_UNLOCKED_ARTIFACTS, newlyUnlockedArtifacts)?.apply()
            _progressState.value = _progressState.value.copy(unlockedArtifactIds = newlyUnlockedArtifacts)
        }

        val currentAfterArt = _progressState.value
        val newlyUnlockedAchievements = currentAfterArt.unlockedAchievementIds.toMutableSet()
        var changedAchievements = false

        for (ach in ALL_ACHIEVEMENTS) {
            if (ach.id !in newlyUnlockedAchievements) {
                val shouldUnlock = when (ach.id) {
                    "ach_first_blood" -> currentAfterArt.solvedChallengesCount >= 1
                    "ach_three_stars" -> currentAfterArt.challengeStars.values.any { it >= 3 }
                    "ach_game_completed" -> currentAfterArt.completedGamesCount >= 1
                    "ach_steinitz_disciple" -> currentAfterArt.completedGamesCount >= 3
                    "ach_tactical_master" -> currentAfterArt.challengeStars.values.count { it >= 2 } >= 5
                    "ach_ten_games" -> currentAfterArt.completedGamesCount >= 10
                    "ach_thousand_xp" -> currentAfterArt.totalXp >= 1500
                    else -> false
                }
                if (shouldUnlock) {
                    newlyUnlockedAchievements.add(ach.id)
                    changedAchievements = true
                    addXpInternal(
                        ach.xpReward,
                        "¡Logro Desbloqueado! ${ach.iconEmoji}",
                        "${ach.title}: ${ach.description}",
                        LogCategory.ACHIEVEMENT_UNLOCKED
                    )
                }
            }
        }

        if (changedAchievements) {
            prefs?.edit()?.putStringSet(ns + SUFFIX_UNLOCKED_ACHIEVEMENTS, newlyUnlockedAchievements)?.apply()
            _progressState.value = _progressState.value.copy(unlockedAchievementIds = newlyUnlockedAchievements)
        }
    }

    private fun syncActiveUserSummary() {
        val current = _progressState.value
        val active = current.activeUser ?: return
        val updatedUser = active.copy(
            totalXp = current.totalXp,
            unlockedUpToIndex = current.unlockedUpToIndex,
            completedGamesCount = current.completedGamesCount
        )
        val updatedUsers = current.registeredUsers.map { if (it.id == updatedUser.id) updatedUser else it }
        saveRegisteredUsers(updatedUsers)
        _progressState.value = current.copy(
            activeUser = updatedUser,
            registeredUsers = updatedUsers
        )
    }

    // =========================================================================
    // PERSISTENCIA Y SERIALIZACIÓN JSON EN SHAREDPREFERENCES
    // =========================================================================

    private fun loadActiveProfileFromPrefs() {
        val p = prefs ?: return
        val users = parseRegisteredUsers(p.getString(KEY_USERS_JSON, null))
        val activeId = p.getString(KEY_ACTIVE_USER_ID, null)
        val activeUser = users.find { it.id == activeId }

        val ns = currentNamespace(activeUser?.id)
        val xp = p.getInt(ns + SUFFIX_TOTAL_XP, 0)
        val unlockedIndex = p.getInt(ns + SUFFIX_UNLOCKED_INDEX, 0)
        val charAtHome = p.getBoolean(ns + SUFFIX_CHAR_AT_HOME, xp == 0)
        val guestAvatar = PlayerAvatar.fromId(p.getString(ns + SUFFIX_GUEST_AVATAR, null))

        val solvedIds = p.getStringSet(ns + SUFFIX_SOLVED_CHALLENGES, emptySet()) ?: emptySet()
        val revealedIds = p.getStringSet(ns + SUFFIX_REVEALED_CHALLENGES, emptySet()) ?: emptySet()
        revealedChallengeIdsInMemory = revealedIds.toMutableSet()

        val completedGameIds = p.getStringSet(ns + SUFFIX_COMPLETED_GAMES, emptySet()) ?: emptySet()
        val unlockedArtifacts = (p.getStringSet(
            ns + SUFFIX_UNLOCKED_ARTIFACTS,
            setOf("artifact_lewis_chessmen")
        ) ?: setOf("artifact_lewis_chessmen")).toSet()
        val unlockedAchievements = (p.getStringSet(ns + SUFFIX_UNLOCKED_ACHIEVEMENTS, emptySet()) ?: emptySet()).toSet()

        val starsMap = mutableMapOf<String, Int>()
        for (id in solvedIds) {
            starsMap[id] = p.getInt(ns + SUFFIX_CHALLENGE_STARS_PREFIX + id, 3)
        }

        val allGames = BookDatabase.getAllGames()
        val maxPlyMap = mutableMapOf<String, Int>()
        for (g in allGames) {
            val savedPly = p.getInt(ns + SUFFIX_GAME_MAX_PLY_PREFIX + g.id, 0)
            if (savedPly > 0) {
                maxPlyMap[g.id] = savedPly
            }
        }

        val scoresMap = parseGameScores(p.getString(ns + SUFFIX_SCORES_JSON, null))
        val logsList = parseLogs(p.getString(ns + SUFFIX_LOGS_JSON, null))

        // Asegurar coherencia si había partidas completadas previas
        val maxCompletedIdx = completedGameIds.mapNotNull { id ->
            allGames.indexOfFirst { it.id == id }.takeIf { it >= 0 }
        }.maxOrNull() ?: -1
        val effectiveUnlocked = maxOf(
            unlockedIndex,
            if (maxCompletedIdx >= 0) (maxCompletedIdx + 1).coerceAtMost((allGames.size - 1).coerceAtLeast(0)) else 0
        )

        _progressState.value = UserProgressState(
            activeUser = activeUser?.copy(
                totalXp = xp,
                unlockedUpToIndex = effectiveUnlocked,
                completedGamesCount = completedGameIds.size
            ),
            guestAvatar = guestAvatar,
            registeredUsers = users,
            totalXp = xp,
            rank = UserRank.fromXp(xp),
            unlockedUpToIndex = effectiveUnlocked,
            characterAtHome = charAtHome,
            solvedChallengesCount = solvedIds.size,
            completedGamesCount = completedGameIds.size,
            challengeStars = starsMap,
            completedGameIds = completedGameIds,
            gameMaxPly = maxPlyMap,
            individualGameScores = scoresMap,
            unlockedArtifactIds = unlockedArtifacts,
            unlockedAchievementIds = unlockedAchievements,
            recentLogs = logsList
        )

        checkUnlocks()
    }

    private fun persistFullNamespaceState(state: UserProgressState) {
        val p = prefs ?: return
        val ns = currentNamespace(state.activeUser?.id)
        val editor = p.edit()
        editor.putInt(ns + SUFFIX_TOTAL_XP, state.totalXp)
        editor.putInt(ns + SUFFIX_UNLOCKED_INDEX, state.unlockedUpToIndex)
        editor.putBoolean(ns + SUFFIX_CHAR_AT_HOME, state.characterAtHome)
        editor.putStringSet(ns + SUFFIX_SOLVED_CHALLENGES, state.challengeStars.keys)
        editor.putStringSet(ns + SUFFIX_REVEALED_CHALLENGES, revealedChallengeIdsInMemory.toSet())
        for ((momentId, stars) in state.challengeStars) {
            editor.putInt(ns + SUFFIX_CHALLENGE_STARS_PREFIX + momentId, stars)
        }
        editor.putStringSet(ns + SUFFIX_COMPLETED_GAMES, state.completedGameIds)
        for ((gameId, ply) in state.gameMaxPly) {
            editor.putInt(ns + SUFFIX_GAME_MAX_PLY_PREFIX + gameId, ply)
        }
        editor.putString(ns + SUFFIX_SCORES_JSON, serializeGameScores(state.individualGameScores))
        editor.putString(ns + SUFFIX_LOGS_JSON, serializeLogs(state.recentLogs))
        editor.putStringSet(ns + SUFFIX_UNLOCKED_ARTIFACTS, state.unlockedArtifactIds)
        editor.putStringSet(ns + SUFFIX_UNLOCKED_ACHIEVEMENTS, state.unlockedAchievementIds)
        editor.apply()
    }

    private fun saveRegisteredUsers(users: List<RegisteredUser>) {
        val p = prefs ?: return
        val arr = JSONArray()
        for (u in users) {
            val obj = JSONObject()
            obj.put("id", u.id)
            obj.put("username", u.username)
            obj.put("emailOrTag", u.emailOrTag)
            obj.put("pinHash", u.pinHash)
            obj.put("avatar", u.avatar.id)
            obj.put("createdAt", u.createdAt)
            obj.put("totalXp", u.totalXp)
            obj.put("unlockedUpToIndex", u.unlockedUpToIndex)
            obj.put("completedGamesCount", u.completedGamesCount)
            arr.put(obj)
        }
        p.edit().putString(KEY_USERS_JSON, arr.toString()).apply()
    }

    private fun parseRegisteredUsers(json: String?): List<RegisteredUser> {
        if (json.isNullOrBlank()) return emptyList()
        return try {
            val arr = JSONArray(json)
            val result = mutableListOf<RegisteredUser>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                result.add(
                    RegisteredUser(
                        id = obj.getString("id"),
                        username = obj.getString("username"),
                        emailOrTag = obj.optString("emailOrTag", ""),
                        pinHash = obj.optString("pinHash", ""),
                        avatar = PlayerAvatar.fromId(obj.optString("avatar", "")),
                        createdAt = obj.optLong("createdAt", 0L),
                        totalXp = obj.optInt("totalXp", 0),
                        unlockedUpToIndex = obj.optInt("unlockedUpToIndex", 0),
                        completedGamesCount = obj.optInt("completedGamesCount", 0)
                    )
                )
            }
            result
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun serializeGameScores(scores: Map<String, GameScoreRecord>): String {
        val arr = JSONArray()
        for (record in scores.values) {
            val obj = JSONObject()
            obj.put("gameId", record.gameId)
            obj.put("gameNumber", record.gameNumber)
            obj.put("gameTitle", record.gameTitle)
            obj.put("studyScore", record.studyScore)
            obj.put("challengeScore", record.challengeScore)
            obj.put("stars", record.stars)
            obj.put("attempts", record.attempts)
            obj.put("isCompleted", record.isCompleted)
            obj.put("updatedAt", record.updatedAt)
            arr.put(obj)
        }
        return arr.toString()
    }

    private fun parseGameScores(json: String?): Map<String, GameScoreRecord> {
        if (json.isNullOrBlank()) return emptyMap()
        return try {
            val arr = JSONArray(json)
            val map = mutableMapOf<String, GameScoreRecord>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val rec = GameScoreRecord(
                    gameId = obj.getString("gameId"),
                    gameNumber = obj.optInt("gameNumber", 1),
                    gameTitle = obj.optString("gameTitle", ""),
                    studyScore = obj.optInt("studyScore", 0),
                    challengeScore = obj.optInt("challengeScore", 0),
                    stars = obj.optInt("stars", 0),
                    attempts = obj.optInt("attempts", 0),
                    isCompleted = obj.optBoolean("isCompleted", false),
                    updatedAt = obj.optLong("updatedAt", 0L)
                )
                map[rec.gameId] = rec
            }
            map
        } catch (_: Exception) {
            emptyMap()
        }
    }

    private fun serializeLogs(logs: List<ProgressLogItem>): String {
        val arr = JSONArray()
        for (l in logs) {
            val obj = JSONObject()
            obj.put("id", l.id)
            obj.put("timestamp", l.timestamp)
            obj.put("title", l.title)
            obj.put("description", l.description)
            obj.put("xpDelta", l.xpDelta)
            obj.put("category", l.category.name)
            arr.put(obj)
        }
        return arr.toString()
    }

    private fun parseLogs(json: String?): List<ProgressLogItem> {
        if (json.isNullOrBlank()) return emptyList()
        return try {
            val arr = JSONArray(json)
            val list = mutableListOf<ProgressLogItem>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val catName = obj.optString("category", LogCategory.GAME_COMPLETED.name)
                val category = runCatching { LogCategory.valueOf(catName) }.getOrDefault(LogCategory.GAME_COMPLETED)
                list.add(
                    ProgressLogItem(
                        id = obj.getString("id"),
                        timestamp = obj.optLong("timestamp", 0L),
                        title = obj.optString("title", ""),
                        description = obj.optString("description", ""),
                        xpDelta = obj.optInt("xpDelta", 0),
                        category = category
                    )
                )
            }
            list
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun hashPin(pin: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(pin.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
