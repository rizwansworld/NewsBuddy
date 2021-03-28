package com.rizwan.newsbuddy.features.allNews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rizwan.newsbuddy.R
import com.rizwan.newsbuddy.databinding.RowAllNewsBinding

class AllNewsAdapter(private val mList: List<News>) : RecyclerView.Adapter<AllNewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding : RowAllNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_all_news, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemBinding.sourceTag.text = holder.itemView.context.getString(R.string.source_tag_text, mList[position].source?.name ?: "-")
        holder.itemBinding.title.text = mList[position].title
        holder.itemBinding.publishedAt.text = holder.itemView.context.getString(R.string.published_at_text, mList[position].getPublishedDate())
        holder.itemBinding.description.text = mList[position].description

        Glide
            .with(holder.itemView.context)
            .load(mList[position].urlToImage)
            .centerCrop()
            .apply(
                RequestOptions()
                .placeholder(R.color.black)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            )
            .into(holder.itemBinding.previewImage)

    }

    inner class ViewHolder(val itemBinding: RowAllNewsBinding): RecyclerView.ViewHolder(itemBinding.root)
}