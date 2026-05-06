package com.alaric.aigamerecommender.ui.features.profile

import com.alaric.aigamerecommender.ui.features.queue.QueueEffect


sealed class ProfileEffect {

    data class NavigateToGameDetails(val gameId: Int) : ProfileEffect()

    // feedback to user
    data class ShowToast(val message: String) : ProfileEffect()
    data class ShowSnackbar(val message: String) : ProfileEffect()

    // ui actions
    object HideKeyboard : ProfileEffect()

}