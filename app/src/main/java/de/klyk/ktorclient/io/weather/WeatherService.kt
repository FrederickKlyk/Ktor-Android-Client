package de.klyk.ktorclient.io.weather

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement

class WeatherService(val client: HttpClient) {

    suspend fun fetchWeatherFromLondon() =
        client.get<HttpStatement>("$WEATHER_BASE_URL/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").execute()


    companion object{
        private const val WEATHER_BASE_URL = "https://samples.openweathermap.org/data/2.5"
    }
}