package com.alaric.aigamerecommender.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface SemantixScreens {
    @Serializable
    data object Queue : SemantixScreens
    @Serializable
    data object Search : SemantixScreens
    @Serializable
    data object Profile : SemantixScreens
}