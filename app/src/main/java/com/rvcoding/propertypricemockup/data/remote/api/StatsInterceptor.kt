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
            val endpoint = request.url.toString()
            val duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime)
            val action = if (endpoint.contains("properties.json")) "load-properties" else "load-rates"

            val url = URL("https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/6bed011203c6c8217f0d55f74ddcc5c5/raw/ce8f55cfd963aeef70f2ac9f88f34cefd19fca30/stats?action=$action&duration=$duration")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val responseCode = connection.responseCode
            println("Stats: code=$responseCode duration=${duration}ms action=$action endpoint=${endpoint.substringAfterLast("/")} ")
            connection.disconnect()
        }

        return response
    }
}