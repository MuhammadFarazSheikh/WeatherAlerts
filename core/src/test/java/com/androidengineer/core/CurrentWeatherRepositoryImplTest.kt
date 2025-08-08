package com.androidengineer.core

import com.androidengineer.core.data.mapper.toDomainModel
import com.androidengineer.core.data.remote.RemoteDataSource
import com.androidengineer.core.data.remote.model.Condition
import com.androidengineer.core.data.remote.model.Current
import com.androidengineer.core.data.remote.model.Forecast
import com.androidengineer.core.data.remote.model.Location
import com.androidengineer.core.data.remote.model.Response
import com.androidengineer.core.data.remote.model.WeatherForecast
import com.androidengineer.core.data.repositories.CurrentWeatherRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class CurrentWeatherRepositoryImplTest {

    private val remoteDataSource = mockk<RemoteDataSource>()
    private lateinit var repository: CurrentWeatherRepositoryImpl

    @Before
    fun setup() {
        repository = CurrentWeatherRepositoryImpl(remoteDataSource)
    }

    @Test
    fun getWeatherData_emits_Loading_and_Success_when_response_is_successful() = runTest {
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

        val expected = weatherResponse.toDomainModel()

        coEvery {
            remoteDataSource.getWeatherData(any(), any(), any(), any())
        } returns Response.Success(weatherResponse)

        // When
        val emissions = repository.getWeatherData("key", 38.72, -9.13, 3).toList()

        // Then
        assertThat(emissions[0]).isInstanceOf(com.androidengineer.core.domain.model.Result.Loading::class.java)
        assertThat(emissions[1]).isEqualTo(com.androidengineer.core.domain.model.Result.Success(expected))
    }

    @Test
    fun getWeatherData_emits_Loading_and_Error_when_response_is_error() = runTest {
        coEvery {
            remoteDataSource.getWeatherData(any(), any(), any(), any())
        } returns Response.Error("Something went wrong")

        val emissions = repository.getWeatherData("key", 1.0, 1.0, 3).toList()

        assertThat(emissions[0]).isInstanceOf(com.androidengineer.core.domain.model.Result.Loading::class.java)
        assertThat(emissions[1]).isEqualTo(com.androidengineer.core.domain.model.Result.Error("Something went wrong"))
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}