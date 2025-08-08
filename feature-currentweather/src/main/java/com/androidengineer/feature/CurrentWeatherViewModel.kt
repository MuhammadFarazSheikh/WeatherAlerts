package com.androidengineer.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidengineer.core.domain.usecases.CurrentWeatherUseCase
import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.model.WeatherForecast
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase
) : ViewModel() {

    private val _currentWeatherDataState = MutableStateFlow(WeatherForecast())
    val currentWeatherDataState: StateFlow<WeatherForecast> = _currentWeatherDataState

    fun getCurrentWeatherData(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            currentWeatherUseCase(
                "5a5bf784f73546f48cb225751252706", latitude, longitude, 3
            ).collect { weather ->
                when (weather) {
                    is Result.Success -> {
                        _currentWeatherDataState.value = weather.data
                        _currentWeatherDataState.value =
                            _currentWeatherDataState.value.copy(
                                isLoading = false
                            )
                    }

                    is Result.Error -> {
                        _currentWeatherDataState.value =
                            _currentWeatherDataState.value.copy(
                                isLoading = false,
                                isError = true
                            )
                        println("error = " + weather.message)
                    }

                    is Result.Loading -> {
                        _currentWeatherDataState.value =
                            _currentWeatherDataState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

}