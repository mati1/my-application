package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        GameEntity::class,
        DeckEntity::class,
        CardEntity::class,
        TextCardEntity::class,
        ImageCardEntity::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun deckDao(): DeckDao
}
