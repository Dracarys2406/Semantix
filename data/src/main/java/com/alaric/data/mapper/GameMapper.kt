package com.alaric.data.mapper

import com.alaric.data.local.GameEntity
import com.alaric.domain.model.Game

fun GameEntity.toDomain(): Game {
    return Game(
        id = id,
        title = title,
        summary = summary,
        coverUrl = coverUrl,
        platforms = platforms,
        genres = genres
    )
}

fun Game.toEntity(isInQueue: Boolean = false): GameEntity {
    return GameEntity(
        id = id,
        title = title,
        summary = summary,
        coverUrl = coverUrl,
        platforms = platforms,
        genres = genres,
        isStoredInQueue = isInQueue
    )
}