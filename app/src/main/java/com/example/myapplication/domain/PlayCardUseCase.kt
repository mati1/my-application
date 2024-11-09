package com.example.myapplication.domain

import com.example.myapplication.domain.model.GameCard
import com.example.myapplication.data.CardRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PlayCardUseCase @Inject constructor(
    private val repository: CardRepository,
) {

    suspend operator fun invoke(cardId: Int) {
        val cards = repository.cards.first()
        val revealedCards = cards.filter(GameCard::isRevealed)

        if (revealedCards.isEmpty()) {
            // Reveal the first card
            repository.reveal(cardId)
        } else if (revealedCards.size == 1) {
            if (revealedCards.any { it.id == cardId }) {
                // Hide the card if it's already revealed
                repository.hide(cardId)
            } else if (revealedCards.first().card.id == cards.first { it.id == cardId }.card.id) {
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
