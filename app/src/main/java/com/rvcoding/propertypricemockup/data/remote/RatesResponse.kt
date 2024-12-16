package com.rvcoding.propertypricemockup.data.remote

import kotlinx.serialization.Serializable


/**
 * Json to Kotlin converter used
 * */
@Serializable
data class RatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val historical: Boolean,
    val base: String,
    val date: String,
    val rates: LiveRates,
) {
    companion object {
        val FAILURE = RatesResponse(
            success = false,
            timestamp = 0,
            historical = false,
            base = "",
            date = "",
            rates = LiveRates()
        )
    }
}

@Serializable
data class LiveRates(
    val EUR: Double = 0.0,
    val GBP: Double = 0.0,
    val USD: Double = 0.0,
)
