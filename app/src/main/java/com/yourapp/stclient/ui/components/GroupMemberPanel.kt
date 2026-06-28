package com.sillytavern.kotlin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sillytavern.kotlin.config.STCharacter

@Composable
fun GroupMemberPanel(members: List<STCharacter>) {
    Column { members.forEach { Text(it.name) } }
}
