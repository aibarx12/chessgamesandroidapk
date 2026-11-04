package com.example.chess.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chess.data.BookDatabase
import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChallengeItem
import com.example.chess.data.GameChapter
import com.example.chess.engine.EngineEvaluation
import com.example.chess.engine.StockfishEngine
import com.example.chess.gamification.GamificationRepository
import com.example.chess.gamification.SoundEffectsManager
import com.example.chess.gamification.UserProgressState
import com.example.chess.model.BoardState
import com.example.chess.model.BoardTheme
import com.example.chess.model.Move
import com.example.chess.model.PieceColor
import com.example.chess.model.PieceStyle
import com.example.chess.model.SanParser
import com.example.chess.model.Square
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class HomeTab {
    EXPLORE_GAMES,
    CRITICAL_CHALLENGES,
    MUSEUM_PROGRESS
}

enum class ChallengeStatus {
    PENDING,
    WRONG_TRY,
    SOLVED,
    REVEALED
}

data class ChessUiState(
    val currentScreen: Screen = Screen.GAME_LIST,
    val selectedHomeTab: HomeTab = HomeTab.EXPLORE_GAMES,
    val selectedChapter: GameChapter? = null,
    val searchQuery: String = "",
    val games: List<BookGame> = emptyList(),
    val challenges: List<GameChallengeItem> = emptyList(),
    val solvedChallengeIds: Set<String> = emptySet(),
    val activeGame: BookGame? = null,
    val currentPly: Int = 0,
    val boardState: BoardState = BoardState.initial(),
    val lastMove: Move? = null,
    val selectedSquare: Square? = null,
    val isFlipped: Boolean = false,
    val isEngineEnabled: Boolean = true,
    val engineEvaluation: EngineEvaluation? = null,
    val isEvaluating: Boolean = false,
    val isAutoPlaying: Boolean = false,
    val autoPlaySpeedMs: Long = 1800L,
    val isFreeExploreMode: Boolean = false,
    val showHistoricalIntroDialog: Boolean = false,
    // Challenge ("Buscar la continuación") mode state
    val isChallengeMode: Boolean = false,
    val activeChallenge: CriticalMoment? = null,
    val challengeStatus: ChallengeStatus = ChallengeStatus.PENDING,
    val showChallengeHint: Boolean = false,
    val wrongAttemptsCount: Int = 0,
    val lastEarnedXp: Int? = null,
    val showGameCompletedDialog: Boolean = false,
    val isMuted: Boolean = false,
    val userProgress: UserProgressState = UserProgressState(),
    // Appearance settings
    val selectedBoardTheme: BoardTheme = BoardTheme.CLASSIC_WALNUT,
    val selectedPieceStyle: PieceStyle = PieceStyle.CLASSIC_STAUNTON,
    val showAppearanceDialog: Boolean = false,
    val showBoardThemeDialog: Boolean = false,
    val showPieceStyleDialog: Boolean = false
)

enum class Screen {
    GAME_LIST,
    GAME_VIEWER
}

class ChessViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ChessUiState())
    val uiState: StateFlow<ChessUiState> = _uiState.asStateFlow()

    private var engineJob: Job? = null
    private var autoPlayJob: Job? = null

    init {
        loadGames()
        val firstGame = BookDatabase.getAllGames().firstOrNull()
        if (firstGame != null) {
            setActiveGame(firstGame)
        }
        viewModelScope.launch {
            GamificationRepository.progressState.collect { progress ->
                _uiState.value = _uiState.value.copy(
                    userProgress = progress,
                    solvedChallengeIds = progress.challengeStars.keys
                )
            }
        }
    }

    fun loadGames() {
        val chapter = _uiState.value.selectedChapter
        val filteredGames = BookDatabase.searchGames(_uiState.value.searchQuery, chapter)
        val filteredChallenges = BookDatabase.getChallengesByChapter(chapter).filter { item ->
            val q = _uiState.value.searchQuery.trim().lowercase()
            if (q.isEmpty()) true
            else item.moment.title.lowercase().contains(q) ||
                item.game.whitePlayer.lowercase().contains(q) ||
                item.game.blackPlayer.lowercase().contains(q) ||
                item.game.title.lowercase().contains(q) ||
                item.game.year.lowercase().contains(q)
        }
        _uiState.value = _uiState.value.copy(
            games = filteredGames,
            challenges = filteredChallenges
        )
    }

    fun onHomeTabSelected(tab: HomeTab) {
        _uiState.value = _uiState.value.copy(selectedHomeTab = tab)
    }

    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        loadGames()
    }

    fun onChapterSelected(chapter: GameChapter?) {
        _uiState.value = _uiState.value.copy(selectedChapter = chapter)
        loadGames()
    }

    fun openGame(game: BookGame) {
        setActiveGame(game)
        _uiState.value = _uiState.value.copy(
            currentScreen = Screen.GAME_VIEWER,
            isChallengeMode = false,
            activeChallenge = null
        )
    }

    fun startChallenge(game: BookGame, moment: CriticalMoment) {
        stopAutoPlay()
        engineJob?.cancel()

        val clampedPly = moment.plyBefore.coerceIn(0, game.moves.size)
        val stateAtMoment: BoardState
        val prevMove: Move?

        if (clampedPly == 0) {
            stateAtMoment = BoardState.initial()
            prevMove = null
        } else {
            val targetMove = game.moves[clampedPly - 1]
            stateAtMoment = BoardState.fromFen(targetMove.fenAfter)
            val prevFen = if (clampedPly == 1) {
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            } else {
                game.moves[clampedPly - 2].fenAfter
            }
            prevMove = SanParser.parseSan(BoardState.fromFen(prevFen), targetMove.san)
        }

        _uiState.value = _uiState.value.copy(
            currentScreen = Screen.GAME_VIEWER,
            activeGame = game,
            currentPly = clampedPly,
            boardState = stateAtMoment,
            lastMove = prevMove,
            selectedSquare = null,
            isFlipped = stateAtMoment.turn == PieceColor.BLACK,
            // Disable engine during challenge so the user calculates without Stockfish help
            isEngineEnabled = false,
            engineEvaluation = null,
            isEvaluating = false,
            isFreeExploreMode = false,
            isAutoPlaying = false,
            isChallengeMode = true,
            activeChallenge = moment,
            challengeStatus = ChallengeStatus.PENDING,
            showChallengeHint = false,
            wrongAttemptsCount = 0
        )
    }

    fun startRandomChallenge() {
        val all = BookDatabase.getAllChallenges()
        if (all.isEmpty()) return
        val unsolved = all.filter { it.moment.id !in _uiState.value.solvedChallengeIds }
        val chosen = if (unsolved.isNotEmpty()) unsolved.random() else all.random()
        startChallenge(chosen.game, chosen.moment)
    }

    fun startNextChallenge() {
        val all = BookDatabase.getAllChallenges()
        if (all.isEmpty()) return
        val currentId = _uiState.value.activeChallenge?.id
        val currentIndex = all.indexOfFirst { it.moment.id == currentId }
        val nextItem = if (currentIndex in 0 until all.size - 1) {
            all[currentIndex + 1]
        } else {
            all.first()
        }
        startChallenge(nextItem.game, nextItem.moment)
    }

    fun retryChallenge() {
        val game = _uiState.value.activeGame ?: return
        val moment = _uiState.value.activeChallenge ?: return
        startChallenge(game, moment)
    }

    fun toggleChallengeHint() {
        _uiState.value = _uiState.value.copy(showChallengeHint = !_uiState.value.showChallengeHint)
    }

    fun revealChallengeSolution() {
        val game = _uiState.value.activeGame ?: return
        val moment = _uiState.value.activeChallenge ?: return
        val solutionPly = (moment.plyBefore + 1).coerceAtMost(game.moves.size)
        jumpToPlyInternal(solutionPly, preserveChallengeMode = true)
        GamificationRepository.recordChallengeResult(
            momentId = moment.id,
            gameTitle = "${game.whitePlayer} vs ${game.blackPlayer} (${game.year})",
            wrongAttempts = _uiState.value.wrongAttemptsCount,
            hintUsed = true,
            revealed = true
        )
        _uiState.value = _uiState.value.copy(
            challengeStatus = ChallengeStatus.REVEALED,
            showChallengeHint = true,
            lastEarnedXp = 10
        )
    }

    fun exitChallengeMode() {
        _uiState.value = _uiState.value.copy(
            isChallengeMode = false,
            activeChallenge = null,
            isEngineEnabled = true
        )
        triggerEngineEvaluation(_uiState.value.boardState)
    }

    fun navigateTo(screen: Screen) {
        if (screen == Screen.GAME_LIST) {
            stopAutoPlay()
        }
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }

    fun setActiveGame(game: BookGame) {
        stopAutoPlay()
        val initialState = BoardState.initial()
        _uiState.value = _uiState.value.copy(
            activeGame = game,
            currentPly = 0,
            boardState = initialState,
            lastMove = null,
            selectedSquare = null,
            isFlipped = false,
            isFreeExploreMode = false,
            isAutoPlaying = false,
            isChallengeMode = false,
            activeChallenge = null,
            isEngineEnabled = true
        )
        triggerEngineEvaluation(initialState)
    }

    fun goToStart() {
        stopAutoPlay()
        val state = BoardState.initial()
        _uiState.value = _uiState.value.copy(
            currentPly = 0,
            boardState = state,
            lastMove = null,
            selectedSquare = null,
            isFreeExploreMode = false,
            isChallengeMode = false,
            activeChallenge = null
        )
        triggerEngineEvaluation(state)
    }

    fun previousMove() {
        stopAutoPlay()
        val current = _uiState.value.currentPly
        if (current <= 0) return
        jumpToPly(current - 1)
    }

    fun nextMove() {
        val game = _uiState.value.activeGame ?: return
        val current = _uiState.value.currentPly
        if (current >= game.moves.size) {
            stopAutoPlay()
            return
        }
        jumpToPly(current + 1)
    }

    fun goToEnd() {
        stopAutoPlay()
        val game = _uiState.value.activeGame ?: return
        jumpToPly(game.moves.size)
    }

    fun jumpToPly(targetPly: Int) {
        jumpToPlyInternal(targetPly, preserveChallengeMode = false)
    }

    private fun jumpToPlyInternal(targetPly: Int, preserveChallengeMode: Boolean) {
        val game = _uiState.value.activeGame ?: return
        val clamped = targetPly.coerceIn(0, game.moves.size)

        val newState: BoardState
        val lastM: Move?

        if (clamped == 0) {
            newState = BoardState.initial()
            lastM = null
        } else {
            val targetMove = game.moves[clamped - 1]
            newState = BoardState.fromFen(targetMove.fenAfter)

            val prevFen = if (clamped == 1) {
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            } else {
                game.moves[clamped - 2].fenAfter
            }
            val prevState = BoardState.fromFen(prevFen)
            val parsedMove = SanParser.parseSan(prevState, targetMove.san)
            lastM = parsedMove
        }

        if (clamped > 0 && lastM != null) {
            if (lastM.isCapture) {
                SoundEffectsManager.playCapture()
            } else {
                SoundEffectsManager.playMove()
            }
        }

        val wasAlreadyCompleted = game.id in _uiState.value.userProgress.completedGameIds
        GamificationRepository.recordGamePlyProgress(
            gameId = game.id,
            gameTitle = "${game.whitePlayer} vs ${game.blackPlayer} (${game.year})",
            currentPly = clamped,
            totalPlies = game.moves.size
        )
        val shouldShowCompleted = clamped == game.moves.size && !wasAlreadyCompleted && !preserveChallengeMode

        _uiState.value = _uiState.value.copy(
            currentPly = clamped,
            boardState = newState,
            lastMove = lastM,
            selectedSquare = null,
            isFreeExploreMode = false,
            isChallengeMode = if (preserveChallengeMode) _uiState.value.isChallengeMode else false,
            activeChallenge = if (preserveChallengeMode) _uiState.value.activeChallenge else null,
            showGameCompletedDialog = shouldShowCompleted
        )

        triggerEngineEvaluation(newState)
    }

    fun toggleAutoPlay() {
        if (_uiState.value.isAutoPlaying) {
            stopAutoPlay()
        } else {
            startAutoPlay()
        }
    }

    private fun startAutoPlay() {
        autoPlayJob?.cancel()
        _uiState.value = _uiState.value.copy(isAutoPlaying = true, isChallengeMode = false, activeChallenge = null)
        autoPlayJob = viewModelScope.launch {
            while (isActive) {
                delay(_uiState.value.autoPlaySpeedMs)
                val game = _uiState.value.activeGame
                val current = _uiState.value.currentPly
                if (game == null || current >= game.moves.size) {
                    stopAutoPlay()
                    break
                }
                nextMove()
            }
        }
    }

    private fun stopAutoPlay() {
        autoPlayJob?.cancel()
        autoPlayJob = null
        _uiState.value = _uiState.value.copy(isAutoPlaying = false)
    }

    fun toggleFlip() {
        _uiState.value = _uiState.value.copy(isFlipped = !_uiState.value.isFlipped)
    }

    fun toggleEngine() {
        // In active unsolved challenge mode, engine stays disabled so the user finds the continuation unaided
        if (_uiState.value.isChallengeMode &&
            (_uiState.value.challengeStatus == ChallengeStatus.PENDING ||
                _uiState.value.challengeStatus == ChallengeStatus.WRONG_TRY)
        ) {
            return
        }
        val newState = !_uiState.value.isEngineEnabled
        _uiState.value = _uiState.value.copy(isEngineEnabled = newState)
        if (newState) {
            triggerEngineEvaluation(_uiState.value.boardState)
        } else {
            engineJob?.cancel()
            _uiState.value = _uiState.value.copy(engineEvaluation = null, isEvaluating = false)
        }
    }

    fun onSquareClicked(square: Square) {
        stopAutoPlay()
        val currentSelected = _uiState.value.selectedSquare
        val state = _uiState.value.boardState

        if (currentSelected == null) {
            val piece = state.getPiece(square)
            if (piece != null && piece.color == state.turn) {
                _uiState.value = _uiState.value.copy(selectedSquare = square)
            }
        } else {
            if (currentSelected == square) {
                _uiState.value = _uiState.value.copy(selectedSquare = null)
                return
            }

            val clickedPiece = state.getPiece(square)
            if (clickedPiece != null && clickedPiece.color == state.turn) {
                _uiState.value = _uiState.value.copy(selectedSquare = square)
                return
            }

            val legalMoves = state.generateLegalMoves().filter { it.from == currentSelected && it.to == square }
            if (legalMoves.isNotEmpty()) {
                val move = legalMoves.first()

                // Handle Challenge Mode ("Buscar la continuación")
                val activeChallenge = _uiState.value.activeChallenge
                val game = _uiState.value.activeGame
                if (_uiState.value.isChallengeMode &&
                    activeChallenge != null &&
                    game != null &&
                    (_uiState.value.challengeStatus == ChallengeStatus.PENDING ||
                        _uiState.value.challengeStatus == ChallengeStatus.WRONG_TRY)
                ) {
                    val expectedFromSan = SanParser.parseSan(state, activeChallenge.solutionSan)
                    val expectedFromGameMove = game.moves.getOrNull(activeChallenge.plyBefore)?.let {
                        SanParser.parseSan(state, it.san)
                    }

                    val isCorrect = (expectedFromSan != null && move.from == expectedFromSan.from && move.to == expectedFromSan.to) ||
                        (expectedFromGameMove != null && move.from == expectedFromGameMove.from && move.to == expectedFromGameMove.to)

                    if (isCorrect) {
                        val nextState = state.makeMove(move)
                        val earnedXp = GamificationRepository.recordChallengeResult(
                            momentId = activeChallenge.id,
                            gameTitle = "${game.whitePlayer} vs ${game.blackPlayer} (${game.year})",
                            wrongAttempts = _uiState.value.wrongAttemptsCount,
                            hintUsed = _uiState.value.showChallengeHint,
                            revealed = false
                        )
                        if (move.isCapture) {
                            SoundEffectsManager.playCapture()
                        } else {
                            SoundEffectsManager.playMove()
                        }
                        val updatedSolved = _uiState.value.solvedChallengeIds + activeChallenge.id
                        _uiState.value = _uiState.value.copy(
                            currentPly = (activeChallenge.plyBefore + 1).coerceAtMost(game.moves.size),
                            boardState = nextState,
                            lastMove = move,
                            selectedSquare = null,
                            challengeStatus = ChallengeStatus.SOLVED,
                            solvedChallengeIds = updatedSolved,
                            lastEarnedXp = earnedXp
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            selectedSquare = null,
                            challengeStatus = ChallengeStatus.WRONG_TRY,
                            wrongAttemptsCount = _uiState.value.wrongAttemptsCount + 1
                        )
                    }
                    return
                }

                // Normal / Free exploration move
                val nextState = state.makeMove(move)
                if (move.isCapture) {
                    SoundEffectsManager.playCapture()
                } else {
                    SoundEffectsManager.playMove()
                }
                _uiState.value = _uiState.value.copy(
                    boardState = nextState,
                    lastMove = move,
                    selectedSquare = null,
                    isFreeExploreMode = true
                )
                triggerEngineEvaluation(nextState)
            } else {
                _uiState.value = _uiState.value.copy(selectedSquare = null)
            }
        }
    }

    fun dismissGameCompletedDialog() {
        _uiState.value = _uiState.value.copy(showGameCompletedDialog = false)
    }

    fun toggleMute() {
        val muted = SoundEffectsManager.toggleMute()
        _uiState.value = _uiState.value.copy(isMuted = muted)
    }

    fun resetToBookLine() {
        jumpToPly(_uiState.value.currentPly)
    }

    fun toggleHistoricalDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showHistoricalIntroDialog = show)
    }

    fun setBoardTheme(theme: BoardTheme) {
        _uiState.value = _uiState.value.copy(selectedBoardTheme = theme)
    }

    fun setPieceStyle(style: PieceStyle) {
        _uiState.value = _uiState.value.copy(selectedPieceStyle = style)
    }

    fun toggleAppearanceDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showAppearanceDialog = show)
    }

    fun toggleBoardThemeDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showBoardThemeDialog = show)
    }

    fun togglePieceStyleDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showPieceStyleDialog = show)
    }

    fun resetBoardTheme() {
        _uiState.value = _uiState.value.copy(selectedBoardTheme = BoardTheme.CLASSIC_WALNUT)
    }

    fun resetPieceStyle() {
        _uiState.value = _uiState.value.copy(selectedPieceStyle = PieceStyle.CLASSIC_STAUNTON)
    }

    fun resetAppearanceDefaults() {
        _uiState.value = _uiState.value.copy(
            selectedBoardTheme = BoardTheme.CLASSIC_WALNUT,
            selectedPieceStyle = PieceStyle.CLASSIC_STAUNTON
        )
    }

    private fun triggerEngineEvaluation(state: BoardState) {
        if (!_uiState.value.isEngineEnabled) return
        engineJob?.cancel()
        _uiState.value = _uiState.value.copy(isEvaluating = true)

        engineJob = viewModelScope.launch {
            val eval = StockfishEngine.analyze(state, targetDepth = 4)
            _uiState.value = _uiState.value.copy(
                engineEvaluation = eval,
                isEvaluating = false
            )
        }
    }
}
