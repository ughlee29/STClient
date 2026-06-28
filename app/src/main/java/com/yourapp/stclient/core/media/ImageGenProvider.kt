package com.sillytavern.kotlin.engine

interface ImageGenProvider {
    suspend fun generateImage(prompt: String): ImageResult
}

data class ImageResult(val uri: String, val prompt: String)
