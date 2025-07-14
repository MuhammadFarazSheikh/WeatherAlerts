package com.androidengineer.core

import com.androidengineer.core.data.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

const val WEATHER_FORECAST_ENDPOINT = "forecast.json"

interface ApiService {

    @GET(WEATHER_FORECAST_ENDPOINT)
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int
    ): WeatherForecast
}