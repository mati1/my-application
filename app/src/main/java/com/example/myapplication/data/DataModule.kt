package com.example.myapplication.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context,
    ): AppDatabase = Room.inMemoryDatabaseBuilder(
        context = applicationContext,
        klass = AppDatabase::class.java,
    ).build()

    @Provides
    fun provideGameDao(
        appDatabase: AppDatabase
    ): GameDao = appDatabase.gameDao()

    @Provides
    fun provideDeckDao(
        appDatabase: AppDatabase
    ): DeckDao = appDatabase.deckDao()
}
