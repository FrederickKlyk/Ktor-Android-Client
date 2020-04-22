package de.klyk.ktorclient.domain.weather

import com.github.kittinunf.result.Result
import kotlinx.coroutines.flow.Flow

interface WeatherInteractor {
    suspend fun fetchWeatherFromLondon(): Result<Weather, WeatherException>
    fun fetchWeatherStream() : Flow<Weather>
}