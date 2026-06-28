package com.sillytavern.kotlin.extensions

class ExtensionRegistry {
    private val extensions = linkedMapOf<String, STExtension>()
    private val hooks = mutableMapOf<ExtensionHookPoint, MutableList<ExtensionHook<Any>>>()

    fun registered(): List<STExtension> = extensions.values.toList()

    fun register(extension: STExtension) {
        extensions[extension.manifest.id] = extension
    }

    fun unregister(id: String) {
        extensions.remove(id)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> addHook(point: ExtensionHookPoint, hook: ExtensionHook<T>) {
        hooks.getOrPut(point) { mutableListOf() }.add(hook as ExtensionHook<Any>)
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T> runHooks(point: ExtensionHookPoint, input: T): T {
        var value: Any = input as Any
        hooks[point].orEmpty().forEach { value = it.run(value) }
        return value as T
    }
}
