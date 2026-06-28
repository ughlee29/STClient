package com.sillytavern.kotlin.engine

data class CloudModel(val id: String, val name: String, val contextTokens: Int, val supportsStreaming: Boolean = true)

data class LLMRequest(
    val prompt: String,
    val model: String = "",
    val temperature: Double = 0.8,
    val maxTokens: Int = 1024
)

data class LLMResponse(val text: String, val model: String = "", val usageTokens: Int = 0)
