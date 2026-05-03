package com.alaric.aigamerecommender.ui.navigation

sealed interface AiGameRecommenderScreens {
    data object Queue : AiGameRecommenderScreens
    data object Search : AiGameRecommenderScreens
    data object Profile : AiGameRecommenderScreens
}