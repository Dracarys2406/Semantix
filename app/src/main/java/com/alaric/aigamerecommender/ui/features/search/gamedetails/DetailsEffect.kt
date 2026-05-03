package com.alaric.aigamerecommender.ui.features.search.gamedetails

sealed interface DetailsEffect {
    data class ShowToast(val message: String) : DetailsEffect
}