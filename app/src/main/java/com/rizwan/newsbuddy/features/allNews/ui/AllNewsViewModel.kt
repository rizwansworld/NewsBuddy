package com.rizwan.newsbuddy.features.allNews.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizwan.newsbuddy.features.allNews.repository.AllNewsRepository
import com.rizwan.newsbuddy.networking.Resource
import kotlinx.coroutines.launch

class AllNewsViewModel : ViewModel() {

    private val TAG = "AllNewsViewModel"

    //:TODO MutableLiveData & LiveData relation
    //:TODO Testing ViewModel

    /**
     * Repository can be a dependency
     *
     * Test cases for ViewModel:
     * Success
     * Empty
     * Error
     *
     */

    private val _newsList = MutableLiveData<List<News>>()
    val newsList : LiveData<List<News>> = _newsList

    private val _progressBarVisibility = MutableLiveData<Int>(View.GONE)
    val progressBarVisibility : LiveData<Int> = _progressBarVisibility

    private val _emptyViewVisibility = MutableLiveData<Int>(View.GONE)
    val emptyViewVisibility : LiveData<Int> = _emptyViewVisibility

    private val _errorViewVisibility = MutableLiveData<Int>(View.GONE)
    val errorViewVisibility : LiveData<Int> = _errorViewVisibility

    private val pageNumber = 1

    init {
        getAllNews("in")
    }

    private fun getAllNews(countryCode: String) = viewModelScope.launch {
        showProgressBar()
        val resource = AllNewsRepository().getAllNews(countryCode, pageNumber)
        handleResource(resource)
    }

    private fun handleResource(resource: Resource<AllNewsDataModel>) {
        hideProgressBar()
        when (resource) {

            is Resource.Success -> {
                _newsList.postValue(resource.data?.articles)
            }
            is Resource.Empty -> {
                showEmptyView()
            }
            is Resource.Error -> {
                showErrorView()
            }

        }
    }

    private fun showProgressBar() {
        _progressBarVisibility.value = View.VISIBLE
    }

    private fun hideProgressBar() {
        _progressBarVisibility.value = View.GONE
    }

    private fun showErrorView() {
        _errorViewVisibility.value = View.VISIBLE
    }

    private fun showEmptyView() {
        _emptyViewVisibility.value = View.VISIBLE
    }

}