package com.example.myapplication.domain.model

import kotlin.uuid.Uuid

data class Deck(
    val id: Uuid,
    val name: String,
    val cards: List<Card>
)
