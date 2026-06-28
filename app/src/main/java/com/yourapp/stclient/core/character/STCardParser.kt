package com.sillytavern.kotlin.config

import java.util.UUID

object STCardParser {
    fun parseJsonLike(raw: String): STCharacter {
        fun field(name: String): String {
            val regex = Regex("\\\"$name\\\"\\s*:\\s*\\\"(.*?)\\\"", RegexOption.DOT_MATCHES_ALL)
            return regex.find(raw)?.groupValues?.getOrNull(1)?.replace("\\n", "\n") ?: ""
        }
        val name = field("name").ifBlank { "Imported Character" }
        return STCharacter(
            id = UUID.randomUUID().toString(),
            name = name,
            description = field("description"),
            personality = field("personality"),
            scenario = field("scenario"),
            firstMessage = field("first_mes").ifBlank { field("firstMessage") },
            exampleDialogue = field("mes_example"),
            creatorNotes = field("creator_notes")
        )
    }

    fun validate(character: STCharacter): List<String> = buildList {
        if (character.name.isBlank()) add("Character name is required.")
        if (character.description.isBlank()) add("Description is empty.")
        if (character.firstMessage.isBlank()) add("First message is empty.")
    }
}
