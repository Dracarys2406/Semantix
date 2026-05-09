package com.alaric.aigamerecommender.ui.features.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaric.aigamerecommender.ui.features.search.SearchEffect.*
import com.alaric.domain.usecase.GetRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())

    private val _selectedGenres = MutableStateFlow<List<String>>(emptyList())

    // Channel for one-time effects like toasts and snackbars
    private val _effect = Channel<SearchEffect>()
    val effect = _effect.receiveAsFlow()

    // combines DB flow, filters, and UI state into one output
    val state: StateFlow<SearchState> = combine(
        getRecommendationsUseCase.observeSearchGames(),
        _selectedGenres,
        _uiState
    ) { rawGames, selected, uiState ->

        // get all unique genres currently present in the search results
        val genres = rawGames
            .flatMap { it.genres ?: emptyList() }
            .distinct()
            .sorted()

        // Filter the list. If empty, show all. If not, only show games matching ALL selected genres.
        val filtered = if (selected.isEmpty()) {
            rawGames
        } else {
            rawGames.filter { game ->
                game.genres?.containsAll(selected) == true
            }
        }

        // Output the final combined state to the UI
        uiState.copy(
            recommendedGames = filtered,
            availableGenres = genres,
            selectedGenres = selected
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), // timeout to keep flow alive through config changes
        initialValue = SearchState()
    )

    // UI actions
    fun processIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.OnSearchQueryChanged -> {
                _uiState.update { it.copy(query = intent.query) }
            }
            is SearchIntent.OnSearchClicked -> {
                executeSearch()
            }
            is SearchIntent.OnGameSelected -> {
                triggerEffect(NavigateToGameDetails(intent.gameId))
            }
            is SearchIntent.OnRetrySearch -> {
                executeSearch()
            }
            is SearchIntent.OnToggleQueueStatus -> {
                triggerEffect(NavigateToGameDetails(intent.game.id))
            }

            // Filter logic -------------------
            is SearchIntent.OnGenreToggled -> {
                val currentList = _selectedGenres.value
                if (currentList.contains(intent.genre)) {
                    // Remove if it's already selected
                    _selectedGenres.value = currentList - intent.genre
                } else {
                    // Add if it's a new selection
                    _selectedGenres.value = currentList + intent.genre
                }
            }
            is SearchIntent.OnClearFilters -> {
                _selectedGenres.value = emptyList()
            }
        }
    }

    private fun executeSearch() {
        val currentQuery = _uiState.value.query
        if (currentQuery.isBlank()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            // Auto-clear filters on a new search so the user sees all results initially
            _selectedGenres.value = emptyList()

            try {
                getRecommendationsUseCase(currentQuery)
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Unknown Error") }
                Log.d("SEARCH", "Search failed at SearchViewModel")
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