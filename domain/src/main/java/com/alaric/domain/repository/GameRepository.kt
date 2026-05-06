package com.alaric.domain.repository

import com.alaric.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun observeGame(id: Int): Flow<Game?> // to get details for a specific game

    fun observeSearchGames(): Flow<List<Game>>
    fun observeQueueGames(): Flow<List<Game>>
    fun observeLibraryGames(): Flow<List<Game>>
    suspend fun fetchRecommendations(query: String)

    suspend fun updatePlayedStatus(gameId : Int, isPlayed : Boolean)
    // Queue management
    suspend fun updateQueueStatus(id: Int, isQueued: Boolean)

    suspend fun moveToLibrary(id: Int)
}