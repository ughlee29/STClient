package com.sillytavern.kotlin.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sillytavern.kotlin.config.ChatMessage

@Composable
fun SwipeableMessageBubble(message: ChatMessage, onSwipeLeft: () -> Unit, onSwipeRight: () -> Unit, modifier: Modifier = Modifier) {
    StreamingMessageBubble(message = message, modifier = modifier)
}
