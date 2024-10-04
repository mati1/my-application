package com.example.myapplication.domain

import com.example.myapplication.data.Card
import com.example.myapplication.data.CardRepository
import javax.inject.Inject

class PlayCardUseCase @Inject constructor(
    private val repository: CardRepository,
) {

    operator fun invoke(cards: List<Card>, cardId: Int) {
        val revealedCards = cards.filter(Card::isRevealed)

        if (revealedCards.isEmpty()) {
            // Reveal the first card
            repository.reveal(cardId)
        } else if (revealedCards.size == 1) {
            if (revealedCards.first().label == cards.first { it.id == cardId }.label) {
                // Match!
                repository.clear()
                repository.match(revealedCards.first().id, cardId)
            } else {
                // Reveal the second card
                repository.reveal(cardId)
            }
        } else {
            // Miss!
            repository.hide(revealedCards.first().id)
            repository.hide(revealedCards.last().id)
            repository.reveal(cardId)
        }
    }
}
