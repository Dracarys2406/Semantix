package com.alaric.domain.usecase

import com.alaric.domain.model.Game
import com.alaric.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageQueueUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(gameId: Int, isQueued: Boolean) {
        repository.updateQueueStatus(gameId, isQueued)
    }

    suspend fun moveToLibrary(gameId: Int) {
        repository.moveToLibrary(gameId)
    }

    fun observeQueueGames(): Flow<List<Game>> {
        return repository.observeQueueGames()
    }


}