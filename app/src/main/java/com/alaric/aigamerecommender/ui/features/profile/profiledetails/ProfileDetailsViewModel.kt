package com.alaric.aigamerecommender.ui.features.profile.profiledetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaric.domain.usecase.GetGameUseCase
import com.alaric.domain.usecase.ManageLibraryUseCase
import com.alaric.domain.usecase.ManageQueueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGameUseCase: GetGameUseCase,
    private val manageLibraryUseCase: ManageLibraryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileDetailsState())
    val state = _state.asStateFlow()

    private val _effect = Channel<ProfileDetailsEffect>()
    val effect = _effect.receiveAsFlow()

    private val gameId: Int = checkNotNull(savedStateHandle["gameId"])

    init {
        observeGameDetails()
    }

    private fun observeGameDetails() {
        viewModelScope.launch {
            getGameUseCase(gameId).collect { game ->
                if (game != null) {
                    _state.update { it.copy(isLoading = false, game = game, error = null) }
                } else {
                    _state.update { it.copy(isLoading = false, error = "Game not found.") }
                }
            }
        }
    }

    fun processIntent(intent: ProfileDetailsIntent) {
        when (intent) {
            is ProfileDetailsIntent.OnDeleteFromLibrary -> {
                viewModelScope.launch {
                    manageLibraryUseCase.invoke(gameId, false)
                    _effect.send(ProfileDetailsEffect.ShowToast("Removed From Library!"))
                }
            }
            is ProfileDetailsIntent.OnEditNote -> {}
        }
    }
}