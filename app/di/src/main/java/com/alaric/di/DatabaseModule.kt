package com.alaric.di

import android.content.Context
import androidx.room.Room
import com.alaric.data.local.GameDao
import com.alaric.data.local.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGameDatabase(
        @ApplicationContext context: Context
    ): GameDatabase {
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            "game_discovery_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameDao(database: GameDatabase): GameDao {
        return database.dao
    }
}