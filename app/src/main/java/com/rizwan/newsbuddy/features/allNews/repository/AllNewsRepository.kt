package com.rizwan.newsbuddy.features.allNews.repository

import com.rizwan.newsbuddy.features.allNews.ui.AllNewsDataModel
import retrofit2.Response

class AllNewsRepository {

    private val remoteDataSource = RemoteDataSource()
//    private val localDatasource = LocalDataSource(context)

    suspend fun getAllNews(countryCode: String, pageNumber: Int): Response<AllNewsDataModel> {
        return remoteDataSource.getAllNews(countryCode, pageNumber)
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