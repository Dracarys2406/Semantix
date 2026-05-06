package com.alaric.aigamerecommender.ui.features.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileRoute (
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ProfileEffect.HideKeyboard -> {}
                is ProfileEffect.NavigateToGameDetails -> {onNavigateToDetails(effect.gameId)}
                is ProfileEffect.ShowSnackbar -> {}
                is ProfileEffect.ShowToast -> {}
            }
        }
    }

    ProfileScreen(
        profileState = state,
        onIntent = viewModel::processIntent
    )
}