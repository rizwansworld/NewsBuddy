package com.rizwan.newsbuddy.features.allNews.ui

import android.view.View
import androidx.lifecycle.*
import com.rizwan.newsbuddy.features.allNews.repository.AllNewsRepository
import com.rizwan.newsbuddy.networking.Resource

class AllNewsViewModel : ViewModel() {

    //private val TAG = "AllNewsViewModel"

    private val _progressBarVisibility = MutableLiveData<Int>(View.GONE)
    val progressBarVisibility : LiveData<Int> = _progressBarVisibility

    private val _emptyViewVisibility = MutableLiveData<Int>(View.GONE)
    val emptyViewVisibility : LiveData<Int> = _emptyViewVisibility

    private val _errorViewVisibility = MutableLiveData<Int>(View.GONE)
    val errorViewVisibility : LiveData<Int> = _errorViewVisibility

    private val pageNumber = 1

    val newsList : LiveData<List<News>> = Transformations.map(
        AllNewsRepository().getAllNews("sa", pageNumber)
    ) { resource ->
        when (resource) {
            is Resource.Success -> {
                hideProgressBar()
                resource.data
            }
            is Resource.Empty -> {
                hideProgressBar()
                showEmptyView()
                resource.data
            }
            is Resource.Error -> {
                hideProgressBar()
                showErrorView()
                resource.data
            }
            is Resource.Loading -> {
                showProgressBar()
                listOf() // empty list
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