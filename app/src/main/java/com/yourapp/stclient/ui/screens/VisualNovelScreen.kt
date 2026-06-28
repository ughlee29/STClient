package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun VisualNovelScreen(speaker: String, line: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        DynamicSpriteOverlay(spriteNames = listOf(speaker), modifier = Modifier.align(Alignment.Center))
        VNDialogueBox(speaker = speaker, line = line, modifier = Modifier.align(Alignment.BottomCenter))
    }
}
