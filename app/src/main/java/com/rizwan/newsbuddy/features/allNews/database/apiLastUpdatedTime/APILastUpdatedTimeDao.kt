package com.rizwan.newsbuddy.features.allNews.database.apiLastUpdatedTime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rizwan.newsbuddy.features.allNews.models.API
import com.rizwan.newsbuddy.features.allNews.models.APILastUpdatedTime
import java.util.*

@Dao
interface APILastUpdatedTimeDao {

    @Query("SELECT timestamp from apiLastUpdatedTime WHERE apiName = :api")
    suspend fun getLastUpdatedTime(api: API) : Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTimestamp(apiLastUpdatedTime: APILastUpdatedTime)

}