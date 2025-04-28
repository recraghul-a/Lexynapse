package com.example.lexynapse.ui

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lexynapse.LexiApplication
import com.example.lexynapse.data.Difficultly
import com.example.lexynapse.data.LexiRepository
import com.example.lexynapse.data.Word
import com.example.lexynapse.data.WordCard
import com.example.lexynapse.ui.screens.DetailScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface LexiState {
    data class Success(val words: List<WordCard>): LexiState
    object Empty: LexiState
}

data class DifficultyState(
    var level: Int = - 1
)
class LexiViewModel(val lexiRepository: LexiRepository): ViewModel() {


    private var _difficulty = MutableStateFlow(DifficultyState())
    val difficultly = _difficulty.asStateFlow()

    private var _wordState = MutableStateFlow<WordCard?>(null)
    val wordState: StateFlow<WordCard?> = _wordState.asStateFlow()

   val uiState = lexiRepository.getWords().map {
        words ->
        when {
            words.isNotEmpty() -> LexiState.Success(words)
            else -> { LexiState.Empty}
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = LexiState.Empty
    )

    fun setDifficulty(i: Int) {
        _difficulty.update { currentState ->
            currentState.copy(
                level = i
            )
        }
    }

    fun insertWord(word: Word) {
        viewModelScope.launch {
            lexiRepository.insertWord(word.toWordCard())
        }
    }

    fun deleteWord(word: WordCard) {
        viewModelScope.launch {
            lexiRepository.deleteWord(word)
        }
    }

    fun getCard(id: Int) {
        viewModelScope
            .launch {
                lexiRepository.getCard(id).collect { wordInfo ->
                    _wordState.value = wordInfo
                }
            }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val hazRepository =
                    (this[APPLICATION_KEY] as LexiApplication).container.lexiRepository
                LexiViewModel(hazRepository)
            }
        }
    }
}

fun Word.toWordCard(): WordCard {
    return WordCard(
        name = this.name,
        meaning = this.meaning,
        liked = this.liked,
        difficultly = this.difficultly
    )
}