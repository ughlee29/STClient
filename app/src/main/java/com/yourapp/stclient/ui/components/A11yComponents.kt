package com.sillytavern.kotlin.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun A11yLabel(text: String, description: String = text) {
    Text(text = text, modifier = Modifier.semantics { contentDescription = description })
}
