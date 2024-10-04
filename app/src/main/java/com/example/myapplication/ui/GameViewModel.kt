package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.GetCardsUseCase
import com.example.myapplication.domain.NewGameUseCase
import com.example.myapplication.domain.PlayCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val newGameUseCase: NewGameUseCase,
    private val getCardsUseCase: GetCardsUseCase,
    private val playCardUseCase: PlayCardUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState(emptyList()))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCardsUseCase().collect { newCards ->
                _uiState.update {
                    it.copy(cards = newCards.map { newCard ->
                        CardUiState(
                            id = newCard.id,
                            label = newCard.label,
                            isFlipped = newCard.isRevealed || newCard.isMatched
                        )
                    })
                }
            }
        }
    }

    fun newGame(size: Int) {
        newGameUseCase(size)
    }

    fun playCard(card: CardUiState) {
        viewModelScope.launch {
            playCardUseCase(getCardsUseCase().first(), card.id)
        }
    }
}
