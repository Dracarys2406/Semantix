package com.alaric.aigamerecommender.ui.features.profile.profiledetails

import com.alaric.domain.model.Game

data class ProfileDetailsState(
    val isLoading: Boolean = true,
    val game: Game? = null,
    val error: String? = null
)
