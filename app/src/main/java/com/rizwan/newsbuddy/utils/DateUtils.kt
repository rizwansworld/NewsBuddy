package com.rizwan.newsbuddy.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateAnyFomat(inputDate: String?, inputFormat: String, outputFormat: String): String {
        val inputDateFormatter = SimpleDateFormat(inputFormat, Locale.ENGLISH)
        val outputDateFormatter = SimpleDateFormat(outputFormat, Locale.ENGLISH)
        return try {
            val date = inputDateFormatter.parse(inputDate)
            outputDateFormatter.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

}