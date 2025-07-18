package com.androidengineer

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

inline fun formatDateTime(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH)

    return try {
        val trimmedInput = input.trim()
        val date: Date = inputFormat.parse(trimmedInput) ?: return ""
        outputFormat.format(date)
    } catch (e: Exception) {
        "Invalid date"
    }
}

fun formatDate(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
    val date = inputFormat.parse(input)
    return outputFormat.format(date)
}