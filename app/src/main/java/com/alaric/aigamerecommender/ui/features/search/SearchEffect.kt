package com.alaric.aigamerecommender.ui.features.search

sealed interface SearchEffect {
    // nav events
    data class NavigateToGameDetails(val gameId: Int) : SearchEffect

    // feedback to user
    data class ShowToast(val message: String) : SearchEffect
    data class ShowSnackbar(val message: String) : SearchEffect

    // ui actions
    object HideKeyboard : SearchEffect
}