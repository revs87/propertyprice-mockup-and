package com.rvcoding.propertypricemockup.data.remote.api

import com.rvcoding.propertypricemockup.data.remote.PropertiesResponse
import com.rvcoding.propertypricemockup.data.remote.RatesResponse
import com.rvcoding.propertypricemockup.domain.data.remote.api.PropertyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PropertyService @Inject constructor(
    private val statsInterceptor: StatsInterceptor
) : PropertyApi {
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

    override suspend fun fetchProperties(): PropertiesResponse = service.fetchProperties()
    override suspend fun fetchRates(): RatesResponse = service.fetchRates()

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/"
    }
}
