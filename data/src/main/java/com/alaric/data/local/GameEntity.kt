package com.alaric.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val coverUrl: String? = null,
    val rating: Double? = null,
    val summary: String? = null,
    val genres: List<String>? = null,
    val platforms: List<String>? = null,
    val releaseDate: Long? = null,
    // media
    val screenshots : List<String>? = null,

    val isStoredInQueue: Boolean = false
)
