package de.klyk.ktorclient.domain.weather

import de.klyk.ktorclient.io.weather.WeatherService
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.receive
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import com.github.kittinunf.result.Result

class WeatherInteractorImpl(
    private val weatherService: WeatherService
) : WeatherInteractor {

    override suspend fun fetchWeatherFromLondon(): Result<Weather, WeatherException> {
        return try {
            weatherService.fetchWeatherFromLondon().run {
                if (status.value in 200..299) {
                    Result.success(receive())
                } else {
                    Result.error(
                        WeatherException(
                            exceptionType = status.value.toString(),
                            message = "Fehler beim Laden mit Statuscode ${status.value}",
                            isHttpStatusCode = true
                        )
                    )
                }
            }
        } catch (e: NoTransformationFoundException) {
            Result.error(WeatherException("NoTransformationFoundException", e.message.toString()))
        } catch (e: SocketTimeoutException) {
            Result.error(WeatherException("SocketTimeoutException", e.message.toString()))
        } catch (e: IOException) {
            Result.error(WeatherException("IOException", e.message.toString()))
        } catch (e: Exception) {
            Result.error(WeatherException("Exception", e.message.toString()))
        }
    }

    override fun fetchWeatherStream() = flow {
        // Fake data stream
        while (true) {
            delay(2000)
            // Send a random fake weather forecast data
            emit(
                Weather(
                    coord = Coordinate(10f, 20f),
                    base = "TestEcke",
                    main = Main(temp = (10..30).random().toFloat(), pressure = 1, humidity = 1, temp_min = 10f, temp_max = 30f),
                    wind = Wind(speed = (0..10).random().toFloat(), deg = 1)
                )
            )
        }
    }
}