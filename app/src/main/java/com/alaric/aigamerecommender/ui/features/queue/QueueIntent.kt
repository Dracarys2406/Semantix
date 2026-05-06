package com.alaric.aigamerecommender.ui.features.queue

import com.alaric.aigamerecommender.ui.features.search.SearchIntent

sealed interface QueueIntent{
    data class OnDeleteFromQueue(val gameId : Int) : QueueIntent
    data class OnMarkComplete(val gameId : Int) : QueueIntent
    data class OnGameSelected(val gameId: Int) : QueueIntent

}