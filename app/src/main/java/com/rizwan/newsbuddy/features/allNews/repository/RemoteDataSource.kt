package com.rizwan.newsbuddy.features.allNews.repository

import com.rizwan.newsbuddy.features.allNews.ui.News
import com.rizwan.newsbuddy.networking.Resource
import com.rizwan.newsbuddy.networking.WebServiceInstance

class RemoteDataSource : DataSource {

    //:TODO Generic
    //:TODO Repetative RemoteDataSource for all API calls
    //:TODO Interface, abstract classes etc.

    //:TODO Keys change in API response

    //:TODO What happens to user's local data in database on App update

    override suspend fun getAllNews(countryCode: String, pageNumber: Int): Resource<List<News>> {
        val response = WebServiceInstance.api.getAllNews(countryCode, pageNumber)
        if (response.isSuccessful) {
            response.body()?.let {

                return if (it.articles.isNotEmpty())
                    Resource.Success(it.articles)
                else
                    Resource.Empty()

            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveNews(news: News) {
        // do nothing
    }

}