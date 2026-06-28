package com.sillytavern.kotlin.config

data class STCharacter(
    val id: String,
    val name: String,
    val description: String = "",
    val personality: String = "",
    val scenario: String = "",
    val firstMessage: String = "",
    val exampleDialogue: String = "",
    val creatorNotes: String = "",
    val tags: List<String> = emptyList(),
    val lorebookEntries: List<String> = emptyList(),
    val avatarUri: String? = null
)
