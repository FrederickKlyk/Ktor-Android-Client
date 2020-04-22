package de.klyk.ktorclient.core.common

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


class HttpLoggingSensitiveInterceptor(
    private val httpLoggingInterceptor: HttpLoggingInterceptor
) : Interceptor {

    private val geheimeURLList = listOf("geheim", "login", "register")

    override fun intercept(chain: Interceptor.Chain): Response = when {
        //Wenn geheime URL zutreffend, dann nicht loggen
        geheimeURLList.contains(chain.request().url.toString()) -> {
            Timber.d("es wird nicht über den HTTP Logging geloggt.")
            chain.proceed(chain.request())
        }
        else -> {
            Timber.d("URL ist nicht sensitiv, es wird geloggt")
            httpLoggingInterceptor.intercept(chain)
        }
    }
}