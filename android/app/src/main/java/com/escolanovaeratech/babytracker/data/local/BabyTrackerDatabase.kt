package com.escolanovaeratech.babytracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [EventEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class BabyTrackerDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        private const val DATABASE_NAME = "baby_tracker.db"

        @Volatile
        private var INSTANCE: BabyTrackerDatabase? = null

        fun getInstance(context: Context): BabyTrackerDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): BabyTrackerDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                BabyTrackerDatabase::class.java,
                DATABASE_NAME,
            ).build()
    }
}
