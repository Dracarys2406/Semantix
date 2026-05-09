package com.alaric.aigamerecommender.ui.features.search

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

        // --- Status indicators ---
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        if (state.error != null) {
            Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
        }



        // --- Data list ---
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item {
                GenreFilterBar(
                    state.availableGenres,
                    state.selectedGenres,
                    onIntent
                )
            }
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
fun GenreFilterBar(
    availableGenres: List<String>,
    selectedGenres: List<String>,
    onGenreToggled: (SearchIntent) -> Unit
) {
    // to expand the filter view
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            // limit to 2 lines unless expanded
            maxLines = if (isExpanded) Int.MAX_VALUE else 2,
            modifier = Modifier.animateContentSize()
        ) {
            availableGenres.forEach { genre ->
                FilterChip(
                    selected = selectedGenres.contains(genre),
                    onClick = { onGenreToggled(SearchIntent.OnGenreToggled(genre)) },
                    label = { Text(genre) },
                    leadingIcon = if (selectedGenres.contains(genre)) {
                        { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp)) }
                    } else null
                )
            }
        }

        // Only show the "Expand" button if we actually have a lot of genres
        if (availableGenres.size > 6) {
            TextButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(if (isExpanded) "Show Less" else "Show All Filters")
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
            .fillMaxWidth().clickable { onIntent(SearchIntent.OnGameSelected(game.id)) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = game.title, style = MaterialTheme.typography.titleMedium)

            // Safe call in case summary is null
            Text(text = game.summary?.take(100)?.plus("...") ?: "No summary", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}