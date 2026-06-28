package com.sillytavern.kotlin.config

import java.util.UUID

enum class MessageRole { SYSTEM, USER, ASSISTANT, CHARACTER, TOOL }

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val chatId: String,
    val role: MessageRole,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val parentId: String? = null,
    val branchGroup: String = UUID.randomUUID().toString(),
    val branchIndex: Int = 0,
    val isStreaming: Boolean = false,
    val tokenCount: Int = 0
)
