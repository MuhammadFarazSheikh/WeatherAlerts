package com.androidengineer.feature_searchweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidengineer.core.domain.model.Result
import com.androidengineer.core.domain.usecases.SearchWeatherUseCase
import com.androidengineer.core.domain.model.WeatherForecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWeatherViewModel @Inject constructor(
    private val searchWeatherUseCase: SearchWeatherUseCase
) : ViewModel() {

    private val _searchMutableState = MutableStateFlow(WeatherForecast())
    val searchState: StateFlow<WeatherForecast> = _searchMutableState

    fun searchWeather(searchQuery: String) {
        viewModelScope.launch {
            searchWeatherUseCase.invoke("5a5bf784f73546f48cb225751252706", searchQuery)
                .collect { weather ->
                    when (weather) {
                        is Result.Success -> {
                            _searchMutableState.value =
                                _searchMutableState.value.copy(isLoading = false)
                            println("Success = " + weather.data)
                            _searchMutableState.value = weather.data
                            _searchMutableState.value =
                                _searchMutableState.value.copy(loaded = true)
                        }

                        is Result.Error -> {
                            _searchMutableState.value =
                                _searchMutableState.value.copy(loaded = false)
                            _searchMutableState.value =
                                _searchMutableState.value.copy(isLoading = false)
                            println("Error = " + weather.message)
                            _searchMutableState.value =
                                _searchMutableState.value.copy(isError = true)
                        }

                        is Result.Loading -> {
                            _searchMutableState.value =
                                _searchMutableState.value.copy(isLoading = true)
                            println("Loading")
                        }
                    }
                }
        }
    }

}