package com.androidengineer.core.data.repositories

import com.androidengineer.core.data.remote.RemoteDataSource
import com.androidengineer.core.data.remote.model.Response
import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.model.WeatherForecast
import com.androidengineer.core.domain.repositories.SearchWeatherRepository
import com.androidengineer.core.data.mapper.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchWeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : SearchWeatherRepository {
    override suspend fun searchWeatherData(
        apiKey: String, location: String
    ): Flow<Result<WeatherForecast>> = flow<Result<WeatherForecast>> {
        emit(Result.Loading)
        val result = remoteDataSource.getSearchWeatherData(apiKey, location)
        when (result) {
            is Response.Success -> emit(Result.Success(result.data.toDomainModel()))
            is Response.Error -> emit(Result.Error(result.message))
            else -> {}
        }
    }
}