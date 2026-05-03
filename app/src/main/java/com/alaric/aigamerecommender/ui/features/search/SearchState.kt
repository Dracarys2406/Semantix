package com.alaric.aigamerecommender.ui.features.search

import com.alaric.domain.model.Game

data class SearchState(
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val recommendedGames: List<Game> = emptyList()
)