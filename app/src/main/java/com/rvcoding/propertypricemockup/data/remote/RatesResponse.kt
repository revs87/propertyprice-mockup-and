package com.rvcoding.propertypricemockup.data.remote

import kotlinx.serialization.SerialName


/**
 * Json to Kotlin converter used
 * */
data class RatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val historical: Boolean,
    val base: String,
    val date: String,
    val rates: LiveRates,
)

data class LiveRates(
    val EUR: Double = 0.0,
    val GBP: Double = 0.0,
    val USD: Double = 0.0,
)
