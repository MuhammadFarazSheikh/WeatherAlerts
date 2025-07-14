package com.androidengineer.core

import com.androidengineer.core.domain.WeatherForecast
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class CurrentWeatherUseCase @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository
) {
    suspend operator fun invoke(apiKey: String, lat: Double, long: Double, days: Int): Flow<Result<WeatherForecast>> =
        currentWeatherRepository.getWeatherData(apiKey, lat, long, days)
}