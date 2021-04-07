package com.rizwan.newsbuddy.features.allNews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rizwan.newsbuddy.R
import kotlinx.android.synthetic.main.row_all_news.view.*

class AllNewsAdapter : RecyclerView.Adapter<AllNewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_all_news,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this)
                .load(news.urlToImage)
                .centerCrop()
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.color.black)
                )
                .into(preview_image)

            source_tag.text = holder.itemView.context.getString(R.string.source_tag_text, news.source?.name ?: "-")
            title.text = news.title
            published_at.text = holder.itemView.context.getString(R.string.published_at_text, news.getPublishedDate())
            description.text = news.description
        }

    }
}