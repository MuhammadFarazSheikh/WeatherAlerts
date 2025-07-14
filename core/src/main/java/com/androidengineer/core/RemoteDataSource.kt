package com.androidengineer.core

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getWeatherData(apiKey: String, lat: Double, long: Double, days: Int) = try {
        Response.Success(apiService.getWeatherData(apiKey, "$lat,$long", days))
    } catch (e: Exception) {
        Response.Error(e.message.toString())
    }
}