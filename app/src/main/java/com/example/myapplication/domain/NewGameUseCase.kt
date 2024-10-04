package com.example.myapplication.domain

import com.example.myapplication.data.CardRepository
import javax.inject.Inject

class NewGameUseCase @Inject constructor(
    private val repository: CardRepository
) {

    operator fun invoke(size: Int) {
        repository.newGame(size)
    }
}