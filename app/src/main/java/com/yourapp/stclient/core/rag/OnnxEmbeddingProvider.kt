package com.sillytavern.kotlin.engine

class OnnxEmbeddingProvider : EmbeddingProvider {
    override val dimension: Int = 384
    override suspend fun embed(text: String): FloatArray {
        val seed = text.fold(0) { acc, c -> acc + c.code }
        return FloatArray(dimension) { ((seed + it * 31) % 997) / 997f }
    }
}
