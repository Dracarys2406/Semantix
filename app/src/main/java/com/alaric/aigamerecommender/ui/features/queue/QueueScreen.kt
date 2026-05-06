package com.alaric.aigamerecommender.ui.features.queue

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alaric.domain.model.Game

@Composable
fun QueueScreen(
    queueState: QueueState,
    onIntent: (QueueIntent) -> Unit
) {
    LazyColumn {
        items(queueState.games) { game ->
            QueueGame(
                game = game,
                onIntent = onIntent
            )
        }
    }
}

@Composable
fun QueueGame(
    game: Game,
    onIntent: (QueueIntent) -> Unit
) {
    ElevatedCard(
        onClick = { onIntent(QueueIntent.OnGameSelected(game.id)) },
        Modifier.fillMaxWidth(0.9f).height(40.dp)
    ) {
        Text(game.title, style = MaterialTheme.typography.titleMedium)
    }
}
