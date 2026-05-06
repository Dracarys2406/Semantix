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

    // expose the reactive stream from the local databae
    fun observeSearchGames(): Flow<List<Game>> {
        return repository.observeSearchGames()
    }
}