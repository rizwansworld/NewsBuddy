package com.rizwan.newsbuddy.features.allNews.repository

import com.rizwan.newsbuddy.features.allNews.ui.News
import com.rizwan.newsbuddy.networking.Resource

interface DataSource {

    suspend fun getAllNews(countryCode: String, pageNumber: Int): Resource<List<News>>
    suspend fun saveNews(news: News)

}