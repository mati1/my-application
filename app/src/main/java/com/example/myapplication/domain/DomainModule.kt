package com.example.myapplication.domain

import com.example.myapplication.data.CardRepository
import com.example.myapplication.data.InMemoryCardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun bindCardRepository(inMemoryCardRepository: InMemoryCardRepository): CardRepository
}
