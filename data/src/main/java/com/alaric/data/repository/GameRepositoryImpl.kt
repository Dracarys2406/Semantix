package com.alaric.data.repository

import android.util.Log
import com.alaric.data.local.GameDao
import com.alaric.data.local.playhistory.PlayHistoryDao
import com.alaric.data.mapper.toDomain
import com.alaric.data.mapper.toEntity
import com.alaric.data.remote.GameApiService
import com.alaric.data.remote.RecommendationRequest
import com.alaric.domain.model.Game
import com.alaric.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val apiService: GameApiService,
    private val playHistoryDao: PlayHistoryDao,
    private val gameDao: GameDao
) : GameRepository {

    // The UI observes this Flow and updates when the DB changes.
    override fun observeSearchGames(): Flow<List<Game>> {
        return gameDao.getSearchGames().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun observeQueueGames(): Flow<List<Game>> {
        return gameDao.getQueue().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun observeLibraryGames(): Flow<List<Game>> {
        return gameDao.getLibrary().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun observeGame(id: Int): Flow<Game?> {
        return gameDao.getGameById(id).map { it?.toDomain() }
    }

    override suspend fun fetchRecommendations(query: String) {
        try {

            // Gather context from both History and Queue
            // then merge them for the AI "Player History" field
            val historyTitles = playHistoryDao.getRecentPlayHistoryTitles() // e.g. ["Elden Ring", "Halo"]
            val queuedTitles = gameDao.getQueuedGameTitles()

            val fullContext = (historyTitles + queuedTitles).distinct()

            // Execute API Call
            val request = RecommendationRequest(
                query = query,
                playHistory = fullContext
            )

            // network call
            val remoteGames = apiService.getRecommendations(request)

            // Cache in Room
            // 3. Filter out games already in the user's Queue ... a redundant measure since our server should already do this
            val queuedIds = gameDao.getQueuedGameIds().toSet()
            val newUniqueGames = remoteGames
                .map { it.toEntity() }
                .filterNot { it.id in queuedIds }

            // Atomic database sync
            // Clear old results and insert the fresh, unique ones
            gameDao.clearAndInsert(newUniqueGames)


        } catch (e: Exception) {
            Log.d("GAMEREPOSITORY_IMPL", e.message?:"Unknown Error at GameRepositoryImpl")
        }
    }

    override suspend fun updateQueueStatus(id: Int, isQueued: Boolean) {
        gameDao.updateQueueAndCleanup(id, isQueued)
    }


    // Play History //

    override suspend fun updatePlayedStatus(gameId: Int, isPlayed: Boolean) {
        gameDao.updatePlayedAndCleanup(gameId, isPlayed)
    }

    override suspend fun moveToLibrary(id: Int) {
        gameDao.moveToLibrary(id)
    }

}