package com.rizwan.newsbuddy.features.allNews.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.rizwan.newsbuddy.features.allNews.database.NewsDatabase
import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import com.rizwan.newsbuddy.features.allNews.ui.News
import retrofit2.Response

class LocalDataSource(context: Context) {

    private val DB_NAME = "allNews"
    private var db: NewsDatabase

    init {
        db = Room.databaseBuilder(context, NewsDatabase::class.java, DB_NAME).build()
    }

}