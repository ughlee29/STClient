package com.sillytavern.kotlin.engine

class ModelDownloader {
    data class Progress(val url: String, val downloadedBytes: Long, val totalBytes: Long?)
    suspend fun download(url: String, onProgress: (Progress) -> Unit = {}): String {
        onProgress(Progress(url, 0, null))
        return url.substringAfterLast('/').ifBlank { "model.bin" }
    }
}
