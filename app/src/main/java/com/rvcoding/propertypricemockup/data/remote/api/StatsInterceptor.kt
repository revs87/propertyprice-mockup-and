package com.rvcoding.propertypricemockup.data.remote.api

import com.rvcoding.propertypricemockup.common.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StatsInterceptor @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val coScope: CoroutineScope
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val startTime = System.nanoTime()
        val response = chain.proceed(request)
        val endTime = System.nanoTime()

        coScope.launch(dispatchersProvider.io) {
            val duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime)
            val endpoint = request.url.toString()

            val url = URL(endpoint)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("action", if (endpoint.contains("properties.json")) "load-properties" else "load-rates")
            connection.setRequestProperty("duration", duration.toString())
            val responseCode = connection.responseCode
            println("Stats: code=$responseCode endpoint=${endpoint.substringAfterLast("/")} duration=$duration")
            connection.disconnect()
        }

        return response
    }
}