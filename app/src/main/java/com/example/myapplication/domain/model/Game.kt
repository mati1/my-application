package com.example.myapplication.domain.model

import kotlin.time.Duration

data class Game(
    val id: Long,
    val cards: List<GameCard>,
    val status: GameStatus = GameStatus.NEW,
    val duration: Duration = Duration.ZERO,
)
