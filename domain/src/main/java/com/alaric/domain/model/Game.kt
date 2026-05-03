package com.alaric.domain.model


data class Game(
    val id: Int,
    val title: String,
    val coverUrl: String? = null,
    val prominentImageUrl : String? = null,
    val screenshots: List<String>? = null,
    val rating: Double? = null,
    val summary: String? = null,
    val genres: List<String>? = null,
    val platforms: List<String>? = null,
    val releaseDate: String? = null,

    val isStoredInQueue : Boolean = false
)
