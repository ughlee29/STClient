package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DynamicSpriteOverlay(spriteNames: List<String>, modifier: Modifier = Modifier) {
    Row(modifier = modifier) { spriteNames.forEach { VNSprite(name = it) } }
}
