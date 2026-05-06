package com.alaric.data.local.playhistory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "play_history")
data class PlayHistoryEntity(
    @PrimaryKey val gameId: Int,
    val title: String,
    val timestamp: Long = System.currentTimeMillis(),
    val  coverUrl : String
)