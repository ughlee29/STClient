package com.sillytavern.kotlin.engine

class OpenAIClient(private val apiKeyManager: ApiKeyManager) : LLMProvider {
    override val id: String = "openai-compatible"
    override val displayName: String = "OpenAI Compatible"

    override suspend fun generate(request: LLMRequest): LLMResponse {
        val key = apiKeyManager.get(id).orEmpty()
        require(key.isNotBlank()) { "API key is missing for $id" }
        return LLMResponse(text = "Network call placeholder for model ${request.model}", model = request.model)
    }
}
