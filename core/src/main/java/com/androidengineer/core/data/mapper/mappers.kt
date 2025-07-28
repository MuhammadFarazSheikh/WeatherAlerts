package com.androidengineer.core.data.mapper

import com.androidengineer.core.data.remote.model.WeatherForecast
import com.androidengineer.core.data.remote.model.Day
import com.androidengineer.core.data.remote.model.Hour
import com.androidengineer.core.data.remote.model.Condition
import com.androidengineer.core.data.remote.model.Current
import com.androidengineer.core.data.remote.model.Astro
import com.androidengineer.core.data.remote.model.Forecast
import com.androidengineer.core.data.remote.model.Forecastday
import com.androidengineer.core.data.remote.model.Location
import com.androidengineer.core.domain.model.WeatherForecast as DomainModelWeatherForecast
import com.androidengineer.core.domain.model.Day as DomainModelDay
import com.androidengineer.core.domain.model.Hour as DomainModelHour
import com.androidengineer.core.domain.model.Condition as DomainModelCondition
import com.androidengineer.core.domain.model.Current as DomainModelCurrent
import com.androidengineer.core.domain.model.Astro as DomainModelAstro
import com.androidengineer.core.domain.model.Forecast as DomainModelForecast
import com.androidengineer.core.domain.model.Forecastday as DomainModelForecastday
import com.androidengineer.core.domain.model.Location as DomainModelLocation

fun WeatherForecast.toDomainModel() = DomainModelWeatherForecast(
    location = this.location.toDomainModel(), current = this.current.toDomainModel(), forecast = this.forecast?.toDomainModel()
)

fun Day.toDomainModel() = DomainModelDay(
    maxtemp_c = this.maxtemp_c,
    maxtemp_f = this.maxtemp_f,
    mintemp_c = this.mintemp_c,
    mintemp_f = this.mintemp_f,
    avgtemp_c = this.avgtemp_c,
    avgtemp_f = this.avgtemp_f,
    maxwind_mph = this.maxwind_mph,
    maxwind_kph = this.maxwind_kph,
    avghumidity = this.avghumidity,
    daily_will_it_rain = this.daily_will_it_rain,
    daily_will_it_snow = this.daily_will_it_snow,
    daily_chance_of_snow = this.daily_chance_of_snow,
    daily_chance_of_rain = this.daily_chance_of_rain,
    condition = this.condition.toDomainModel(),
    uv = this.uv
)

fun Condition.toDomainModel() = DomainModelCondition(
    text = this.text, icon = this.icon, code = this.code
)

fun Hour.toDomainModel() = DomainModelHour(
    time = this.time,
    temp_c = this.temp_c,
    temp_f = this.temp_f,
    condition = this.condition.toDomainModel(),
    wind_mph = this.wind_mph,
    wind_kph = this.wind_kph,
    wind_dir = this.wind_dir,
    humidity = this.humidity,
    will_it_rain = this.will_it_rain,
    chance_of_rain = this.chance_of_rain,
    will_it_snow = this.will_it_snow,
    chance_of_snow = this.chance_of_snow,
    feelslike_c = this.feelslike_c,
    feelslike_f = this.feelslike_f,
    uv = this.uv
)

fun Current.toDomainModel() = DomainModelCurrent(
    last_updated_epoch,
    last_updated = this.last_updated,
    temp_c = this.temp_c,
    temp_f = this.temp_f,
    condition = condition.toDomainModel(),
    wind_mph = this.wind_mph,
    wind_kph = this.wind_kph,
    wind_dir = this.wind_dir,
    humidity = this.humidity,
    feelslike_c = this.feelslike_c,
    feelslike_f = this.feelslike_f,
)

fun Astro.toDomainModel() = DomainModelAstro(
    sunrise = this.sunrise, sunset = this.sunset, moonrise = this.moonrise
)

fun Forecast.toDomainModel() = DomainModelForecast(
    forecastday = this.forecastday.map { it?.toDomainModel() }
)

fun Forecastday.toDomainModel() = DomainModelForecastday(
    date = this.date,
    day = this.day.toDomainModel(),
    astro = this.astro.toDomainModel(),
    hour = this.hour.map { it.toDomainModel() }
)

fun Location.toDomainModel() = DomainModelLocation(
    name = this.name,
    region = this.region,
    country = this.country,
    lat = this.lat,
    lon = this.lon,
    localtime = this.localtime
)