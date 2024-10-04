package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun newGame(size: Int)
    fun cards(): Flow<List<Card>>
    fun reveal(cardId: Int)
    fun hide(cardId: Int)
    fun match(cardId1: Int, cardId2: Int)
    fun clear()
}
