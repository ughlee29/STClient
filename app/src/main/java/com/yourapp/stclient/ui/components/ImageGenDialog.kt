package com.sillytavern.kotlin.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ImageGenDialog(prompt: String, onDismiss: () -> Unit, onGenerate: (String) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Generate Image") },
        text = { Text(prompt) },
        confirmButton = { Button(onClick = { onGenerate(prompt) }) { Text("Generate") } },
        dismissButton = { Button(onClick = onDismiss) { Text("Cancel") } }
    )
}
