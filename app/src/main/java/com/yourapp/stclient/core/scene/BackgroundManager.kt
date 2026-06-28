package com.sillytavern.kotlin.engine

class BackgroundManager {
    private val backgrounds = mutableListOf<String>()
    fun add(uri: String) { backgrounds += uri }
    fun all(): List<String> = backgrounds.toList()
    fun pick(index: Int): String? = backgrounds.getOrNull(index)
}
