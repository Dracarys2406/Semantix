package com.alaric.aigamerecommender.ui.features.queue.queuedetails

import com.alaric.domain.model.Game

data class QueueDetailsState(
    val isLoading: Boolean = true,
    val game: Game? = null,
    val error: String? = null
)
