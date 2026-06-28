package com.sillytavern.kotlin.engine

class SmartContextCleaner(private val budget: ContextBudgetManager) {
    fun trimToBudget(chunks: List<String>, reservedTokens: Int, maxTokens: Int): List<String> {
        val kept = ArrayDeque<String>()
        var used = reservedTokens
        for (chunk in chunks.asReversed()) {
            val tokens = budget.estimateTokens(chunk)
            if (used + tokens > maxTokens) break
            kept.addFirst(chunk)
            used += tokens
        }
        return kept.toList()
    }
}
