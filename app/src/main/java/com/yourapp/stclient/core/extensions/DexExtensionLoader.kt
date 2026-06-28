package com.sillytavern.kotlin.extensions

class DexExtensionLoader {
    fun loadDex(path: String, manifest: ExtensionManifest): STExtension =
        object : STExtension {
            override val manifest: ExtensionManifest = manifest.copy(entryPoint = path)
        }
}
