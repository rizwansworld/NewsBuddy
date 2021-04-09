package com.rizwan.newsbuddy.features.allNews.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apiLastUpdatedTime")
data class APILastUpdatedTime(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,
    val apiName: API,
    val timestamp: Long
) {
}

enum class API {
    ALLNEWS
}