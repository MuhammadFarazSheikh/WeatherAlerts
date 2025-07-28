package com.androidengineer.core.di

import com.androidengineer.core.data.remote.api.ApiService
import com.androidengineer.core.domain.repositories.CurrentWeatherRepository
import com.androidengineer.core.data.repositories.CurrentWeatherRepositoryImpl
import com.androidengineer.core.domain.usecases.CurrentWeatherUseCase
import com.androidengineer.core.data.remote.RemoteDataSource
import com.androidengineer.core.domain.repositories.SearchWeatherRepository
import com.androidengineer.core.data.repositories.SearchWeatherRepositoryImpl
import com.androidengineer.core.domain.usecases.SearchWeatherUseCase
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

    @Provides
    @Singleton
    fun getSearchWeatherRepository(remoteDataSource: RemoteDataSource): SearchWeatherRepository =
        SearchWeatherRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun getSearchWeatherUseCase(searchWeatherRepository: SearchWeatherRepository): SearchWeatherUseCase =
        SearchWeatherUseCase(searchWeatherRepository)


}