package com.sillytavern.kotlin.engine

object JNIMemoryLeakTest {
    external fun nativePing(): String
    fun safePing(): String = runCatching { nativePing() }.getOrDefault("jni-unavailable")
}
