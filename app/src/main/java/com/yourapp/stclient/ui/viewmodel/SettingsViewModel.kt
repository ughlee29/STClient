package com.sillytavern.kotlin.ui

import androidx.lifecycle.ViewModel
import com.sillytavern.kotlin.config.AppSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    private val _settings = MutableStateFlow(AppSettings())
    val settings: StateFlow<AppSettings> = _settings
    fun setCloudInference(enabled: Boolean) { _settings.value = _settings.value.copy(useCloudInference = enabled) }
}
