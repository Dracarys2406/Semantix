package com.alaric.domain.usecase

import com.alaric.domain.model.Game
import com.alaric.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageLibraryUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(gameId : Int, isPlayed : Boolean) {
        repository.updatePlayedStatus(gameId, isPlayed)
    }

    fun observeLibraryGames(): Flow<List<Game>> {
        return repository.observeLibraryGames()
    }
}