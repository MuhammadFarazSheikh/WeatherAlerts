package com.androidengineer.core

import com.androidengineer.core.domain.WeatherForecast
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val remoeDataSource: RemoteDataSource
) : CurrentWeatherRepository {
    override suspend fun getWeatherData(apiKey: String, lat: Double, long: Double, days: Int): Flow<Result<WeatherForecast>> =
        flow<Result<WeatherForecast>> {
            emit(Result.Loading)
            val result = remoeDataSource.getWeatherData(apiKey, lat, long, days)
            when (result) {
                is Response.Success -> emit(Result.Success(result.data.toDomainModel()))
                is Response.Error -> emit(Result.Error(result.message))
                else -> {}
            }
        }
}