package com.example.myapplication.domain.model

sealed class CardContent {
    data class Text(val text: String) : CardContent()
    data class Image(val url: String) : CardContent()
}
