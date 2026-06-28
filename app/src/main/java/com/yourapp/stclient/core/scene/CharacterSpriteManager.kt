package com.sillytavern.kotlin.engine

class CharacterSpriteManager {
    private val sprites = mutableMapOf<String, MutableMap<String, String>>()
    fun setSprite(characterId: String, emotion: String, uri: String) { sprites.getOrPut(characterId) { mutableMapOf() }[emotion] = uri }
    fun sprite(characterId: String, emotion: String): String? = sprites[characterId]?.get(emotion) ?: sprites[characterId]?.get("neutral")
}
