package com.rizwan.newsbuddy.features.allNews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AllNewsViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AllNewsViewModel() as T
    }
}