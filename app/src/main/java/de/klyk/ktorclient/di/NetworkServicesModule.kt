package de.klyk.ktorclient.di

import de.klyk.ktorclient.io.lokalserver.LokalServerService
import de.klyk.ktorclient.io.weather.WeatherService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkServicesModule = module {
    // HTTP Services
    single { WeatherService(client = get(named(DEFAULT_HTTP_CLIENT))) }
    single { LokalServerService(client = get(named(DEFAULT_HTTP_CLIENT))) }
}

/**
 * Namespace Flavors für HTTP-Clients.
 */
const val DEFAULT_HTTP_CLIENT = "default"
const val MOCK_HTTP_CLIENT = "mock"