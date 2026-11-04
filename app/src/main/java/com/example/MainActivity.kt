package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chess.gamification.GamificationRepository
import com.example.chess.gamification.SoundEffectsManager
import com.example.chess.ui.ChessViewModel
import com.example.chess.ui.GameListScreen
import com.example.chess.ui.GameViewerScreen
import com.example.chess.ui.Screen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GamificationRepository.initialize(applicationContext)
        SoundEffectsManager.initialize(applicationContext)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val chessViewModel: ChessViewModel = viewModel()
                val uiState by chessViewModel.uiState.collectAsState()

                Surface(modifier = Modifier.fillMaxSize()) {
                    Crossfade(
                        targetState = uiState.currentScreen,
                        label = "screen_crossfade"
                    ) { screen ->
                        when (screen) {
                            Screen.GAME_LIST -> {
                                GameListScreen(viewModel = chessViewModel)
                            }
                            Screen.GAME_VIEWER -> {
                                GameViewerScreen(viewModel = chessViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}
