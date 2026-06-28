package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun BranchIndicator(current: Int, total: Int, onPrevious: (() -> Unit)? = null, onNext: (() -> Unit)? = null) {
    Row {
        TextButton(onClick = { onPrevious?.invoke() }, enabled = onPrevious != null) { Text("‹") }
        Text("$current/$total")
        TextButton(onClick = { onNext?.invoke() }, enabled = onNext != null) { Text("›") }
    }
}
