package com.alaric.domain.model


data class Game(
    val id: Int,
    val title: String,
    val coverUrl: String? = null,
    val rating: Double? = null,
    val summary: String? = null,
    val genres: List<String>? = null,
    val platforms: List<String>? = null,
    val releaseDate: Long? = null
)
