package com.rizwan.newsbuddy.utils

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class ApplicationContext : MultiDexApplication() {

    companion object {
        lateinit var appContext: Context

        @JvmStatic
        fun getInstance() = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}