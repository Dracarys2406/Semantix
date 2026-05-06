package com.alaric.aigamerecommender.ui.features.search.gamedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaric.domain.usecase.GetGameUseCase
import com.alaric.domain.usecase.ManageQueueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGameUseCase: GetGameUseCase,
    private val manageQueueUseCase: ManageQueueUseCase // I'll add this later to handle toggle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    private val _effect = Channel<DetailsEffect>()
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

    fun processIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.OnAddToQueue -> {
                viewModelScope.launch {
                    manageQueueUseCase.invoke(gameId, true)
                    _effect.send(DetailsEffect.ShowToast("Added to Queue!"))
                }
            }
        }
    }
}