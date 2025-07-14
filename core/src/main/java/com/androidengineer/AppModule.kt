package com.androidengineer

import com.androidengineer.core.ApiService
import com.androidengineer.core.CurrentWeatherRepository
import com.androidengineer.core.CurrentWeatherRepositoryImpl
import com.androidengineer.core.CurrentWeatherUseCase
import com.androidengineer.core.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getRemoteDataSource(apiService: ApiService): RemoteDataSource = RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun getCurrentWeatherRepository(remoteDataSource: RemoteDataSource): CurrentWeatherRepository =
        CurrentWeatherRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun getCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository): CurrentWeatherUseCase =
        CurrentWeatherUseCase(currentWeatherRepository)

}