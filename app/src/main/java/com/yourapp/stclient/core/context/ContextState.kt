package com.sillytavern.kotlin.engine

data class ContextState(
    val usedTokens: Int,
    val maxTokens: Int,
    val reservedResponseTokens: Int
) {
    val remainingTokens: Int get() = (maxTokens - usedTokens - reservedResponseTokens).coerceAtLeast(0)
    val pressure: Float get() = if (maxTokens == 0) 0f else usedTokens.toFloat() / maxTokens
}
