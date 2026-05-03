package com.alaric.aigamerecommender.ui.features.search

import com.alaric.domain.model.Game

sealed interface SearchIntent {
    data class OnSearchQueryChanged(val query: String) : SearchIntent

    object OnSearchClicked : SearchIntent
    object OnRetrySearch : SearchIntent // for error states

    data class OnGameSelected(val gameId: Int) : SearchIntent

    data class OnToggleQueueStatus(val game: Game) : SearchIntent
}