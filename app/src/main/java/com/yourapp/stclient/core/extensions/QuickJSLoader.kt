package com.sillytavern.kotlin.extensions

class QuickJSLoader {
    fun isAvailable(): Boolean = false
    fun loadScriptExtension(source: String, manifest: ExtensionManifest): STExtension =
        object : STExtension {
            override val manifest: ExtensionManifest = manifest.copy(description = manifest.description.ifBlank { "QuickJS extension stub" })
            override suspend fun onLoad(context: ExtensionContext) { require(source.isNotBlank()) }
        }
}
