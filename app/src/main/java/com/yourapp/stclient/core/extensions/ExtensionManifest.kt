package com.sillytavern.kotlin.extensions

data class ExtensionManifest(
    val id: String,
    val name: String,
    val version: String,
    val author: String = "",
    val description: String = "",
    val entryPoint: String = "",
    val permissions: Set<String> = emptySet()
)
