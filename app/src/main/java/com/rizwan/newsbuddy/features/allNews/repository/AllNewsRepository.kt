package com.rizwan.newsbuddy.features.allNews.repository

import com.rizwan.newsbuddy.features.allNews.ui.News
import com.rizwan.newsbuddy.networking.Resource
import com.rizwan.newsbuddy.utils.ApplicationContext

class AllNewsRepository {

    private val remoteDataSource = RemoteDataSource()
    private val localDatasource = LocalDataSource(ApplicationContext.getInstance())

    suspend fun getAllNews(countryCode: String, pageNumber: Int): Resource<List<News>> {
        val localData = localDatasource.getAllNews(countryCode, pageNumber)

        if (localData is Resource.Empty || localData is Resource.Outdated) {
            val remoteData = remoteDataSource.getAllNews(countryCode, pageNumber)

            if (remoteData is Resource.Success) {
                localDatasource.clearAllNews()
                localDatasource.saveAllNews(remoteData.data!!)
            }

            return remoteData
        }

        return localData
    }



}