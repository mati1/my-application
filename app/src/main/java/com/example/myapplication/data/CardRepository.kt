package com.example.myapplication.data

import com.example.myapplication.domain.model.Game
import com.example.myapplication.domain.model.GameCard
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    val cards: Flow<List<GameCard>>

    fun newGame(size: Int)
    fun newGame(game: Game)
    fun reveal(cardId: Int)
    fun hide(cardId: Int)
    fun match(cardId1: Int, cardId2: Int)
    fun clear()
}
