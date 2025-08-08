package com.androidengineer.core.utils

import android.icu.text.SimpleDateFormat
import java.util.Locale

const val DATE_TIME_FORMAT_OLD = "yyyy-MM-dd HH:mm"
const val DATE_TIME_FORMAT_NEW = "EEEE, MMMM dd, yyyy"
const val DATE_FORMAT_OLD = "yyyy-MM-dd"
const val DATE_FORMAT_NEW = "MMMM d, yyyy"

inline fun formatDateTime(input: String, oldFormat: String, newFormat: String): String {
    try {
        val inputFormat = SimpleDateFormat(oldFormat, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(newFormat, Locale.ENGLISH)
        return outputFormat.format(inputFormat.parse(input.trim()))
    } catch (e: Exception) {
        println(e.message)
        return ""
    }
}