package com.rvcoding.propertypricemockup.data.remote.api

import androidx.annotation.VisibleForTesting
import com.rvcoding.propertypricemockup.core.domain.util.DataError
import com.rvcoding.propertypricemockup.core.domain.util.Result
import com.rvcoding.propertypricemockup.core.domain.util.networkResult
import com.rvcoding.propertypricemockup.data.remote.PropertiesResponse
import com.rvcoding.propertypricemockup.data.remote.RatesResponse
import com.rvcoding.propertypricemockup.domain.data.remote.api.PropertyDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PropertyService @Inject constructor(
    private val statsInterceptor: StatsInterceptor
) : PropertyDataSource {
    private val service: PropertyApi

    init {
        service = createApi(statsInterceptor)
    }

    override suspend fun fetchProperties(): Result<PropertiesResponse, DataError.Network> = networkResult { service.fetchProperties() }
    override suspend fun fetchRates(): Result<RatesResponse, DataError.Network> = networkResult { service.fetchRates() }

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/"

        private fun createApi(statsInterceptor: StatsInterceptor): PropertyApi {
            val service: PropertyApi
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
            return service
        }

        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        fun createApiForTesting() : PropertyDataSource = object : PropertyDataSource {
            override suspend fun fetchProperties(): Result<PropertiesResponse, DataError.Network> = networkResult { PropertiesResponse.STUB }
            override suspend fun fetchRates(): Result<RatesResponse, DataError.Network> = networkResult { RatesResponse.STUB }
        }
    }
}
