package com.sillytavern.kotlin.engine

import com.sillytavern.kotlin.config.STCharacter

data class GroupChat(
    val id: String,
    val name: String,
    val members: List<STCharacter>,
    val scenario: String = ""
)
