package com.sillytavern.kotlin.engine

data class RegexRule(val id: String, val scope: RegexScope, val pattern: String, val replacement: String, val enabled: Boolean = true)

class RegexRuleStore {
    private val rows = mutableListOf<RegexRule>()
    fun rules(scope: RegexScope): List<RegexRule> = rows.filter { it.scope == scope }
    fun add(rule: RegexRule) { rows += rule }
    fun remove(id: String) { rows.removeAll { it.id == id } }
}
