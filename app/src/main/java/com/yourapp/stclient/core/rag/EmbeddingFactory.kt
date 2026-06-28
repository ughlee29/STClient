package com.sillytavern.kotlin.engine

object EmbeddingFactory {
    fun create(type: String): EmbeddingProvider = when (type.lowercase()) {
        "llama.cpp", "llama" -> LlamaCppEmbeddingProvider()
        "onnx" -> OnnxEmbeddingProvider()
        else -> OnnxEmbeddingProvider()
    }
}
