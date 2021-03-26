package com.rizwan.newsbuddy.features.allNews.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rizwan.newsbuddy.features.allNews.ui.News

@Dao
interface NewsDao {
    @Insert
    suspend fun save(news: News)

    @Query("SELECT * FROM allNews")
    fun load(): LiveData<List<News>>
}