package com.sillytavern.kotlin.engine

class MemoryGuard(private val maxBytes: Long) {
    fun canAllocate(bytes: Long): Boolean = bytes in 0..maxBytes
    fun usageWarning(usedBytes: Long): String? = if (usedBytes > maxBytes * 0.85) "Memory pressure high" else null
}
