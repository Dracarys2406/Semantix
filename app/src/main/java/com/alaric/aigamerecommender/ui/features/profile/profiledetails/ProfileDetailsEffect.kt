package com.alaric.aigamerecommender.ui.features.queue.queuedetails

interface QueueDetailsEffect {
    data class ShowToast(val message: String) : QueueDetailsEffect
}