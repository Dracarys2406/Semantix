package com.alaric.aigamerecommender.ui.features.queue

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun QueueRoute (
    viewModel: QueueViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                QueueEffect.HideKeyboard -> {}
                is QueueEffect.NavigateToGameDetails -> {onNavigateToDetails(effect.gameId)}
                is QueueEffect.ShowSnackbar -> {}
                is QueueEffect.ShowToast -> {}
            }
        }
    }

    QueueScreen(
        queueState = state,
        onIntent = viewModel::processIntent
    )
}