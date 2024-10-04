package com.example.myapplication.data

data class Card(
    val id: Int,
    val label: String,
    val isRevealed: Boolean = false,
    val isMatched: Boolean = false,
)
