package com.example.chess.data

import android.util.Log
import com.example.chess.model.BoardState
import com.example.chess.model.SanParser

object GameDataBuilder {

    data class RawMoveAnnotation(
        val san: String,
        val glyph: String? = null,
        val commentary: String? = null
    )

    fun buildMoves(rawMoves: List<RawMoveAnnotation>): List<AnnotatedMove> {
        val list = mutableListOf<AnnotatedMove>()
        var currentState = BoardState.initial()
        var ply = 0

        for (item in rawMoves) {
            val move = SanParser.parseSan(currentState, item.san)
            if (move == null) {
                Log.w("GameDataBuilder", "Could not parse move '${item.san}' at ply $ply (${currentState.toFen()})")
                break
            }

            currentState = currentState.makeMove(move)
            ply++
            val moveNumber = (ply + 1) / 2
            val isWhite = ply % 2 != 0

            list.add(
                AnnotatedMove(
                    ply = ply,
                    moveNumber = moveNumber,
                    isWhite = isWhite,
                    san = item.san,
                    glyph = item.glyph,
                    commentary = item.commentary,
                    fenAfter = currentState.toFen()
                )
            )
        }
        return list
    }

    fun buildFromPgn(
        movesText: String,
        annotations: Map<Int, Pair<String?, String?>> = emptyMap()
    ): List<AnnotatedMove> {
        val tokens = movesText.trim().split(Regex("\\s+"))
        val rawMoves = mutableListOf<RawMoveAnnotation>()
        var plyIndex = 1
        for (token in tokens) {
            val clean = token.trim()
            if (clean.isEmpty()) continue
            if (clean.matches(Regex("^\\d+\\.+$")) || clean.matches(Regex("^\\d+\\.\\.\\.$"))) continue
            if (clean in listOf("1-0", "0-1", "1/2-1/2", "*")) continue
            val san = clean.replace(Regex("^\\d+\\.\\.?"), "")
            if (san.isEmpty()) continue
            val ann = annotations[plyIndex]
            rawMoves.add(RawMoveAnnotation(san = san, glyph = ann?.first, commentary = ann?.second))
            plyIndex++
        }
        return buildMoves(rawMoves)
    }
}
