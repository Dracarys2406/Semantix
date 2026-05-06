package com.alaric.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.alaric.domain.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE isPlayed = 0 AND isStoredInQueue = 0")
    fun getSearchGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE id = :gameId LIMIT 1")
    fun getGameById(gameId: Int): Flow<GameEntity?>


    @Upsert
    suspend fun upsertGames(games: List<GameEntity>)

    @Query("DELETE FROM games WHERE isStoredInQueue = 0")
    suspend fun clearSearchResults()

    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGame(gameId: Int)


    @Query("SELECT title FROM games WHERE isStoredInQueue = 1")
    suspend fun getQueuedGameTitles(): List<String>

    // for QueueScreen
    @Query("SELECT * FROM games WHERE isStoredInQueue = 1")
    fun getQueue(): Flow<List<GameEntity>>

    @Query("DELETE FROM games WHERE isStoredInQueue = 0")
    suspend fun deleteNonQueueGames()

    // We'll use this to check for duplicates before inserting
    @Query("SELECT id FROM games WHERE isStoredInQueue = 1")
    suspend fun getQueuedGameIds(): List<Int>

    // clearing the search history
    @Transaction
    suspend fun clearAndInsert(newGames: List<GameEntity>) {
        // Wipe everything that isn't in the user's queue
        clearSearchResults()

        // Insert the fresh recommendations from the AI
        upsertGames(newGames)
    }


    // Queue Management

    @Transaction
    suspend fun updateQueueAndCleanup(gameId: Int, isQueued: Boolean) {
        updateQueueStatus(gameId, isQueued )

        // Only wipe it if it's not played AND not in queue
        if (!isQueued) {
            deleteIfOrphaned(gameId)
        }
    }

    @Query("UPDATE games SET isStoredInQueue = :isQueued WHERE id = :id")
    suspend fun updateQueueStatus(id: Int, isQueued: Boolean)


    @Transaction
    suspend fun moveToLibrary(gameId: Int) {
        updatePlayedStatus(gameId, true)
        updateQueueStatus(gameId, false)
    }


    // Play History

    @Query("SELECT * FROM games WHERE isPlayed = 1")
    fun getLibrary(): Flow<List<GameEntity>>

    @Transaction
    suspend fun updatePlayedAndCleanup(gameId: Int, isPlayed: Boolean) {
        updatePlayedStatus(gameId, isPlayed)

        // Only wipe it if it's not played AND not in queue
        if (!isPlayed) {
            deleteIfOrphaned(gameId)
        }
    }

    @Query("DELETE FROM games WHERE id = :gameId AND isStoredInQueue = 0 AND isPlayed = 0")
    suspend fun deleteIfOrphaned(gameId: Int)

    @Query("UPDATE games SET isPlayed = :isPlayed Where id = :id")
    suspend fun updatePlayedStatus(id: Int, isPlayed: Boolean)





}