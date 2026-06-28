package com.sillytavern.kotlin.engine

class LocalLLMEngine : LLMEngine {
    override suspend fun complete(prompt: String): String = "Local engine placeholder response. Prompt size=${prompt.length}."
}
