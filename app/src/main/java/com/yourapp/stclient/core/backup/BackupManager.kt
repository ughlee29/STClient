package com.sillytavern.kotlin.engine

class BackupManager {
    fun exportJson(payloads: Map<String, String>): String = payloads.entries.joinToString(prefix = "{", postfix = "}") { (k, v) -> "\"$k\":\"${v.replace("\"", "\\\"")}\"" }
    fun importJson(raw: String): Boolean = raw.trim().startsWith("{") && raw.trim().endsWith("}")
}
