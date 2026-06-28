package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sillytavern.kotlin.config.ChatMessage

@Composable
fun StreamingMessageBubble(message: ChatMessage, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(message.role.name)
            Text(message.content)
            if (message.isStreaming) CircularProgressIndicator()
            BranchIndicator(current = message.branchIndex + 1, total = maxOf(1, message.branchIndex + 1))
        }
    }
}
