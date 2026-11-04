package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.chess.data.BookDatabase
import com.example.chess.model.BoardState
import com.example.chess.model.SanParser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Predecesores", appName)
  }

  @Test
  fun `main activity launches without crashing`() {
    val controller = Robolectric.buildActivity(MainActivity::class.java).setup()
    val activity = controller.get()
    assertNotNull(activity)
  }

  @Test
  fun `all games and critical moments are valid and free of kasparov references`() {
    val games = BookDatabase.getAllGames()
    assertTrue("Expected at least 20 games including St Petersburg 1909", games.size >= 20)

    val errors = mutableListOf<String>()
    for (game in games) {
      if (game.moves.isEmpty()) {
        errors.add("Game ${game.id} has NO moves")
        continue
      }
      if (game.criticalMoments.isEmpty()) {
        errors.add("Game ${game.id} has NO critical moments")
      }
      if (game.historicalIntroduction.lowercase().contains("kasparov")) {
        errors.add("Game ${game.id} intro contains kasparov")
      }
      for (move in game.moves) {
        val comment = move.commentary ?: ""
        if (comment.lowercase().contains("kasparov")) {
          errors.add("Game ${game.id} move commentary contains kasparov")
        }
      }
      for (moment in game.criticalMoments) {
        if (game.moves.size <= moment.plyBefore) {
          errors.add("Game ${game.id} moves=${game.moves.size} <= moment ${moment.id} ply=${moment.plyBefore}")
          continue
        }
        val stateBefore = if (moment.plyBefore == 0) {
          BoardState.initial()
        } else {
          BoardState.fromFen(game.moves[moment.plyBefore - 1].fenAfter)
        }
        val parsedSolution = SanParser.parseSan(stateBefore, moment.solutionSan)
        if (parsedSolution == null) {
          errors.add("Game ${game.id} moment ${moment.id} ply=${moment.plyBefore} invalid solution SAN '${moment.solutionSan}'")
        }
      }
    }
    assertTrue("Encountered errors (${errors.size}):\n" + errors.joinToString("\n"), errors.isEmpty())
  }

  @Test
  fun `board theme and piece style customizations function correctly`() {
    val themes = com.example.chess.model.BoardTheme.entries
    assertTrue("At least 6 board themes available", themes.size >= 6)
    for (theme in themes) {
      assertNotNull(theme.displayName)
      assertNotNull(theme.lightSquare)
      assertNotNull(theme.darkSquare)
      assertTrue("Light and dark squares should differ", theme.lightSquare != theme.darkSquare)
    }

    val styles = com.example.chess.model.PieceStyle.entries
    assertTrue("At least 6 piece styles available", styles.size >= 6)
    for (style in styles) {
      assertNotNull(style.displayName)
      assertNotNull(style.shapeFamily)
      assertNotNull(style.whiteFill)
      assertNotNull(style.blackFill)
    }

    val shapeFamilies = com.example.chess.model.PieceShapeFamily.entries
    assertTrue("Shape families should include Lichess inspirations", shapeFamilies.size >= 5)
    assertTrue(shapeFamilies.contains(com.example.chess.model.PieceShapeFamily.CBURNETT))
    assertTrue(shapeFamilies.contains(com.example.chess.model.PieceShapeFamily.MERIDA))
    assertTrue(shapeFamilies.contains(com.example.chess.model.PieceShapeFamily.ALPHA))
    assertTrue(shapeFamilies.contains(com.example.chess.model.PieceShapeFamily.DUBROVNIK))

    val viewModel = com.example.chess.ui.ChessViewModel()
    assertEquals(com.example.chess.model.BoardTheme.CLASSIC_WALNUT, viewModel.uiState.value.selectedBoardTheme)
    assertEquals(com.example.chess.model.PieceStyle.CLASSIC_STAUNTON, viewModel.uiState.value.selectedPieceStyle)

    // Switch theme
    viewModel.setBoardTheme(com.example.chess.model.BoardTheme.WARM_WOOD)
    assertEquals(com.example.chess.model.BoardTheme.WARM_WOOD, viewModel.uiState.value.selectedBoardTheme)

    // Switch style
    viewModel.setPieceStyle(com.example.chess.model.PieceStyle.MERIDA)
    assertEquals(com.example.chess.model.PieceStyle.MERIDA, viewModel.uiState.value.selectedPieceStyle)

    // Toggle board theme dialog
    viewModel.toggleBoardThemeDialog(true)
    assertTrue(viewModel.uiState.value.showBoardThemeDialog)
    viewModel.toggleBoardThemeDialog(false)
    assertFalse(viewModel.uiState.value.showBoardThemeDialog)

    // Toggle piece style dialog
    viewModel.togglePieceStyleDialog(true)
    assertTrue(viewModel.uiState.value.showPieceStyleDialog)
    viewModel.togglePieceStyleDialog(false)
    assertFalse(viewModel.uiState.value.showPieceStyleDialog)

    // Toggle legacy appearance dialog
    viewModel.toggleAppearanceDialog(true)
    assertTrue(viewModel.uiState.value.showAppearanceDialog)
    viewModel.toggleAppearanceDialog(false)
    assertFalse(viewModel.uiState.value.showAppearanceDialog)

    // Reset defaults
    viewModel.resetAppearanceDefaults()
    assertEquals(com.example.chess.model.BoardTheme.CLASSIC_WALNUT, viewModel.uiState.value.selectedBoardTheme)
    assertEquals(com.example.chess.model.PieceStyle.CLASSIC_STAUNTON, viewModel.uiState.value.selectedPieceStyle)
  }

  @Test
  fun `journey 1 by 1 unlocking and user registration individual scores work correctly`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    com.example.chess.gamification.GamificationRepository.resetAllDataForTesting(context)

    val allGames = BookDatabase.getAllGames()
    val game1 = allGames[0]
    val game2 = allGames[1]
    val game3 = allGames[2]

    // 1. Starts at HOME with only Game #1 unlocked
    val initialState = com.example.chess.gamification.GamificationRepository.progressState.value
    assertTrue(initialState.characterAtHome)
    assertEquals(0, initialState.unlockedUpToIndex)
    assertTrue(com.example.chess.gamification.GamificationRepository.isGameUnlocked(game1.id))
    assertFalse(com.example.chess.gamification.GamificationRepository.isGameUnlocked(game2.id))

    // 2. Solve Game #1's critical moment -> saves individual score & unlocks Game #2 (1 by 1)
    val earned1 = com.example.chess.gamification.GamificationRepository.recordChallengeResult(
      momentId = game1.criticalMoments.first().id,
      gameTitle = game1.title,
      wrongAttempts = 0,
      hintUsed = false,
      revealed = false
    )
    assertEquals(300, earned1)
    val afterGame1 = com.example.chess.gamification.GamificationRepository.progressState.value
    assertFalse(afterGame1.characterAtHome)
    assertEquals(1, afterGame1.unlockedUpToIndex)
    assertTrue(com.example.chess.gamification.GamificationRepository.isGameUnlocked(game2.id))
    assertFalse(com.example.chess.gamification.GamificationRepository.isGameUnlocked(game3.id))
    assertEquals(300, afterGame1.individualGameScores[game1.id]?.totalScore)

    // 3. Register User 1 migrating guest scores
    val reg1 = com.example.chess.gamification.GamificationRepository.registerUser(
      username = "JugadorUno",
      emailOrTag = "uno@club.es",
      pin = "1234",
      avatar = com.example.chess.gamification.PlayerAvatar.PAUL_MORPHY,
      migrateGuestProgress = true
    )
    assertTrue(reg1 is com.example.chess.gamification.AuthResult.Success)
    val user1Id = (reg1 as com.example.chess.gamification.AuthResult.Success).user.id

    // Complete Game #2 full study -> adds +100 individual study pts & unlocks Game #3
    com.example.chess.gamification.GamificationRepository.recordGamePlyProgress(
      gameId = game2.id,
      gameTitle = game2.title,
      currentPly = game2.moves.size,
      totalPlies = game2.moves.size
    )
    val afterGame2 = com.example.chess.gamification.GamificationRepository.progressState.value
    assertEquals(2, afterGame2.unlockedUpToIndex)
    assertTrue(com.example.chess.gamification.GamificationRepository.isGameUnlocked(game3.id))
    assertEquals(100, afterGame2.individualGameScores[game2.id]?.totalScore)
    val user1SavedXp = afterGame2.totalXp

    // 4. Register User 2 starting fresh at HOME
    val reg2 = com.example.chess.gamification.GamificationRepository.registerUser(
      username = "JugadorDos",
      pin = "",
      avatar = com.example.chess.gamification.PlayerAvatar.CAPABLANCA,
      migrateGuestProgress = false
    )
    assertTrue(reg2 is com.example.chess.gamification.AuthResult.Success)
    val user2State = com.example.chess.gamification.GamificationRepository.progressState.value
    assertEquals("JugadorDos", user2State.displayUsername)
    assertTrue(user2State.characterAtHome)
    assertEquals(0, user2State.unlockedUpToIndex)
    assertEquals(0, user2State.totalXp)
    assertTrue(user2State.individualGameScores.isEmpty())

    // 5. Switch back to User 1 with PIN -> restores all individual scores and unlocked games
    val wrongLogin = com.example.chess.gamification.GamificationRepository.loginUser(user1Id, "9999")
    assertTrue(wrongLogin is com.example.chess.gamification.AuthResult.Error)

    val okLogin = com.example.chess.gamification.GamificationRepository.loginUser(user1Id, "1234")
    assertTrue(okLogin is com.example.chess.gamification.AuthResult.Success)
    val restoredUser1 = com.example.chess.gamification.GamificationRepository.progressState.value
    assertEquals("JugadorUno", restoredUser1.displayUsername)
    assertEquals(2, restoredUser1.unlockedUpToIndex)
    assertEquals(user1SavedXp, restoredUser1.totalXp)
    assertEquals(300, restoredUser1.individualGameScores[game1.id]?.totalScore)
    assertEquals(100, restoredUser1.individualGameScores[game2.id]?.totalScore)
  }
}
