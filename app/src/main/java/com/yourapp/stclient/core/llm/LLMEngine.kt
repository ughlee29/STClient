package com.sillytavern.kotlin.engine

interface LLMEngine {
    suspend fun complete(prompt: String): String
}
