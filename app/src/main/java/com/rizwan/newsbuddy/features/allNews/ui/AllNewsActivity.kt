package com.rizwan.newsbuddy.features.allNews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizwan.newsbuddy.R
import com.rizwan.newsbuddy.databinding.ActivityAllNewsBinding

class AllNewsActivity : AppCompatActivity() {

    //private val TAG = "AllNewsActivity"

    private lateinit var mBinding: ActivityAllNewsBinding
    private val mViewModel by viewModels<AllNewsViewModel>()

    private lateinit var adapter: AllNewsAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_all_news
        )

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = AllNewsAdapter()
        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = layoutManager
    }

    private fun observeViewModel() {
        mViewModel.newsList.observe(this, Observer {
            adapter.differ.submitList(it)
        })
    }
}