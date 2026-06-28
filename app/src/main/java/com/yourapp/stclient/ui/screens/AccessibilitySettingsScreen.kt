package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AccessibilitySettingsScreen(largeText: Boolean, onLargeTextChanged: (Boolean) -> Unit) {
    Column {
        Text("Accessibility")
        Switch(checked = largeText, onCheckedChange = onLargeTextChanged)
    }
}
