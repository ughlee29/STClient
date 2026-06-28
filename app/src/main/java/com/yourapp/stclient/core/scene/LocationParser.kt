package com.sillytavern.kotlin.engine

data class ParsedLocation(val name: String, val confidence: Float)

class LocationParser {
    fun parse(text: String): List<ParsedLocation> = Regex("(?:in|at|near)\\s+([A-Z][A-Za-z0-9_ -]+)")
        .findAll(text)
        .map { ParsedLocation(it.groupValues[1].trim(), 0.7f) }
        .toList()
}
