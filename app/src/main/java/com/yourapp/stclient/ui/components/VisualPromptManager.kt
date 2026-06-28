package com.sillytavern.kotlin.ui

class VisualPromptManager {
    fun buildPrompt(characterName: String, scene: String, emotion: String): String =
        listOf(characterName, scene, emotion).filter { it.isNotBlank() }.joinToString(", ")
}
