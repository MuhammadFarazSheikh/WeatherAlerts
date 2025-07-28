package com.androidengineer.core.data.remote.model

data class WeatherForecast(
    val location: Location, val current: Current, val forecast: Forecast
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String,
    val lat: Double,
    val lon: Double
)

data class Current(
    val last_updated_epoch: Long,
    val last_updated: String,
    val temp_c: Double,
    val temp_f: Double,
    val condition: Condition,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_dir: String,
    val humidity: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
)

data class Condition(
    val text: String, val icon: String, val code: Int
)

data class Forecast(
    val forecastday: List<Forecastday>
)

data class Forecastday(
    val date: String, val day: Day, val astro: Astro, val hour: List<Hour>
)

data class Day(
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val maxwind_mph: Double,
    val maxwind_kph: Double,
    val avghumidity: Int,
    val daily_will_it_rain: Int,
    val daily_chance_of_rain: Int,
    val daily_will_it_snow: Int,
    val daily_chance_of_snow: Int,
    val condition: Condition,
    val uv: Double
)

data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
)

data class Hour(
    val time: String,
    val temp_c: Double,
    val temp_f: Double,
    val condition: Condition,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_dir: String,
    val humidity: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val will_it_rain: Int,
    val chance_of_rain: Int,
    val will_it_snow: Int,
    val chance_of_snow: Int,
    val uv: Double
)