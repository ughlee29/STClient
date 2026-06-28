package com.sillytavern.kotlin.engine

class ApiKeyManager {
    private val keys = mutableMapOf<String, String>()
    fun set(providerId: String, key: String) { keys[providerId] = key }
    fun get(providerId: String): String? = keys[providerId]
    fun clear(providerId: String) { keys.remove(providerId) }
}
