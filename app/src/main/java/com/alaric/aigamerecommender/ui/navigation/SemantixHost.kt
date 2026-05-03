package com.alaric.aigamerecommender.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alaric.aigamerecommender.ui.features.search.SearchRoute
import com.alaric.aigamerecommender.ui.features.search.gamedetails.GameDetailsRoute

@Composable
fun SemantixHost(
    modifier: Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navHostController,
        startDestination = SemantixScreens.Search
    ) {
        composable<SemantixScreens.Search> {
            SearchRoute { gameId ->
                navHostController.navigate("details/$gameId")
            }
        }

        composable<SemantixScreens.Queue> {
        }

        composable<SemantixScreens.Profile> {
        }

        composable(
            route = "details/{gameId}",
            arguments = listOf(
                navArgument("gameId") { type = NavType.IntType }
            )
        ) {
            // no need to extract id here because
            // DetailsViewModel grabs it directly with SavedStateHandle
            GameDetailsRoute(
                onNavigateBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}