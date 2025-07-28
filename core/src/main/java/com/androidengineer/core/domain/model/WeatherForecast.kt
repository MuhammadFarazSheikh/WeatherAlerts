package com.androidengineer.core.domain.model

import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

data class WeatherForecast(
    val loaded: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val location: Location = Location(),
    val current: Current = Current(),
    val forecast: Forecast? = Forecast()
)

data class Location(
    val name: String = "",
    val region: String = "",
    val country: String = "",
    val localtime: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0
)

data class Current(
    val last_updated_epoch: Long = 0,
    val last_updated: String = "",
    val temp_c: Double = 0.0,
    val temp_f: Double = 0.0,
    val condition: Condition = Condition(),
    val wind_mph: Double = 0.0,
    val wind_kph: Double = 0.0,
    val wind_dir: String = "",
    val humidity: Int = -1,
    val feelslike_c: Double = 0.0,
    val feelslike_f: Double = 0.0,
) {
    val tempCentigrade: String get() = "${temp_c.roundToInt()}\u00B0C"
    val feelsLikeCentigrade: String get() = "${feelslike_c.roundToInt()}\u00B0C"
    val humidityPercent: String get() = "$humidity%"
    val windSpeedKMPH: String get() = "${wind_kph.roundToInt()} km/h"
}

@Serializable
data class Condition(
    val text: String = "", val icon: String = "", val code: Int = -1
) {
    val fullIconUrl: String get() = "https:$icon"
}

@Serializable
data class Forecast(
    val forecastday: List<Forecastday?> = emptyList()

)

@Serializable
data class Forecastday(
    val date: String? = "",
    val day: Day? = Day(),
    val astro: Astro? = Astro(),
    val hour: List<Hour>? = emptyList()
)

@Serializable
data class Day(
    val maxtemp_c: Double = 0.0,
    val maxtemp_f: Double = 0.0,
    val mintemp_c: Double = 0.0,
    val mintemp_f: Double = 0.0,
    val avgtemp_c: Double = 0.0,
    val avgtemp_f: Double = 0.0,
    val maxwind_mph: Double = 0.0,
    val maxwind_kph: Double = 0.0,
    val avghumidity: Int = -1,
    val daily_will_it_rain: Int = -1,
    val daily_chance_of_rain: Int = -1,
    val daily_will_it_snow: Int = -1,
    val daily_chance_of_snow: Int = -1,
    val condition: Condition = Condition(),
    val uv: Double = 0.0
) {
    val tempCentigrade: String get() = "${maxtemp_c.roundToInt()}\u00B0C"
    val humidityPercent: String get() = "$avghumidity%"
    val windSpeedKMPH: String get() = "${maxwind_kph.roundToInt()} km/h"
}

@Serializable
data class Astro(
    val sunrise: String = "",
    val sunset: String = "",
    val moonrise: String = "",
)

@Serializable
data class Hour(
    val time: String = "",
    val temp_c: Double = 0.0,
    val temp_f: Double = 0.0,
    val condition: Condition = Condition(),
    val wind_mph: Double = 0.0,
    val wind_kph: Double = 0.0,
    val wind_dir: String = "",
    val humidity: Int = -1,
    val feelslike_c: Double = 0.0,
    val feelslike_f: Double = 0.0,
    val will_it_rain: Int = -1,
    val chance_of_rain: Int = -1,
    val will_it_snow: Int = -1,
    val chance_of_snow: Int = -1,
    val uv: Double = 0.0
) {
    val tempCentigrade: String get() = "${temp_c.roundToInt()}\u00B0C"
    val feelsLikeCentigrade: String get() = "${feelslike_c.roundToInt()}\u00B0C"
    val humidityPercent: String get() = "$humidity%"
    val windSpeedKMPH: String get() = "${wind_kph.roundToInt()} km/h"
}