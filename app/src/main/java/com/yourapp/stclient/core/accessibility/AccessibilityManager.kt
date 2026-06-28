package com.sillytavern.kotlin.engine

data class AccessibilityProfile(val largeText: Boolean = false, val highContrast: Boolean = false, val reduceMotion: Boolean = false)

class AccessibilityManager {
    var profile: AccessibilityProfile = AccessibilityProfile()
        private set
    fun update(profile: AccessibilityProfile) { this.profile = profile }
}
