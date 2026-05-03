package com.alaric.aigamerecommender.ui.features.search

import com.alaric.domain.model.Game

data class searchState(
    val isLoading : Boolean,
    val error : String,
    val prompt : String,
    val games : List<Game>
)