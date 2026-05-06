package com.alaric.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alaric.data.local.playhistory.PlayHistoryDao
import com.alaric.data.local.playhistory.PlayHistoryEntity

@Database(
    entities = [GameEntity::class, PlayHistoryEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract val dao: GameDao
    abstract val playHistoryDao: PlayHistoryDao
}