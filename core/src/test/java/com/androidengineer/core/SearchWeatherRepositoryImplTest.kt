package com.androidengineer.core

import com.androidengineer.core.data.mapper.toDomainModel
import com.androidengineer.core.data.remote.RemoteDataSource
import com.androidengineer.core.data.remote.model.Condition
import com.androidengineer.core.data.remote.model.Current
import com.androidengineer.core.data.remote.model.Forecast
import com.androidengineer.core.data.remote.model.Location
import com.androidengineer.core.data.remote.model.Response
import com.androidengineer.core.data.repositories.SearchWeatherRepositoryImpl
import com.androidengineer.core.data.remote.model.WeatherForecast
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchWeatherRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: SearchWeatherRepositoryImpl

    @Before
    fun setup() {
        remoteDataSource = mockk()
        repository = SearchWeatherRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `searchWeatherData emits loading then success`() = runTest {
        val fakeApiKey = "api_key"
        val fakeLocation = "Lisbon"
        val weatherResponse = WeatherForecast(
            location = Location(
                name = "Lisbon",
                country = "Portugal",
                lat = 38.72,
                lon = -9.13,
                localtime = "2024-10-01 10:00",
                region = "Region"
            ),
            current = Current(
                last_updated_epoch = 0,
                last_updated = "2024-10-01 10:00",
                temp_c = 10.0,
                temp_f = 0.0,
                condition = Condition(
                    text = "Sunny",
                    icon = "icon_url",
                    code = 1000
                ),
                wind_mph = 0.0,
                wind_kph = 0.0,
                wind_dir = "N",
                humidity = 0,
                feelslike_c = 0.0,
                feelslike_f = 0.0
            ),
            forecast = Forecast(forecastday = listOf())
        )

        coEvery { remoteDataSource.getSearchWeatherData(fakeApiKey, fakeLocation) } returns
                Response.Success(weatherResponse)

        val emissions = repository.searchWeatherData(fakeApiKey, fakeLocation).toList()

        assertThat(emissions[0]).isInstanceOf(com.androidengineer.core.domain.model.Result.Loading::class.java)

        val successResult = emissions[1] as com.androidengineer.core.domain.model.Result.Success
        assertThat(successResult.data).isEqualTo(weatherResponse.toDomainModel())
    }

    @Test
    fun `searchWeatherData emits loading then error`() = runTest {
        val fakeApiKey = "api_key"
        val fakeLocation = "Lisbon"
        val errorMessage = "Network error"

        coEvery { remoteDataSource.getSearchWeatherData(fakeApiKey, fakeLocation) } returns
                Response.Error(errorMessage)

        val emissions = repository.searchWeatherData(fakeApiKey, fakeLocation).toList()

        assertThat(emissions[0]).isInstanceOf(com.androidengineer.core.domain.model.Result.Loading::class.java)

        val errorResult = emissions[1] as com.androidengineer.core.domain.model.Result.Error
        assertThat(errorResult.message).isEqualTo(errorMessage)
    }
}
