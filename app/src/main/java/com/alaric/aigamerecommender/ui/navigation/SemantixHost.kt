package com.alaric.aigamerecommender.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alaric.aigamerecommender.ui.features.profile.ProfileRoute
import com.alaric.aigamerecommender.ui.features.profile.profiledetails.ProfileGameDetailsRoute
import com.alaric.aigamerecommender.ui.features.queue.QueueRoute
import com.alaric.aigamerecommender.ui.features.queue.queuedetails.QueueGameDetailsRoute
import com.alaric.aigamerecommender.ui.features.search.SearchRoute
import com.alaric.aigamerecommender.ui.features.search.gamedetails.GameDetailsRoute

@Composable
fun SemantixHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navHostController,
        startDestination = SemantixScreens.Search,
        modifier = modifier
    ) {
        composable<SemantixScreens.Search> {
            SearchRoute { gameId ->
                navHostController.navigate("details/$gameId")
            }
        }

        composable<SemantixScreens.Queue> {
            QueueRoute() { gameId ->
                navHostController.navigate("queueDetails/$gameId")
            }
        }

        composable<SemantixScreens.Profile> {
            ProfileRoute() { gameId ->
                navHostController.navigate("profileDetails/$gameId")
            }
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

        composable(
            route = "queueDetails/{gameId}",
            arguments = listOf(
                navArgument("gameId") { type = NavType.IntType }
            )
        ) {

            QueueGameDetailsRoute(
                onNavigateBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(
            route = "profileDetails/{gameId}",
            arguments = listOf(
                navArgument("gameId") { type = NavType.IntType }
            )
        ) {

            ProfileGameDetailsRoute(
                onNavigateBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}