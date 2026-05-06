package com.alaric.data.remote

import com.google.gson.annotations.SerializedName


data class RecommendationRequest(
    @SerializedName("query")
    val query: String,
    @SerializedName("playHistory")
    val playHistory: List<String> = emptyList()
)
