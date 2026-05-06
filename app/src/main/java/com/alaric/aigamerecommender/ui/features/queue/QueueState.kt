package com.alaric.aigamerecommender.ui.features.queue

import com.alaric.domain.model.Game

data class QueueState(
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val games : List<Game> = emptyList()
)
