package com.alaric.aigamerecommender.ui.features.search

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchEffect.NavigateToGameDetails -> onNavigateToDetails(effect.gameId)
                is SearchEffect.ShowToast -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                is SearchEffect.ShowSnackbar -> { /* Implementation for Snackbar later */ }
                is SearchEffect.HideKeyboard -> { /* Implementation for FocusManager later */ }
            }
        }
    }

    SearchScreen(
        state = state,
        onIntent = viewModel::processIntent
    )
}