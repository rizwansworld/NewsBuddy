package com.rizwan.newsbuddy.features.allNews.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.rizwan.newsbuddy.features.allNews.models.API
import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import com.rizwan.newsbuddy.features.allNews.ui.News
import com.rizwan.newsbuddy.networking.Resource
import com.rizwan.newsbuddy.utils.ApiResponse
import com.rizwan.newsbuddy.utils.ApiSuccessResponse
import com.rizwan.newsbuddy.utils.ApplicationContext
import com.rizwan.newsbuddy.utils.NetworkBoundResource

class AllNewsRepository {

    private val TAG = "AllNewsRepository"

    private val remoteDataSource = RemoteDataSource()
    private val localDatasource = LocalDataSource(ApplicationContext.getInstance())

    fun getAllNews(countryCode: String, pageNumber: Int): LiveData<Resource<List<News>>> {
        return object : NetworkBoundResource<AllNewsDataModel, List<News>>() {
            override fun processResponse(response: ApiSuccessResponse<AllNewsDataModel>): List<News> {
                return response.body.articles
            }

            override fun saveCallResult(item: List<News>) {
                localDatasource.saveAllNews(item)
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                return data == null || data.isEmpty() || APIUpdateCheck().shouldFetch(API.ALLNEWS)
            }

            override fun loadFromDb(): LiveData<List<News>> {
                return localDatasource.getAllNews()
            }

            override fun createCall(): LiveData<ApiResponse<AllNewsDataModel>> {
                return remoteDataSource.getAllNews(countryCode, pageNumber)
            }

        }.asLiveData()

    }


}