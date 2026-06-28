package com.sillytavern.kotlin.engine

import com.sillytavern.kotlin.config.STCharacter

class GroupChatOrchestrator {
    fun nextSpeaker(group: GroupChat, recentSpeakerId: String?): STCharacter? =
        group.members.firstOrNull { it.id != recentSpeakerId } ?: group.members.firstOrNull()
}
