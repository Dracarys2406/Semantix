package com.alaric.domain.usecase

import com.alaric.domain.repository.GameRepository
import javax.inject.Inject

class ManageHistoryUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(gameId : Int, isPlayed : Boolean) {
        repository.updatePlayedStatus(gameId, isPlayed)
    }
}