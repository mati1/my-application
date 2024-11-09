package com.example.myapplication.data

import com.example.myapplication.domain.model.Card
import com.example.myapplication.domain.model.CardContent
import com.example.myapplication.domain.model.Game
import com.example.myapplication.domain.model.GameCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val gameDao: GameDao
) {

    fun getCurrentGame(): Flow<Game?> {
        return gameDao.getNewest().map { it?.toModel() }
    }

    suspend fun newGame(game: Game) {
        withContext(Dispatchers.IO) {
            gameDao.insert(game.toEntity())
        }
    }
}

private fun GameEntity.toModel(): Game {
    return Game(
        id = id,
        cards = listOf(GameCard(1, Card(1L, CardContent.Text("1"))))
    )
}

private fun Game.toEntity(): GameEntity {
    return GameEntity(
        id = id,
    )
}
