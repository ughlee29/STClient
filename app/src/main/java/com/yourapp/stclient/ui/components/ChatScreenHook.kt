package com.sillytavern.kotlin.ui

class ChatScreenHook {
    private val listeners = mutableListOf<(String) -> Unit>()
    fun onEvent(listener: (String) -> Unit) { listeners += listener }
    fun emit(event: String) { listeners.forEach { it(event) } }
}
