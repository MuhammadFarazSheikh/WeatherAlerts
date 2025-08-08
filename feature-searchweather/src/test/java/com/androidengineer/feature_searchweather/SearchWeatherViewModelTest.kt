package com.androidengineer.feature_searchweather

import app.cash.turbine.test
import com.androidengineer.core.domain.model.Current
import com.androidengineer.core.domain.model.Forecast
import com.androidengineer.core.domain.model.Forecastday
import com.androidengineer.core.domain.model.Location
import com.androidengineer.core.domain.model.WeatherForecast
import com.androidengineer.core.domain.usecases.SearchWeatherUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchWeatherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var useCase: SearchWeatherUseCase
    private lateinit var viewModel: SearchWeatherViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = mockk()
        viewModel = SearchWeatherViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchWeather emits loading then success`() = runTest {
        val fakeWeather = WeatherForecast(
            loaded = true,
            isError = false,
            isLoading = false,
            location = Location(name = "Lisbon", country = "Portugal"),
            current = Current(temp_c = 25.0),
            forecast = Forecast(forecastday = listOf(Forecastday("2025-08-07")))
        )

        coEvery { useCase.invoke(any(), any()) } returns flowOf(
            com.androidengineer.core.domain.model.Result.Loading,
            com.androidengineer.core.domain.model.Result.Success(fakeWeather)
        )

        viewModel.searchState.test {
            viewModel.searchWeather("Lisbon")

            advanceUntilIdle()

            assertThat(awaitItem()).isEqualTo(WeatherForecast())

            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()
            assertThat(loadingState.loaded).isFalse()
            assertThat(loadingState.isError).isFalse()

            val successState = awaitItem()
            assertThat(successState.isLoading).isFalse()
            assertThat(successState.loaded).isTrue()
            assertThat(successState.isError).isFalse()
            assertThat(successState.location.name).isEqualTo("Lisbon")
            assertThat(successState.current.temp_c).isEqualTo(25.0)
            assertThat(successState.forecast?.forecastday?.size).isEqualTo(1)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `searchWeather emits loading then error`() = runTest {
        coEvery { useCase.invoke(any(), any()) } returns flowOf(
            com.androidengineer.core.domain.model.Result.Loading,
            com.androidengineer.core.domain.model.Result.Error("Network error")
        )

        viewModel.searchState.test {
            viewModel.searchWeather("Lisbon")

            advanceUntilIdle()

            assertThat(awaitItem()).isEqualTo(WeatherForecast())

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
