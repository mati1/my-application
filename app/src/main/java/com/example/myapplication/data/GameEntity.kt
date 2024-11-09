package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
)

@Dao
interface GameDao {

    @Query("SELECT * FROM games ORDER BY id DESC LIMIT 1")
    fun getNewest(): Flow<GameEntity?>

    @Insert
    fun insert(game: GameEntity)
}
