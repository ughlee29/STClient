package com.sillytavern.kotlin.engine

class MentionParser {
    fun mentions(text: String): List<String> = Regex("@([A-Za-z0-9_ -]+)").findAll(text).map { it.groupValues[1].trim() }.toList()
}
