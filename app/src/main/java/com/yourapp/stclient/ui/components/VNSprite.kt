package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VNSprite(name: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(180.dp)) { Text(name) }
}
