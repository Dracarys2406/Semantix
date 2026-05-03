package com.alaric.domain.usecase

import com.alaric.domain.model.Game
import com.alaric.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(id: Int): Flow<Game?> {
        return repository.observeGame(id)
    }
}