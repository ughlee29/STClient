package com.sillytavern.kotlin.engine

object EmbeddingProviderGuide {
    val notes = listOf(
        "Use ONNX for simple bundled embeddings.",
        "Use llama.cpp bindings for local model parity.",
        "Keep dimensions consistent inside VectorDatabase."
    )
}
