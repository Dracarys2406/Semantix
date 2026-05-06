package com.alaric.aigamerecommender.ui.features.queue.queuedetails

import com.alaric.aigamerecommender.ui.features.search.gamedetails.DetailsIntent

interface QueueDetailsIntent {
    object OnDeleteFromQueue : QueueDetailsIntent
    object OnMarkFinished : QueueDetailsIntent
    object OnEditNote : QueueDetailsIntent

}