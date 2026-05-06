package com.alaric.aigamerecommender.ui.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaric.aigamerecommender.ui.features.queue.QueueEffect
import com.alaric.aigamerecommender.ui.features.queue.QueueIntent
import com.alaric.aigamerecommender.ui.features.queue.QueueState
import com.alaric.aigamerecommender.ui.navigation.SemantixScreens
import com.alaric.domain.usecase.ManageLibraryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    manageLibraryUseCase: ManageLibraryUseCase
    ) : ViewModel() {
    init {
        // we sart observing the database when the ViewModel is created
        viewModelScope.launch {
            manageLibraryUseCase.observeLibraryGames().collect { games ->
                // Every time Room changes, this updates the UI state instantly
                _state.update { it.copy(games = games) }
            }
        }
    }

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    // channel for one time effects like toasts and snackbars
    private val _effect = Channel<ProfileEffect>()
    val effect = _effect.receiveAsFlow()

    // UI actions
    fun processIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.OnGameSelected -> {
                triggerEffect(ProfileEffect.NavigateToGameDetails(intent.gameId))
            }
            is ProfileIntent.RemoveFromLibrary -> {

            }
            is ProfileIntent.OnChangeProfilePhoto -> TODO()
        }
    }

    private fun triggerEffect(effect: ProfileEffect) {
        viewModelScope.launch {
            _effect.send(effect)


        }
    }
}