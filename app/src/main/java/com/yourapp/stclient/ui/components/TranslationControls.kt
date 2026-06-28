package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TranslationControls(onTranslate: () -> Unit, onDetect: () -> Unit) {
    Row {
        Button(onClick = onDetect) { Text("Detect") }
        Button(onClick = onTranslate) { Text("Translate") }
    }
}
