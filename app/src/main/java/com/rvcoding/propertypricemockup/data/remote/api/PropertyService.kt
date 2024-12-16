package com.rvcoding.propertypricemockup.data.remote.api

import com.rvcoding.propertypricemockup.core.domain.util.DataError
import com.rvcoding.propertypricemockup.core.domain.util.Result
import com.rvcoding.propertypricemockup.core.domain.util.Result.Error
import com.rvcoding.propertypricemockup.core.domain.util.Result.Success
import com.rvcoding.propertypricemockup.data.remote.PropertiesResponse
import com.rvcoding.propertypricemockup.data.remote.RatesResponse
import com.rvcoding.propertypricemockup.domain.data.remote.api.PropertyDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class PropertyService @Inject constructor(
    private val statsInterceptor: StatsInterceptor
) : PropertyDataSource {
    private val service: PropertyApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(statsInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PropertyApi::class.java)
    }

    override suspend fun fetchProperties(): Result<PropertiesResponse, DataError.Network> = networkCall { service.fetchProperties() }
    override suspend fun fetchRates(): Result<RatesResponse, DataError.Network> = networkCall { service.fetchRates() }

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/"
    }
}

inline fun <T> networkCall(
    block: () -> T
): Result<T, DataError.Network> =
    try {
        Success(block.invoke())
    } catch (_: IOException) {
        Error(DataError.Network.NO_INTERNET)
    } catch (_: SocketTimeoutException) {
        Error(DataError.Network.REQUEST_TIMEOUT)
    } catch (_: Exception) {
        Error(DataError.Network.UNKNOWN)
    }
