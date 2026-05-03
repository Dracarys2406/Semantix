package com.alaric.domain.repository

import com.alaric.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun observeGame(id: Int): Flow<Game?> // to get details for a specific game

    fun observeGames(): Flow<List<Game>>
    suspend fun fetchRecommendations(prompt: String)
}