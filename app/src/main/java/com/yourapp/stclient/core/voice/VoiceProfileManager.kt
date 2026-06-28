package com.sillytavern.kotlin.engine

data class VoiceProfile(val id: String, val name: String, val providerVoiceId: String, val speed: Float = 1f)

class VoiceProfileManager {
    private val profiles = linkedMapOf<String, VoiceProfile>()
    fun upsert(profile: VoiceProfile) { profiles[profile.id] = profile }
    fun get(id: String): VoiceProfile? = profiles[id]
    fun all(): List<VoiceProfile> = profiles.values.toList()
}
