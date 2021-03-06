package de.klyk.ktorclient

import android.app.Application
import de.klyk.ktorclient.core.di.networkModule
import de.klyk.ktorclient.di.commonModule
import de.klyk.ktorclient.di.domainModule
import de.klyk.ktorclient.di.networkServicesModule
import de.klyk.ktorclient.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //Start Timber
        Timber.plant(Timber.DebugTree())
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules(
                listOf(
                    viewModelModule,
                    commonModule,
                    networkModule,
                    networkServicesModule,
                    domainModule
                )
            )
        }
    }
}