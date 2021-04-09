package com.rizwan.newsbuddy.features.allNews.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.rizwan.newsbuddy.features.allNews.database.news.NewsDatabase
import com.rizwan.newsbuddy.features.allNews.models.API
import com.rizwan.newsbuddy.features.allNews.ui.News
import com.rizwan.newsbuddy.networking.Resource

class LocalDataSource(context: Context) : DataSource {

    private val TAG = "LocalDataSource"

    private val DB_NAME = "allNews"
    private var db: NewsDatabase

    init {
        db = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            DB_NAME
        ).build()
    }

    override suspend fun getAllNews(
        countryCode: String,
        pageNumber: Int
    ): Resource<List<News>> {
        val list = db.newsDao().load()

        Log.e(TAG, "shouldFetch: ${APIUpdateCheck().shouldFetch(API.ALLNEWS)}")

        return when {
            APIUpdateCheck().shouldFetch(API.ALLNEWS) -> {
                Resource.Outdated()
            }
            list.isEmpty() -> {
                Resource.Empty()
            }
            else -> {
                Resource.Success(list)
            }
        }
    }

    override suspend fun saveNews(news: News) {
        db.newsDao().save(news)
    }

    suspend fun saveAllNews(newsList: List<News>) {
        newsList.forEach {
            db.newsDao().save(it)
        }
    }

    suspend fun clearAllNews() {
        db.newsDao().deleteAllNews()
    }

}