package com.rizwan.newsbuddy.features.allNews.repository

import androidx.lifecycle.LiveData
import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import com.rizwan.newsbuddy.networking.WebServiceInstance
import com.rizwan.newsbuddy.utils.ApiResponse

class RemoteDataSource {

    //:TODO Generic
    //:TODO Repetative RemoteDataSource for all API calls
    //:TODO Interface, abstract classes etc.

    //:TODO Keys change in API response

    //:TODO What happens to user's local data in database on App update

    fun getAllNews(countryCode: String, pageNumber: Int): LiveData<ApiResponse<AllNewsDataModel>> {
        return WebServiceInstance.api.getAllNews(countryCode, pageNumber)
    }

}