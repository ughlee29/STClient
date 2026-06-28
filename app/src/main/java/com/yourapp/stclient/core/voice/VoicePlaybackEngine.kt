package com.sillytavern.kotlin.engine

class VoicePlaybackEngine(private val provider: TTSProvider? = null) {
    suspend fun play(text: String, profile: VoiceProfile? = null) { provider?.speak(text, profile?.id) }
    fun stop() { provider?.stop() }
}
