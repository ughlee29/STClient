package com.sillytavern.kotlin.engine

interface TTSProvider {
    suspend fun speak(text: String, voiceProfileId: String? = null)
    fun stop()
}
