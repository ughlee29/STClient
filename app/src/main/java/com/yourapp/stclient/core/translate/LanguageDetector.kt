package com.sillytavern.kotlin.engine

class LanguageDetector {
    fun detect(text: String): String = when {
        text.any { it in '\u3040'..'\u30ff' } -> "ja"
        text.any { it in '\u4e00'..'\u9fff' } -> "zh"
        else -> "en"
    }
}
