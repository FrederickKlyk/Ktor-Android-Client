package de.klyk.ktorclient.di

import de.klyk.ktorclient.domain.lokalserver.LokalServerInteractor
import de.klyk.ktorclient.domain.lokalserver.LokalServerInteractorImpl
import de.klyk.ktorclient.domain.weather.WeatherInteractor
import de.klyk.ktorclient.domain.weather.WeatherInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    single<WeatherInteractor> { WeatherInteractorImpl(weatherService = get()) }
    single<LokalServerInteractor> { LokalServerInteractorImpl(lokalServerService = get())}
}