package com.example.chess.data

import androidx.annotation.DrawableRes
import com.example.R

data class AnnotatedMove(
    val ply: Int,               // 1-indexed ply (1 = 1.e4, 2 = 1...e5)
    val moveNumber: Int,        // 1, 1, 2, 2...
    val isWhite: Boolean,
    val san: String,            // Standard Algebraic Notation, e.g., "e4", "Nf3", "Bxf7+"
    val glyph: String? = null,  // "!", "!!", "?", "!?", etc.
    val commentary: String? = null, // Paraphrased master analysis in Spanish
    val fenAfter: String        // FEN after this move is played
) {
    val formattedMove: String
        get() = if (isWhite) "$moveNumber. $san${glyph ?: ""}" else "$moveNumber... $san${glyph ?: ""}"
}

data class CriticalMoment(
    val id: String,
    val plyBefore: Int,         // Ply index right before the critical move is played (0 = initial position)
    val title: String,          // Short title of the tactical/strategic challenge
    val prompt: String,         // Question posed to the user
    val hint: String,           // Strategic/tactical hint without giving away the exact square
    val solutionSan: String,    // Expected move in SAN (e.g., "Bxh7+", "Rxc3", "Bc5")
    val explanation: String     // Detailed explanation once solved or revealed
)

enum class GameChapter(val displayName: String, val subtitle: String, val period: String) {
    PIONEERS("Era Romántica y Steinitz", "Del ataque directo al nacimiento de la escuela posicional", "1851 – 1895"),
    LASKER("La Era de Emanuel Lasker", "Psicología competitiva, lucha práctica y el Congreso de 1909", "1889 – 1914"),
    CAPABLANCA("La Era de Capablanca", "Claridad cristalina, técnica de finales y armonía posicional", "1907 – 1924"),
    ALEKHINE("La Era de Alekhine", "Dinamismo concreto, iniciativa e hipermodernismo", "1925 – 1935")
}

@get:DrawableRes
val GameChapter.portraitRes: Int
    get() = when (this) {
        GameChapter.PIONEERS -> R.drawable.portrait_wilhelm_steinitz
        GameChapter.LASKER -> R.drawable.portrait_emanuel_lasker
        GameChapter.CAPABLANCA -> R.drawable.portrait_jose_raul_capablanca
        GameChapter.ALEKHINE -> R.drawable.portrait_alexander_alekhine
    }

@get:DrawableRes
val GameChapter.dividerRes: Int
    get() = when (this) {
        GameChapter.PIONEERS, GameChapter.LASKER -> R.drawable.divider_romantic_to_strategic
        GameChapter.CAPABLANCA, GameChapter.ALEKHINE -> R.drawable.divider_strategic_to_russian
    }

data class BookGame(
    val id: String,
    val gameNumber: Int,
    val title: String,
    val whitePlayer: String,
    val blackPlayer: String,
    val event: String,
    val year: String,
    val result: String, // "1-0", "0-1", "1/2-1/2"
    val eco: String,
    val openingName: String,
    val chapter: GameChapter,
    val historicalIntroduction: String,
    val strategicTheme: String,
    val moves: List<AnnotatedMove>,
    val criticalMoments: List<CriticalMoment> = emptyList()
)

@get:DrawableRes
val BookGame.portraitRes: Int
    get() = when {
        whitePlayer.contains("Capablanca", ignoreCase = true) || blackPlayer.contains("Capablanca", ignoreCase = true) -> R.drawable.portrait_jose_raul_capablanca
        whitePlayer.contains("Alekhine", ignoreCase = true) || blackPlayer.contains("Alekhine", ignoreCase = true) -> R.drawable.portrait_alexander_alekhine
        whitePlayer.contains("Lasker", ignoreCase = true) || blackPlayer.contains("Lasker", ignoreCase = true) -> R.drawable.portrait_emanuel_lasker
        whitePlayer.contains("Steinitz", ignoreCase = true) || blackPlayer.contains("Steinitz", ignoreCase = true) -> R.drawable.portrait_wilhelm_steinitz
        whitePlayer.contains("Anderssen", ignoreCase = true) || blackPlayer.contains("Anderssen", ignoreCase = true) -> R.drawable.portrait_adolf_anderssen
        whitePlayer.contains("Morphy", ignoreCase = true) || blackPlayer.contains("Morphy", ignoreCase = true) -> R.drawable.portrait_paul_morphy
        whitePlayer.contains("Rubinstein", ignoreCase = true) || blackPlayer.contains("Rubinstein", ignoreCase = true) -> R.drawable.portrait_akiba_rubinstein
        chapter == GameChapter.CAPABLANCA -> R.drawable.portrait_jose_raul_capablanca
        chapter == GameChapter.ALEKHINE -> R.drawable.portrait_alexander_alekhine
        chapter == GameChapter.LASKER -> R.drawable.portrait_emanuel_lasker
        else -> R.drawable.portrait_wilhelm_steinitz
    }

