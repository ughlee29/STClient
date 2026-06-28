package com.sillytavern.kotlin.ui

import androidx.lifecycle.ViewModel
import com.sillytavern.kotlin.config.STCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<STCharacter>>(emptyList())
    val characters: StateFlow<List<STCharacter>> = _characters

    fun add(character: STCharacter) { _characters.value = _characters.value + character }
    fun remove(id: String) { _characters.value = _characters.value.filterNot { it.id == id } }
}
