package com.alaric.di

import android.content.Context
import androidx.room.Room
import com.alaric.data.local.GameDao
import com.alaric.data.local.GameDatabase
import com.alaric.data.local.playhistory.PlayHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideGameDao(database: GameDatabase): GameDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun providePlayHistoryDao(database: GameDatabase): PlayHistoryDao {
        return database.playHistoryDao
    }
}
