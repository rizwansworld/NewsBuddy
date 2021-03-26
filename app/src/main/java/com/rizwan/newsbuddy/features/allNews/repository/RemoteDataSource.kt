package com.rizwan.newsbuddy.features.allNews.repository

import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import com.rizwan.newsbuddy.features.allNews.ui.News
import com.rizwan.newsbuddy.networking.WebServiceInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource : DataSource {

    override suspend fun getAllNews(countryCode: String, pageNumber: Int): Response<AllNewsDataModel> {
        return WebServiceInstance.api.getAllNews(countryCode, pageNumber)
    }

    override suspend fun saveNews(news: News) {
        // do nothing
    }

}