package com.sillytavern.kotlin.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BackupViewModel : ViewModel() {
    private val _status = MutableStateFlow("Idle")
    val status: StateFlow<String> = _status
    fun markExported() { _status.value = "Exported" }
    fun markImported() { _status.value = "Imported" }
}
