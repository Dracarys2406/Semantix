package com.alaric.aigamerecommender.ui.features.profile

import com.alaric.domain.model.Game


data class ProfileState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val profilePhoto: String = "",
    val games : List<Game> = emptyList()
)
