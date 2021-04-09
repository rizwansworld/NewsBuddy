package com.rizwan.newsbuddy.features.allNews.repository

import android.os.SystemClock
import com.rizwan.newsbuddy.features.allNews.database.apiLastUpdatedTime.APILastUpdatedTimeDatabase
import com.rizwan.newsbuddy.features.allNews.models.API
import com.rizwan.newsbuddy.features.allNews.models.APILastUpdatedTime
import com.rizwan.newsbuddy.utils.ApplicationContext
import java.util.concurrent.TimeUnit

class APIUpdateCheck {
    var db: APILastUpdatedTimeDatabase =
        APILastUpdatedTimeDatabase(ApplicationContext.getInstance())

    suspend fun shouldFetch(api: API): Boolean {
        val now = now()
        val timevalue = 1
        val timeUnit = TimeUnit.MINUTES
        val timeout = timeUnit.toMillis(timevalue.toLong())

        suspend fun saveCurrentTimestamp() {
            db.getDao().upsertTimestamp(
                APILastUpdatedTime(
                    apiName = api,
                    timestamp = now
                )
            )
        }

        val lastFetched = db.getDao().getLastUpdatedTime(api)

        return if (lastFetched == null || now - lastFetched > timeout) {
            saveCurrentTimestamp()
            true
        } else {
            false
        }
    }

    private fun now() = SystemClock.uptimeMillis()

}