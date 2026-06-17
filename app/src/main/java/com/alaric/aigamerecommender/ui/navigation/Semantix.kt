package com.alaric.aigamerecommender.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alaric.aigamerecommender.ui.theme.SemantixTheme

@Composable
fun Semantix() {
    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SemantixTheme.backgrounds.navBar)
            ) {
                NavigationBar(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary,
                    tonalElevation = 0.dp
                ) {
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute(SemantixScreens.Queue::class) == true,
                        onClick = {
                            navHostController.navigate(SemantixScreens.Queue) {
                                // save state when leaving tab
                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // avoid opening multiple copies if cilicked multiple times
                                launchSingleTop = true
                                // Restore the saved scroll position and state of the user is entering
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = Icons.Default.Menu, contentDescription = "Queue") },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                    )
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute(SemantixScreens.Search::class) == true,
                        onClick = {
                            navHostController.navigate(SemantixScreens.Search) {
                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                    )
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute(SemantixScreens.Profile::class) == true,
                        onClick = {
                            navHostController.navigate(SemantixScreens.Profile) {
                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "profile") },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                    )
                } // navigation bar ends
            }
        },
        modifier = Modifier.background(SemantixTheme.backgrounds.screen)
    ) { innerPadding ->
        SemantixHost(
            modifier = Modifier.padding(innerPadding),
            navHostController
        )
    }
}