package com.alaric.aigamerecommender.ui.features.search.gamedetails

sealed interface DetailsIntent {
    object OnToggleQueueStatus : DetailsIntent
}