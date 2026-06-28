package com.sillytavern.kotlin.engine

class TranslationPipeline(private val detector: LanguageDetector, private val provider: TranslationProvider) {
    suspend fun translateIfNeeded(text: String, targetLanguage: String): String {
        val source = detector.detect(text)
        return if (source == targetLanguage) text else provider.translate(text, source, targetLanguage)
    }
}
