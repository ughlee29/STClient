package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.sillytavern.kotlin.config.STCardParser

@Composable
fun CharacterImportScreen(onImport: (String) -> Unit) {
    var status by remember { mutableStateOf("Ready to import ST card JSON.") }
    Column {
        Text(status)
        Button(onClick = {
            val character = STCardParser.parseJsonLike("{}")
            status = "Imported: ${character.name}"
            onImport(character.id)
        }) { Text("Import") }
    }
}
