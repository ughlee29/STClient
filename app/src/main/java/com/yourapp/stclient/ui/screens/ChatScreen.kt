package com.sillytavern.kotlin.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sillytavern.kotlin.engine.ChatViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel, modifier: Modifier = Modifier) {
    val messages by viewModel.messages.collectAsState()
    ChatPanel(messages = messages, onSend = viewModel::sendUserMessage, modifier = modifier)
}
