package com.example.myapplication.data

import com.example.myapplication.domain.model.Card
import com.example.myapplication.domain.model.CardContent
import com.example.myapplication.domain.model.Deck
import javax.inject.Inject
import kotlin.random.Random
import kotlin.uuid.Uuid

class DeckRepository @Inject constructor(
    private val deckDao: DeckDao,
) {

    fun getDecks(): List<Deck> {
        return listOf(
            Deck(Uuid.random(), "Numbers", getNumberedDeck()),
            Deck(Uuid.random(), "Emojis", getEmojisDeck()),
        )
    }

    private fun getNumberedDeck(): List<Card> = (1..10)
        .map { Card(it.toLong(), CardContent.Text(it.toString())) }

    private fun getEmojisDeck(): List<Card> = setOf(
        "❤️", "🚀", "🤣", "😇",
    ).map { Card(Random.nextLong(), CardContent.Text(it)) }
}
