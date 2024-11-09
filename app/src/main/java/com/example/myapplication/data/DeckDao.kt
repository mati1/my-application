package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DeckDao {

    @Query("SELECT * FROM decks")
    fun getAll(): List<DeckEntity>

    @Query("SELECT * FROM decks " +
            "JOIN cards ON decks.id = cards.deck_id")
    fun getDeckWithCards(): Map<DeckEntity, List<CardEntity>>
}
