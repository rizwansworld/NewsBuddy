package com.rizwan.newsbuddy.networking

import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import com.rizwan.newsbuddy.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<AllNewsDataModel>

}