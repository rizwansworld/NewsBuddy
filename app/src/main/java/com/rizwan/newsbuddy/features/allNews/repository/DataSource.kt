package com.rizwan.newsbuddy.features.allNews.repository

import androidx.lifecycle.LiveData
import com.rizwan.newsbuddy.features.allNews.ui.News
import com.rizwan.newsbuddy.networking.Resource

interface DataSource {

    suspend fun getAllNews(countryCode: String, pageNumber: Int): LiveData<Any>
    suspend fun saveNews(news: News)

}