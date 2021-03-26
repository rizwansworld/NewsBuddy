package com.rizwan.newsbuddy.features.allNews.repository

import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import com.rizwan.newsbuddy.features.allNews.ui.News
import retrofit2.Response

interface DataSource {

    suspend fun getAllNews(countryCode: String, pageNumber: Int): Response<AllNewsDataModel>
    suspend fun saveNews(news: News)

}