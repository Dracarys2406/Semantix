package com.alaric.aigamerecommender.ui.features.search

import com.alaric.domain.model.Game

sealed interface SearchIntent {

    // core search
    data class OnSearchQueryChanged(val query: String) : SearchIntent

    object OnSearchClicked : SearchIntent
    object OnRetrySearch : SearchIntent // for error states.. can be common with IGDB rate limits
                                        // will modify server later to avoid rate limiting

    // game details
    data class OnGameSelected(val gameId: Int) : SearchIntent

    data class OnToggleQueueStatus(val game: Game) : SearchIntent

    // for filtering
    data class OnGenreToggled(val genre: String) : SearchIntent
    object OnClearFilters : SearchIntent
}