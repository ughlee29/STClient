package com.sillytavern.kotlin.extensions

enum class ExtensionHookPoint {
    BEFORE_PROMPT_BUILD,
    AFTER_PROMPT_BUILD,
    BEFORE_LLM_REQUEST,
    AFTER_LLM_RESPONSE,
    MESSAGE_DISPLAY,
    CHARACTER_IMPORT
}

fun interface ExtensionHook<T> {
    suspend fun run(input: T): T
}
