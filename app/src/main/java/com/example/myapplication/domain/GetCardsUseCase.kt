package com.example.myapplication.domain

import com.example.myapplication.data.CardRepository
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val repository: CardRepository
) {

    operator fun invoke() = repository.cards()
}