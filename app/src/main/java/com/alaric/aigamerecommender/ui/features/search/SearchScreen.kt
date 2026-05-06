package com.alaric.aigamerecommender.ui.features.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alaric.domain.model.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    onIntent: (SearchIntent) -> Unit // The single pipeline for all actions. makes this stateless
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // --- Search Bar ---
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = state.query,
                onValueChange = { onIntent(SearchIntent.OnSearchQueryChanged(it)) },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Describe a game...") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onIntent(SearchIntent.OnSearchClicked) }) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- Status Indicators ---
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        if (state.error != null) {
            Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
        }

        // --- Data List ---
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.recommendedGames, key = { it.id }) { game ->
                GameCardSearch(
                    game = game,
                    onIntent = onIntent
                )
            }
        }
    }
}

@Composable
fun GameCardSearch(
    game: Game,
    onIntent: (SearchIntent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Here is where the navigation intent is fired!
            .clickable { onIntent(SearchIntent.OnGameSelected(game.id)) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = game.title, style = MaterialTheme.typography.titleMedium)

            // Safe call in case summary is null
            Text(text = game.summary?.take(100)?.plus("...") ?: "No summary", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}