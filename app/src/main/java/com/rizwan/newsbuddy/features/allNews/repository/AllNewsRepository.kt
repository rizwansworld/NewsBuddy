package com.rizwan.newsbuddy.features.allNews.repository

import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import com.rizwan.newsbuddy.networking.Resource
import retrofit2.Response

class AllNewsRepository {

    private val remoteDataSource = RemoteDataSource()
//    private val localDatasource = LocalDataSource(context)

    suspend fun getAllNews(countryCode: String, pageNumber: Int): Resource<AllNewsDataModel> {
        return handleResponse(remoteDataSource.getAllNews(countryCode, pageNumber))
    }

    private fun handleResponse(response: Response<AllNewsDataModel>) : Resource<AllNewsDataModel> {
        if (response.isSuccessful) {
            response.body()?.let {

                return if (it.articles.isNotEmpty())
                    Resource.Success(it)
                else
                    Resource.Empty()

            }
        }
        return Resource.Error(response.message())
    }

//    suspend fun getAllNews(): Response<AllNewsDataModel> {
//        var data = localDatasource.getAllNews()
//
//        if (data.isEmpty()) {
//            val remoteData = remoteDataSource.getAllNews()
//
//            remoteData.forEach {
//                localDatasource.saveNews(it)
//            }
//
//            data = localDatasource.getAllNews()
//        }
//
//        return data
//    }



}