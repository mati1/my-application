package com.example.myapplication.data

import com.example.myapplication.domain.model.Card
import com.example.myapplication.domain.model.CardContent
import com.example.myapplication.domain.model.Game
import com.example.myapplication.domain.model.GameCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCardRepository @Inject constructor(

) : CardRepository {

    @OptIn(DelicateCoroutinesApi::class)
    private val externalScope: CoroutineScope = GlobalScope

    private val _cards: MutableStateFlow<List<GameCard>> = MutableStateFlow(emptyList())
    private val revealedCards = MutableStateFlow(emptySet<Int>())
    private val matchedCards = MutableStateFlow(emptySet<Int>())

    override fun newGame(size: Int) {
        _cards.value = (1..size)
            .flatMap { listOf(it, it) }
            .shuffled()
            .mapIndexed { index, number ->
                GameCard(
                    index,
                    Card(id = number.toLong(), content = CardContent.Text(number.toString()))
                )
            }
            .toMutableList()
        revealedCards.value = emptySet()
        matchedCards.value = emptySet()
    }

    override fun newGame(game: Game) {
        _cards.value = game.cards
        revealedCards.value = emptySet()
        matchedCards.value = emptySet()
    }

    override val cards: Flow<List<GameCard>> = combine(
        _cards,
        revealedCards,
        matchedCards,
    ) { cards, revealed, matched ->
        println("Combining...")
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
