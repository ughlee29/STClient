package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sillytavern.kotlin.config.AppSettings

@Composable
fun SettingsScreen(settings: AppSettings, onCloudChanged: (Boolean) -> Unit) {
    Column {
        Text("Settings")
        Text("Provider: ${settings.selectedProvider}")
        Switch(checked = settings.useCloudInference, onCheckedChange = onCloudChanged)
    }
}
