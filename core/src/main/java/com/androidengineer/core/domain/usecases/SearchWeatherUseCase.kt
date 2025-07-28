package com.androidengineer.core.domain.usecases

import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.model.WeatherForecast
import com.androidengineer.core.domain.repositories.SearchWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchWeatherUseCase @Inject constructor(
    private val searchWeatherRepository: SearchWeatherRepository
){
    suspend fun invoke(apiKey: String, location: String): Flow<Result<WeatherForecast>> =
        searchWeatherRepository.searchWeatherData(apiKey, location)
}