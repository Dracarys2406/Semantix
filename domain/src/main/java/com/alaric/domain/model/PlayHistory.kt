package com.alaric.domain.model

data class PlayHistory(
    val gameId: Int,
    val gameTitle: String,
    val lastPlayed: Long,
    val playTimeMinutes: Int = 0
)
