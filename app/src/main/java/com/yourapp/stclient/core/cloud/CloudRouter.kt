package com.sillytavern.kotlin.engine

class CloudRouter(private val providers: List<LLMProvider>) {
    suspend fun generate(providerId: String, request: LLMRequest): LLMResponse {
        val provider = providers.firstOrNull { it.id == providerId } ?: error("Provider not registered: $providerId")
        return provider.generate(request)
    }
}
