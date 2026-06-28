package com.sillytavern.kotlin.engine

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VNViewModel : ViewModel() {
    private val _line = MutableStateFlow("Visual novel mode ready.")
    val line: StateFlow<String> = _line
    fun setLine(value: String) { _line.value = value }
}
