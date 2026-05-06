package com.alaric.aigamerecommender.ui.features.profile

import com.alaric.aigamerecommender.ui.features.queue.QueueIntent

sealed class ProfileIntent{
    data class RemoveFromLibrary(val gameId: Int) : ProfileIntent()
    data class OnGameSelected(val gameId: Int) : ProfileIntent()
    data class OnChangeProfilePhoto(val photoUrl : String) : ProfileIntent()

}

