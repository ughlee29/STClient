package com.sillytavern.kotlin.engine

interface EmbeddingProvider {
    val dimension: Int
    suspend fun embed(text: String): FloatArray
}
