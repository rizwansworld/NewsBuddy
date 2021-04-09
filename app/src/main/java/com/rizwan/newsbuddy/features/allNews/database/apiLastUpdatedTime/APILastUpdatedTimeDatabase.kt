package com.rizwan.newsbuddy.features.allNews.database.apiLastUpdatedTime

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rizwan.newsbuddy.features.allNews.models.APILastUpdatedTime

@Database(
    entities = [APILastUpdatedTime::class],
    version = 1
)
@TypeConverters(APILastUpdatedTimeConverters::class)
abstract class APILastUpdatedTimeDatabase : RoomDatabase() {

    abstract fun getDao(): APILastUpdatedTimeDao

    companion object {
        private var instance: APILastUpdatedTimeDatabase?= null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                APILastUpdatedTimeDatabase::class.java,
                "apiLastUpdatedTime"
            ).build()
    }
}