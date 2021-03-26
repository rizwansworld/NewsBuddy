package com.rizwan.newsbuddy.features.allNews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizwan.newsbuddy.features.allNews.repository.AllNewsRepository
import com.rizwan.newsbuddy.networking.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class AllNewsViewModel : ViewModel() {

    private val _newsResponse = MutableLiveData<Resource<AllNewsDataModel>>()
    val newsResponse : LiveData<Resource<AllNewsDataModel>> = _newsResponse

    private val pageNumber = 1

    init {
        getAllNews("in")
    }

    private fun getAllNews(countryCode: String) = viewModelScope.launch {
        _newsResponse.postValue(Resource.Loading())
        val response = AllNewsRepository().getAllNews(countryCode, pageNumber)
        _newsResponse.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<AllNewsDataModel>) : Resource<AllNewsDataModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}