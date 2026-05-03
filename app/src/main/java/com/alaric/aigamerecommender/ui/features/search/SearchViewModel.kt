package com.alaric.aigamerecommender.ui.features.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaric.aigamerecommender.ui.features.search.SearchEffect.*
import com.alaric.domain.usecase.GetRecommendationsUseCase
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
class SearchViewModel @Inject constructor(
    private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    init {
        // we sart observing the database when the ViewModel is created
        viewModelScope.launch {
            getRecommendationsUseCase.observe().collect { games ->
                // Every time Room changes, this updates the UI state instantly
                _state.update { it.copy(recommendedGames = games) }
            }
        }
    }

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    // channel for one time effects like toasts and snackbars
    private val _effect = Channel<SearchEffect>()
    val effect = _effect.receiveAsFlow()

    // UI actions
    fun processIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.OnSearchQueryChanged -> {
                _state.update { it.copy(query = intent.query) }
            }
            is SearchIntent.OnSearchClicked -> {
                executeSearch()
            }
            is SearchIntent.OnGameSelected -> {
                triggerEffect(NavigateToGameDetails(intent.gameId))
            }

            is SearchIntent.OnRetrySearch -> executeSearch()

            is SearchIntent.OnToggleQueueStatus -> {
                triggerEffect(NavigateToGameDetails(intent.game.id))
            }

        }
    }

    private fun executeSearch() {
        val currentQuery = _state.value.query
        if (currentQuery.isBlank()) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                // this UseCase interacts with the Repository
                // which handles the Ktor fetch and Room insertion.
                getRecommendationsUseCase(currentQuery)

                // Assuming the UI observes the Room Database flow separately,
                // or the UseCase returns the success status.
                _state.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown Error") }

                Log.d("SEARCH", "Searh failed at SearchViewModel")
                triggerEffect(SearchEffect.ShowToast("Search failed."))
            }
        }
    }

    private fun triggerEffect(effect: SearchEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
