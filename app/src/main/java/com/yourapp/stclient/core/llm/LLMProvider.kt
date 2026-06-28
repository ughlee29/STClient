package com.sillytavern.kotlin.engine

interface LLMProvider {
    val id: String
    val displayName: String
    suspend fun generate(request: LLMRequest): LLMResponse
}
