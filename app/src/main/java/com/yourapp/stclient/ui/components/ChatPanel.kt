package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sillytavern.kotlin.config.ChatMessage

@Composable
fun ChatPanel(messages: List<ChatMessage>, onSend: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages, key = { it.id }) { message ->
                StreamingMessageBubble(message = message)
            }
        }
        BottomInputBar(onSend = onSend)
    }
}
