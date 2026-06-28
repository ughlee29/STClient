package com.sillytavern.kotlin.engine

class LlamaCppEmbeddingProvider : EmbeddingProvider {
    override val dimension: Int = 384
    override suspend fun embed(text: String): FloatArray = FloatArray(dimension) { index -> ((text.hashCode() + index) % 100) / 100f }
}
