package com.sillytavern.kotlin.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TTSButton(text: String, onSpeak: (String) -> Unit) {
    Button(onClick = { onSpeak(text) }) { Text("Speak") }
}
