package com.alaric.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE isStoredInQueue = 1")
    fun getQueue(): Flow<List<GameEntity>>

    @Upsert
    suspend fun upsertGames(games: List<GameEntity>)

    @Query("DELETE FROM games WHERE isStoredInQueue = 0")
    suspend fun clearSearchResults()

    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGame(gameId: Int)
}