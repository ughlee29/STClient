package com.sillytavern.kotlin.engine

class InferenceLifecycleManager {
    var isRunning: Boolean = false
        private set
    fun start() { isRunning = true }
    fun stop() { isRunning = false }
}
