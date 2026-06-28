package com.sillytavern.kotlin.engine

enum class RegexScope { INPUT, PROMPT, STREAMING, POST_RESPONSE, MESSAGE_DISPLAY }

class RegexEngine(private val store: RegexRuleStore = RegexRuleStore()) {
    fun process(text: String, scope: RegexScope): String =
        store.rules(scope).fold(text) { acc, rule -> if (rule.enabled) acc.replace(rule.pattern.toRegex(), rule.replacement) else acc }
}
