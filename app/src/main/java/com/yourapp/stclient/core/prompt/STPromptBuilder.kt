package com.sillytavern.kotlin.engine

import com.sillytavern.kotlin.config.ChatMessage

class STPromptBuilder {
    fun build(messages: List<ChatMessage>, systemPrompt: String = ""): String = buildString {
        if (systemPrompt.isNotBlank()) appendLine("System: $systemPrompt")
        messages.forEach { appendLine("${it.role.name.lowercase()}: ${it.content}") }
    }
}
