package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCardRepository @Inject constructor(

) : CardRepository {

    private val cards: MutableStateFlow<List<Card>> = MutableStateFlow(emptyList())
    private val revealedCards = MutableStateFlow(emptySet<Int>())
    private val matchedCards = MutableStateFlow(emptySet<Int>())

    override fun newGame(size: Int) {
        cards.value = (1..size)
            .flatMap { listOf(it, it) }
            .shuffled()
            .mapIndexed { index, number -> Card(index, number.toString()) }
            .toMutableList()
        revealedCards.value = emptySet()
        matchedCards.value = emptySet()
    }

    override fun cards(): Flow<List<Card>> = combine(
        cards,
        revealedCards,
        matchedCards,
    ) { cards, revealed, matched ->
        cards.map { card ->
            card.copy(
                isRevealed = revealed.contains(card.id),
                isMatched = matched.contains(card.id)
            )
        }
    }

    override fun reveal(cardId: Int) {
        revealedCards.update { it + cardId }
    }

    override fun hide(cardId: Int) {
        revealedCards.update { it - cardId }
    }

    override fun clear() {
        revealedCards.value = emptySet()
    }

    override fun match(cardId1: Int, cardId2: Int) {
        matchedCards.update { it + cardId1 + cardId2 }
    }
}
