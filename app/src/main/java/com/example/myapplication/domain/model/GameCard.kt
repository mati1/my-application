package com.example.myapplication.domain.model

/**
 * Represents a card in the game.
 */
data class GameCard(
    val id: Int,
    val card: Card,
    val isRevealed: Boolean = false,
    val isMatched: Boolean = false,
)
