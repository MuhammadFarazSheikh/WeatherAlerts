package com.androidengineer.feature

import app.cash.turbine.test
import com.androidengineer.core.domain.model.Current
import com.androidengineer.core.domain.model.Forecast
import com.androidengineer.core.domain.model.Forecastday
import com.androidengineer.core.domain.model.Location
import com.androidengineer.core.domain.model.WeatherForecast
import com.androidengineer.core.domain.usecases.CurrentWeatherUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var useCase: CurrentWeatherUseCase
    private lateinit var viewModel: CurrentWeatherViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = mockk()
        viewModel = CurrentWeatherViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should update state with loading then success`() = runTest {
        val fakeWeather = WeatherForecast(
            loaded = true,
            isError = false,
            isLoading = false,
            location = Location(name = "Lisbon", country = "Portugal"),
            current = Current(temp_c = 25.0),
            forecast = Forecast(forecastday = listOf(Forecastday("2025-08-07")))
        )

        coEvery { useCase(any(), any(), any(), any()) } returns flowOf(
            com.androidengineer.core.domain.model.Result.Loading,
            com.androidengineer.core.domain.model.Result.Success(fakeWeather)
        )

        viewModel.currentWeatherDataState.test {
            viewModel.getCurrentWeatherData(10.0, 20.0)

            advanceUntilIdle()

            // Initial state
            assertEquals(WeatherForecast(), awaitItem())

            // Loading state
            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()
            assertThat(loadingState.loaded).isFalse()
            assertThat(loadingState.isError).isFalse()

            // Success state
            val successState = awaitItem()
            assertThat(successState.isLoading).isFalse()
            assertThat(successState.loaded).isTrue()
            assertThat(successState.isError).isFalse()
            assertThat(successState.location.name).isEqualTo("Lisbon")
            assertThat(successState.location.country).isEqualTo("Portugal")
            assertThat(successState.current.temp_c).isEqualTo(25.0)
            assertThat(successState.forecast?.forecastday?.size).isEqualTo(1)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should update state with loading then error`() = runTest {
        coEvery { useCase(any(), any(), any(), any()) } returns flowOf(
            com.androidengineer.core.domain.model.Result.Loading,
            com.androidengineer.core.domain.model.Result.Error("Network failure")
        )

        viewModel.currentWeatherDataState.test {
            viewModel.getCurrentWeatherData(10.0, 20.0)

            advanceUntilIdle()
            assertEquals(WeatherForecast(), awaitItem())
            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()
            assertThat(loadingState.loaded).isFalse()
            assertThat(loadingState.isError).isFalse()
            val errorState = awaitItem()
            assertThat(errorState.isLoading).isFalse()
            assertThat(errorState.loaded).isFalse()
            assertThat(errorState.isError).isTrue()

            cancelAndIgnoreRemainingEvents()
        }
    }
}