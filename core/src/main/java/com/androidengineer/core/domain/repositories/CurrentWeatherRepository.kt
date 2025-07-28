package com.androidengineer.core.domain.repositories

import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.model.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun getWeatherData(apiKey: String, lat: Double, long: Double, days: Int): Flow<Result<WeatherForecast>>
}