package com.rizwan.newsbuddy.features.allNews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rizwan.newsbuddy.features.allNews.ui.News

@Database(
    entities = [News::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}