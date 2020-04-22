package de.klyk.ktorclient.di

import de.klyk.ktorclient.ui.main.MainActivityViewModel
import de.klyk.ktorclient.ui.main.lokalserver.LokalServerFragmentViewModel
import de.klyk.ktorclient.ui.main.weather.WeatherFragmentViewModel
import de.klyk.ktorclient.ui.main.websocket.WebsocketsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { WeatherFragmentViewModel(weatherInteractor = get()) }
    viewModel { LokalServerFragmentViewModel(lokalServerInteractor = get()) }
    viewModel { WebsocketsFragmentViewModel(websocketClient = get(named(DEFAULT_HTTP_CLIENT))) }
}