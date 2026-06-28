package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*

@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    val steps = listOf("Choose provider", "Import character", "Start chatting")
    Column {
        Text(steps.getOrElse(step) { "Ready" })
        Button(onClick = { if (step >= steps.lastIndex) onFinished() else step++ }) { Text("Next") }
    }
}
