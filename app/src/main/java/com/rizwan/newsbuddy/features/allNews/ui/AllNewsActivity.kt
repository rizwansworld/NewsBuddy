package com.rizwan.newsbuddy.features.allNews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizwan.newsbuddy.R
import com.rizwan.newsbuddy.databinding.ActivityAllNewsBinding
import com.rizwan.newsbuddy.features.allNews.repository.AllNewsRepository
import com.rizwan.newsbuddy.networking.Resource

class AllNewsActivity : AppCompatActivity() {

    private val TAG = "AllNewsActivity"

    private lateinit var mBinding: ActivityAllNewsBinding
    private lateinit var repository: AllNewsRepository
    private lateinit var mViewModel: AllNewsViewModel

    private val layoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_all_news
        )

        repository = AllNewsRepository()
        mViewModel = ViewModelProvider(this).get(AllNewsViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        mBinding.recyclerView.layoutManager = layoutManager
    }

    private fun observeViewModel() {
        mViewModel.newsResponse.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        mBinding.recyclerView.adapter = AllNewsAdapter(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun showProgressBar() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
    }
}