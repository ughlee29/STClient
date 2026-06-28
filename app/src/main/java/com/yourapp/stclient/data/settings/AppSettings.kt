package com.sillytavern.kotlin.config

data class AppSettings(
    val useCloudInference: Boolean = true,
    val selectedProvider: String = "openai-compatible",
    val selectedModel: String = "",
    val temperature: Double = 0.8,
    val maxOutputTokens: Int = 1024,
    val contextWindowTokens: Int = 32768,
    val streamResponses: Boolean = true,
    val ttsEnabled: Boolean = false,
    val imageGenerationEnabled: Boolean = false,
    val accessibilityLargeText: Boolean = false,
    val visualNovelMode: Boolean = false
)
