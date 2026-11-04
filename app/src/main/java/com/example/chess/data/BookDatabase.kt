package com.example.chess.data

import com.example.chess.data.games.AlekhineEarlyGames
import com.example.chess.data.games.AlekhineGoldenEraGames
import com.example.chess.data.games.CapablancaEarlyGames
import com.example.chess.data.games.CapablancaLateGames
import com.example.chess.data.games.LaskerEarlyGames
import com.example.chess.data.games.LaskerLateGames
import com.example.chess.data.games.PioneersRomanticGames
import com.example.chess.data.games.SteinitzEarlyGames
import com.example.chess.data.games.SteinitzLateGames

data class GameChallengeItem(
    val game: BookGame,
    val moment: CriticalMoment
)

object BookDatabase {

    private val allGamesList: List<BookGame> by lazy {
        PioneersRomanticGames.games +
        SteinitzEarlyGames.games +
        SteinitzLateGames.games +
        LaskerEarlyGames.games +
        LaskerLateGames.games +
        CapablancaEarlyGames.games +
        CapablancaLateGames.games +
        AlekhineEarlyGames.games +
        AlekhineGoldenEraGames.games
    }

    private val allChallengesList: List<GameChallengeItem> by lazy {
        allGamesList.flatMap { game ->
            game.criticalMoments.map { moment ->
                GameChallengeItem(game = game, moment = moment)
            }
        }
    }

    fun getAllGames(): List<BookGame> = allGamesList

    fun getAllChallenges(): List<GameChallengeItem> = allChallengesList

    fun getGameById(id: String): BookGame? = allGamesList.find { it.id == id }

    fun getGamesByChapter(chapter: GameChapter?): List<BookGame> {
        if (chapter == null) return allGamesList
        return allGamesList.filter { it.chapter == chapter }
    }

    fun getChallengesByChapter(chapter: GameChapter?): List<GameChallengeItem> {
        if (chapter == null) return allChallengesList
        return allChallengesList.filter { it.game.chapter == chapter }
    }

    fun searchGames(query: String, chapter: GameChapter? = null): List<BookGame> {
        val base = getGamesByChapter(chapter)
        val q = query.trim().lowercase()
        if (q.isEmpty()) return base

        return base.filter { game ->
            game.title.lowercase().contains(q) ||
            game.whitePlayer.lowercase().contains(q) ||
            game.blackPlayer.lowercase().contains(q) ||
            game.event.lowercase().contains(q) ||
            game.year.lowercase().contains(q) ||
            game.eco.lowercase().contains(q) ||
            game.openingName.lowercase().contains(q) ||
            game.strategicTheme.lowercase().contains(q)
        }
    }
}
