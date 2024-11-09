package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_cards")
data class ImageCardEntity(
    @PrimaryKey val id: Long,
    val cardId: Long,
    val url: String,
)