package com.example.chess.gamification

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.example.R

object SoundEffectsManager {

    private var soundPool: SoundPool? = null
    private var moveSoundId: Int = 0
    private var captureSoundId: Int = 0
    private var isLoaded = false
    private var isMuted = false

    fun initialize(context: Context) {
        if (soundPool != null) return

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(4)
            .setAudioAttributes(audioAttributes)
            .build()

        soundPool?.let { pool ->
            moveSoundId = pool.load(context, R.raw.chess_move, 1)
            captureSoundId = pool.load(context, R.raw.chess_capture, 1)
            pool.setOnLoadCompleteListener { _, _, status ->
                if (status == 0) {
                    isLoaded = true
                }
            }
        }
    }

    fun playMove() {
        if (!isMuted && isLoaded && moveSoundId != 0) {
            soundPool?.play(moveSoundId, 0.8f, 0.8f, 1, 0, 1.0f)
        }
    }

    fun playCapture() {
        if (!isMuted && isLoaded && captureSoundId != 0) {
            soundPool?.play(captureSoundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    fun toggleMute(): Boolean {
        isMuted = !isMuted
        return isMuted
    }

    fun isMuted(): Boolean = isMuted
}
