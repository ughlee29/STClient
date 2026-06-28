package com.sillytavern.kotlin.extensions

interface STExtension {
    val manifest: ExtensionManifest
    suspend fun onLoad(context: ExtensionContext) = Unit
    suspend fun onUnload() = Unit
}

data class ExtensionContext(
    val appVersion: String,
    val hooks: ExtensionRegistry
)
