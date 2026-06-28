package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VNDialogueBox(speaker: String, line: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(12.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(speaker)
            Text(line)
        }
    }
}
