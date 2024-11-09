package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_cards")
data class TextCardEntity(
    @PrimaryKey val id: Long,
    val cardId: Long,
    val text: String,
)