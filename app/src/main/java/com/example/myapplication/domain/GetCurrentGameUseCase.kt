package com.example.myapplication.domain

import com.example.myapplication.data.GameRepository
import com.example.myapplication.domain.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentGameUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {

    operator fun invoke(): Flow<Game?> = gameRepository.getCurrentGame()
}
