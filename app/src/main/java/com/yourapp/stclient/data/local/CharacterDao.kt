package com.sillytavern.kotlin.config

interface CharacterDao {
    suspend fun all(): List<STCharacter>
    suspend fun get(id: String): STCharacter?
    suspend fun upsert(character: STCharacter)
    suspend fun delete(id: String)
}

class InMemoryCharacterDao : CharacterDao {
    private val rows = linkedMapOf<String, STCharacter>()
    override suspend fun all(): List<STCharacter> = rows.values.sortedBy { it.name.lowercase() }
    override suspend fun get(id: String): STCharacter? = rows[id]
    override suspend fun upsert(character: STCharacter) { rows[character.id] = character }
    override suspend fun delete(id: String) { rows.remove(id) }
}
