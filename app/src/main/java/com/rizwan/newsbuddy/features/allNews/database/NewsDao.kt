package com.rizwan.newsbuddy.features.allNews.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rizwan.newsbuddy.features.allNews.ui.News

@Dao
interface NewsDao {
    @Insert
    suspend fun save(news: News)

    @Query("SELECT * FROM allNews")
    suspend fun load(): List<News>

    @Query("DELETE FROM allNews")
    suspend fun deleteAllNews()
}