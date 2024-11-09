package com.example.myapplication.domain

import com.example.myapplication.data.CardRepository
import com.example.myapplication.data.DeckRepository
import com.example.myapplication.data.GameRepository
import com.example.myapplication.domain.model.Game
import com.example.myapplication.domain.model.GameCard
import javax.inject.Inject
import kotlin.uuid.Uuid

class NewGameUseCase @Inject constructor(
    private val repository: CardRepository,
    private val gameRepository: GameRepository,
    private val deckRepository: DeckRepository,
) {

    suspend operator fun invoke() {

        val deck = deckRepository
            .getDecks()
            .last()

        val gameCards = deck.cards
            .flatMap { listOf(it, it) }
            .shuffled()
            .mapIndexed { index, card -> GameCard(index, card) }
            .toMutableList()

        val game = Game(
            id = 0,
            cards = gameCards,
        )

        gameRepository.newGame(game)
    }
}
