package com.rizwan.newsbuddy.features.allNews.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.rizwan.newsbuddy.features.allNews.database.news.NewsDatabase
import com.rizwan.newsbuddy.features.allNews.ui.News

class LocalDataSource(context: Context) {

    //private val TAG = "LocalDataSource"

    private val DB_NAME = "allNews"
    private var db: NewsDatabase

    init {
        db = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            DB_NAME
        ).build()
    }

    fun getAllNews(): LiveData<List<News>> {
        return db.newsDao().load()
    }

    fun saveAllNews(newsList: List<News>) {
        db.newsDao().deleteAllNews()
        newsList.forEach {
            db.newsDao().save(it)
        }
    }

}