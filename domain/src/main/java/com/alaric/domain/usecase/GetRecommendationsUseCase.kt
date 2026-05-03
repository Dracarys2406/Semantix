package com.alaric.domain.usecase

import com.alaric.domain.model.Game
import com.alaric.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(prompt: String) {

        repository.fetchRecommendations(prompt)
    }


    // 2. The Query: Exposes the reactive stream from the local databae
    fun observe(): Flow<List<Game>> {

        return repository.observeGames()
    }
}