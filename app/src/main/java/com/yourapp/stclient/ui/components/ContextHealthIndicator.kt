package com.sillytavern.kotlin.ui

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column

@Composable
fun ContextHealthIndicator(usedTokens: Int, maxTokens: Int) {
    val fraction = if (maxTokens <= 0) 0f else (usedTokens.toFloat() / maxTokens).coerceIn(0f, 1f)
    Column {
        Text("Context: $usedTokens / $maxTokens")
        LinearProgressIndicator(progress = { fraction })
    }
}
