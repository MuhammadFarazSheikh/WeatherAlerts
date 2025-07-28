package com.androidengineer.core.domain.repositories

import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.model.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface SearchWeatherRepository {
    suspend fun searchWeatherData(apiKey: String, location: String): Flow<Result<WeatherForecast>>
}