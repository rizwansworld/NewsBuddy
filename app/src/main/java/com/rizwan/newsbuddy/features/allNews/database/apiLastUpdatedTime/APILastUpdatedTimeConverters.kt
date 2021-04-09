package com.rizwan.newsbuddy.features.allNews.database.apiLastUpdatedTime

import androidx.room.TypeConverter
import com.rizwan.newsbuddy.features.allNews.models.API

class APILastUpdatedTimeConverters {

    @TypeConverter
    fun fromAPIEnum(api: API) : String {
        return api.name
    }

    @TypeConverter
    fun toAPIEnum(string: String) : API {
        return API.valueOf(string)
    }
}