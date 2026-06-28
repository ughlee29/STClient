package com.sillytavern.kotlin.engine

class RAGPipeline(private val embeddings: EmbeddingProvider, private val db: VectorDatabase) {
    suspend fun add(id: String, text: String) { db.upsert(VectorRecord(id, text, embeddings.embed(text))) }
    suspend fun retrieve(query: String, limit: Int = 5): List<VectorRecord> = db.search(embeddings.embed(query), limit)
}
