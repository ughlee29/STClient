package com.sillytavern.kotlin.engine

class ContextBudgetManager(private val maxTokens: Int) {
    fun estimateTokens(text: String): Int = (text.length / 4).coerceAtLeast(1)
    fun state(texts: List<String>, reserved: Int): ContextState = ContextState(texts.sumOf { estimateTokens(it) }, maxTokens, reserved)
}
