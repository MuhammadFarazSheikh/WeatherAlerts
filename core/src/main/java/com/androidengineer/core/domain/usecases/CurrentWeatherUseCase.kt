package com.androidengineer.core.domain.usecases

import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.model.WeatherForecast
import com.androidengineer.core.domain.repositories.CurrentWeatherRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class CurrentWeatherUseCase @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository
) {
    suspend operator fun invoke(apiKey: String, lat: Double, long: Double, days: Int): Flow<Result<WeatherForecast>> =
        currentWeatherRepository.getWeatherData(apiKey, lat, long, days)
}