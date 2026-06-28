package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BackupScreen(onExport: () -> Unit, onImport: () -> Unit) {
    Column {
        Text("Backup")
        Button(onClick = onExport) { Text("Export") }
        Button(onClick = onImport) { Text("Import") }
    }
}
