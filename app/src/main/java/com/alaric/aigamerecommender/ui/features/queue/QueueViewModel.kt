package com.alaric.aigamerecommender.ui.features.queue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaric.aigamerecommender.ui.features.search.SearchEffect
import com.alaric.aigamerecommender.ui.features.search.SearchEffect.NavigateToGameDetails
import com.alaric.aigamerecommender.ui.features.search.SearchIntent
import com.alaric.domain.usecase.ManageQueueUseCase
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
class QueueViewModel @Inject constructor (
    manageQueueUseCase: ManageQueueUseCase
) : ViewModel(){

    init {
        // we sart observing the database when the ViewModel is created
        viewModelScope.launch {
            manageQueueUseCase.observeQueueGames().collect { games ->
                // Every time Room changes, this updates the UI state instantly
                _state.update { it.copy(games = games) }
            }
        }
    }

    private val _state = MutableStateFlow(QueueState())
    val state: StateFlow<QueueState> = _state.asStateFlow()

    // channel for one time effects like toasts and snackbars
    private val _effect = Channel<QueueEffect>()
    val effect = _effect.receiveAsFlow()

    // UI actions
    fun processIntent(intent: QueueIntent) {
        when (intent) {
            is QueueIntent.OnGameSelected -> {
                triggerEffect(QueueEffect.NavigateToGameDetails(intent.gameId))
            }
            is QueueIntent.OnDeleteFromQueue -> {

            }
            is QueueIntent.OnMarkComplete -> TODO()
        }
    }

    private fun triggerEffect(effect: QueueEffect) {
        viewModelScope.launch {
            _effect.send(effect)


        }
    }


}