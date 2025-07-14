package com.androidengineer.core

import com.androidengineer.core.domain.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun getWeatherData(apiKey: String, lat: Double, long: Double, days: Int): Flow<Result<WeatherForecast>>
}