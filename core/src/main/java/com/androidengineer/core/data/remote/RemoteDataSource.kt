package com.androidengineer.core.data.remote

import com.androidengineer.core.data.remote.api.ApiService
import com.androidengineer.core.data.remote.model.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getWeatherData(apiKey: String, lat: Double, long: Double, days: Int) = try {
        Response.Success(apiService.getWeatherData(apiKey, "$lat,$long", days))
    } catch (e: Exception) {
        Response.Error(e.message.toString())
    }

    suspend fun getSearchWeatherData(apiKey: String, city: String) = try {
        Response.Success(apiService.getSearchWeatherData(apiKey, city))
    } catch (e: Exception) {
        Response.Error(e.message.toString())
    }
}