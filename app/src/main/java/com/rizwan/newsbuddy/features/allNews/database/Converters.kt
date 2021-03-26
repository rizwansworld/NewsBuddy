package com.rizwan.newsbuddy.features.allNews.database

import androidx.room.TypeConverter
import com.rizwan.newsbuddy.features.allNews.ui.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name ?: ""
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}