package com.rizwan.newsbuddy.features.allNews.repository

import android.util.Log
import com.rizwan.newsbuddy.features.allNews.database.apiLastUpdatedTime.APILastUpdatedTimeDatabase
import com.rizwan.newsbuddy.features.allNews.models.API
import com.rizwan.newsbuddy.features.allNews.models.APILastUpdatedTime
import com.rizwan.newsbuddy.utils.AppExecutors
import com.rizwan.newsbuddy.utils.ApplicationContext
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class APIUpdateCheck {

    //private val TAG = "APIUpdateCheck"

    var db: APILastUpdatedTimeDatabase =
        APILastUpdatedTimeDatabase(ApplicationContext.getInstance())

    fun shouldFetch(api: API): Boolean {
        val now = now()
        val timevalue = 3
        val timeUnit = TimeUnit.MINUTES
        val timeout = timeUnit.toMillis(timevalue.toLong())

        fun saveCurrentTimestamp() {
            //Log.e(TAG, "saveCurrentTimestamp: SAVING CURRENT API TIMESTAMP")
            AppExecutors.diskIO().execute {
                db.getDao().upsertTimestamp(
                    APILastUpdatedTime(
                        apiName = api,
                        timestamp = now
                    )
                )
            }
        }

        val lastFetched = AppExecutors.diskIO().submit(Callable<Long> {
            db.getDao().getLastUpdatedTime(api)
        }).get()

        return if (lastFetched == null || (now - lastFetched) > timeout) {
            saveCurrentTimestamp()
            true
        } else {
            false
        }

    }

    private fun now() = System.currentTimeMillis()

}