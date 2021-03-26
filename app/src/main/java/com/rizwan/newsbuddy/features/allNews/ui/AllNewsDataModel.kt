package com.rizwan.newsbuddy.features.allNews.ui

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rizwan.newsbuddy.utils.DateUtils
import com.rizwan.newsbuddy.utils.Macros

data class AllNewsDataModel(
    val articles: List<News>,
    val status: String? = null,
    val totalResults: Int? = null
)

@Entity(tableName = "allNews")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) {
    fun getPublishedDate() : String {
        return DateUtils.getDateAnyFomat(publishedAt, Macros.DATE_FORMAT_1, Macros.DATE_FORMAT_2)
    }
}

data class Source(
    val id: String? = null,
    val name: String? = null
)