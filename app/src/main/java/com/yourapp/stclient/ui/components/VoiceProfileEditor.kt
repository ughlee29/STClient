package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*

@Composable
fun VoiceProfileEditor(initialName: String, onSave: (String) -> Unit) {
    var name by remember { mutableStateOf(initialName) }
    Column {
        Text("Voice Profile")
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
    }
}
