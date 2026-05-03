package com.alaric.aigamerecommender.ui.features.search.gamedetails

import com.alaric.domain.model.Game

data class DetailsState(
    val isLoading: Boolean = true,
    val game: Game? = null,
    val error: String? = null
)