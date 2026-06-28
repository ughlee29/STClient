package com.sillytavern.kotlin.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BackgroundPickerDialog(options: List<String>, onPick: (String) -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Pick Background") },
        text = { Text(options.joinToString("\n")) },
        confirmButton = { Button(onClick = { options.firstOrNull()?.let(onPick) ?: onDismiss() }) { Text("Use First") } },
        dismissButton = { Button(onClick = onDismiss) { Text("Cancel") } }
    )
}
