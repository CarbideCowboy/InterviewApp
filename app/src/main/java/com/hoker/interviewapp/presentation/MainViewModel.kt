package com.hoker.interviewapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoker.interviewapp.domain.models.Character
import com.hoker.interviewapp.domain.use_case.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {

    val characterList: MutableState<List<Character>?> = mutableStateOf(null)
    val selectedCharacter: MutableState<Character?> = mutableStateOf(null)
    val showLocationDialog = mutableStateOf(false)

    init {
        viewModelScope.launch {
            characterList.value = getCharactersUseCase.getCharacters()
        }
    }

    fun getLocationText(character: Character): String {
        return "${character.name}'s is currently located at ${character.location.name}"
    }
}