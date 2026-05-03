package com.alaric.aigamerecommender.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun Semantix() {
    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar() {
                NavigationBarItem(
                    selected = currentDestination?.route == SemantixScreens.Queue.toString(),
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
                    icon = { Icon(imageVector = Icons.Default.Menu, contentDescription = "Queue") }
                )
                NavigationBarItem(
                    selected = currentDestination?.route == SemantixScreens.Search.toString(),
                    onClick = {
                        navHostController.navigate(SemantixScreens.Search) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") }
                )
                NavigationBarItem(
                    selected = currentDestination?.route == SemantixScreens.Profile.toString(),
                    onClick = {
                        navHostController.navigate(SemantixScreens.Profile) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "profile") }
                )
            }
        }
    ) { innerPadding ->
        SemantixHost(
            modifier = Modifier.padding(innerPadding),
            navHostController
        )
    }
}