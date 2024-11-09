package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.GetCardsUseCase
import com.example.myapplication.domain.GetCurrentGameUseCase
import com.example.myapplication.domain.NewGameUseCase
import com.example.myapplication.domain.PlayCardUseCase
import com.example.myapplication.domain.model.CardContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getCurrentGameUseCase: GetCurrentGameUseCase,
    private val newGameUseCase: NewGameUseCase,
    private val getCardsUseCase: GetCardsUseCase,
    private val playCardUseCase: PlayCardUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState(emptyList()))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCurrentGameUseCase().collect { game ->
                if (game != null) {
                    _uiState.update {
                        it.copy(cards = game.cards.map { newCard ->
                            CardUiState(
                                id = newCard.id,
                                label = (newCard.card.content as? CardContent.Text)?.text
                                    ?: "Image?",
                                isFlipped = newCard.isRevealed || newCard.isMatched
                            )
                        })
                    }
                }
            }
        }
    }

    fun newGame(size: Int) {
        viewModelScope.launch {
            newGameUseCase()
        }
    }

    fun playCard(card: CardUiState) {
        viewModelScope.launch {
            playCardUseCase(card.id)
        }
    }
}
