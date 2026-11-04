package com.example.chess.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.engine.EngineEvaluation

@Composable
fun VerticalEvaluationBar(
    evaluation: EngineEvaluation?,
    isFlipped: Boolean = false,
    modifier: Modifier = Modifier
) {
    val winPct = evaluation?.winPercentageWhite ?: 50f
    val animatedPct by animateFloatAsState(
        targetValue = winPct,
        animationSpec = tween(durationMillis = 350),
        label = "eval_anim"
    )

    // Percentage of bar that belongs to white
    val whiteRatio = (animatedPct / 100f).coerceIn(0.04f, 0.96f)
    val topRatio = if (isFlipped) whiteRatio else (1f - whiteRatio)
    val bottomRatio = 1f - topRatio

    Box(
        modifier = modifier
            .width(26.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(6.dp))
            .border(1.dp, Color(0xFF3B4252), RoundedCornerShape(6.dp))
            .background(Color(0xFF2E3440))
            .testTag("evaluation_bar")
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Black portion (top if normal, bottom if flipped)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(topRatio)
                    .background(if (isFlipped) Color(0xFFF0EFEA) else Color(0xFF262628)),
                contentAlignment = Alignment.TopCenter
            ) {
                if (evaluation != null && ((!isFlipped && evaluation.scoreCp < 0) || (isFlipped && evaluation.scoreCp > 0))) {
                    Text(
                        text = evaluation.formattedScore.replace("+", ""),
                        color = if (isFlipped) Color.Black else Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // White portion
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(bottomRatio)
                    .background(if (isFlipped) Color(0xFF262628) else Color(0xFFF0EFEA)),
                contentAlignment = Alignment.BottomCenter
            ) {
                if (evaluation != null && ((!isFlipped && evaluation.scoreCp >= 0) || (isFlipped && evaluation.scoreCp < 0))) {
                    Text(
                        text = evaluation.formattedScore,
                        color = if (isFlipped) Color.White else Color.Black,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}
