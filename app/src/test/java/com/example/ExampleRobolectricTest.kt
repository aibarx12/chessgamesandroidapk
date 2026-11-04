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
}
