package com.alaric.aigamerecommender.ui.features.queue

import com.alaric.aigamerecommender.ui.features.search.SearchEffect


sealed interface QueueEffect {
    data class NavigateToGameDetails(val gameId : Int) : QueueEffect

    // feedback to user
    data class ShowToast(val message: String) : QueueEffect
    data class ShowSnackbar(val message: String) : QueueEffect

    // ui actions
    object HideKeyboard : QueueEffect
}