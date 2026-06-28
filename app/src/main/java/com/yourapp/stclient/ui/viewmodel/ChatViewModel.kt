package com.sillytavern.kotlin.engine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sillytavern.kotlin.config.ChatMessage
import com.sillytavern.kotlin.config.MessageRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ChatViewModel(
    private val engine: LLMEngine = LocalLLMEngine(),
    private val promptBuilder: STPromptBuilder = STPromptBuilder()
) : ViewModel() {
    private val chatId = UUID.randomUUID().toString()
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    fun sendUserMessage(text: String) {
        val user = ChatMessage(chatId = chatId, role = MessageRole.USER, content = text)
        _messages.value = _messages.value + user
        viewModelScope.launch {
            val prompt = promptBuilder.build(_messages.value)
            val reply = engine.complete(prompt)
            _messages.value = _messages.value + ChatMessage(chatId = chatId, parentId = user.id, role = MessageRole.ASSISTANT, content = reply)
        }
    }
}
