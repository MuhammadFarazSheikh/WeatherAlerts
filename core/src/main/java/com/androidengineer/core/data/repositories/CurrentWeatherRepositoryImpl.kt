package com.androidengineer.core.data.repositories

import com.androidengineer.core.data.remote.RemoteDataSource
import com.androidengineer.core.data.remote.model.Response
import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.model.WeatherForecast
import com.androidengineer.core.domain.repositories.CurrentWeatherRepository
import com.androidengineer.core.data.mapper.toDomainModel
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