package com.androidengineer.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidengineer.core.CurrentWeatherUseCase
import com.androidengineer.core.Result
import com.androidengineer.core.domain.WeatherForecast
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

    init {

        viewModelScope.launch {
            currentWeatherUseCase(
                "5a5bf784f73546f48cb225751252706", 38.72, -9.14, 3
            ).collect { weather ->
                when (weather) {
                    is Result.Success -> {
                        _currentWeatherDataState.value = weather.data
                    }

                    is Result.Error -> {
                        println("error = "+weather.message)
                        _currentWeatherDataState.value = WeatherForecast()
                    }

                    is Result.Loading -> {
                        _currentWeatherDataState.value = WeatherForecast()
                    }
                }
            }
        }
    }

}