package com.sillytavern.kotlin.engine

interface TranslationProvider {
    suspend fun translate(text: String, sourceLanguage: String, targetLanguage: String): String
}
