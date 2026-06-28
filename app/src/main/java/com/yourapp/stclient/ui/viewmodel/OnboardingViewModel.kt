package com.sillytavern.kotlin.engine

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingViewModel : ViewModel() {
    private val _step = MutableStateFlow(0)
    val step: StateFlow<Int> = _step
    fun next() { _step.value += 1 }
    fun reset() { _step.value = 0 }
}
