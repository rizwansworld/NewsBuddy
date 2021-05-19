package com.rizwan.newsbuddy.features.allNews.database.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rizwan.newsbuddy.features.allNews.ui.News

@Dao
abstract class NewsDao {
    @Insert
    abstract fun save(news: News)

    @Query("SELECT * FROM allNews")
    abstract fun load(): LiveData<List<News>>

    @Query("DELETE FROM allNews")
    abstract fun deleteAllNews()
}