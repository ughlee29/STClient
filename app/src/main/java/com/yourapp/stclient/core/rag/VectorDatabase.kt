package com.sillytavern.kotlin.engine

import kotlin.math.sqrt

data class VectorRecord(val id: String, val text: String, val vector: FloatArray)

class VectorDatabase {
    private val rows = linkedMapOf<String, VectorRecord>()
    fun upsert(record: VectorRecord) { rows[record.id] = record }
    fun search(query: FloatArray, limit: Int): List<VectorRecord> = rows.values
        .sortedByDescending { cosine(query, it.vector) }
        .take(limit)

    private fun cosine(a: FloatArray, b: FloatArray): Float {
        val n = minOf(a.size, b.size)
        var dot = 0f; var aa = 0f; var bb = 0f
        for (i in 0 until n) { dot += a[i] * b[i]; aa += a[i] * a[i]; bb += b[i] * b[i] }
        return if (aa == 0f || bb == 0f) 0f else dot / (sqrt(aa) * sqrt(bb))
    }
}
