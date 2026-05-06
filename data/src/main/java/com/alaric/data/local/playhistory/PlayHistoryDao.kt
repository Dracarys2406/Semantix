package com.alaric.data.local.playhistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGameToHistory(entry: PlayHistoryEntity)

    // We only need the titles for the request
    @Query("SELECT title FROM play_history ORDER BY timestamp DESC LIMIT 10")
    suspend fun getRecentPlayHistoryTitles(): List<String>

    @Query("SELECT * FROM play_history ORDER BY timestamp DESC")
    fun observeFullHistory(): Flow<List<PlayHistoryEntity>>
}