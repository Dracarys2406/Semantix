package com.alaric.domain.repository

import com.alaric.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun observeGames(): Flow<List<Game>>
    suspend fun fetchRecommendations(prompt: String)
}