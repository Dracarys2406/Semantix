package com.alaric.data.repository

import android.util.Log
import com.alaric.data.local.GameDao
import com.alaric.data.mapper.toDomain
import com.alaric.data.mapper.toEntity
import com.alaric.data.remote.GameApiService
import com.alaric.domain.model.Game
import com.alaric.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GameApiService,
    private val dao: GameDao
) : GameRepository {

    // The UI observes this Flow and updates when the DB changes.
    override fun observeGames(): Flow<List<Game>> {
        return dao.getAllGames().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun observeGame(id: Int): Flow<Game?> {
        return dao.getGameById(id).map { it?.toDomain() }
    }

    override suspend fun fetchRecommendations(prompt: String) {
        try {
            // 1. Fetch from Ktor
            val remoteGames = api.getRecommendations(prompt)

            dao.upsertGames(remoteGames.map { it.toEntity() })

        } catch (e: Exception) {
            Log.d("GAMEREPOSITORY_IMPL", e.message?:"Unknown Error at GameRepositoryImpl")
        }
    }
}