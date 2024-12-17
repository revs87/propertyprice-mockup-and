package com.rvcoding.propertypricemockup.data.remote

import kotlinx.serialization.Serializable


/**
 * Json to Kotlin converter used
 * */
@Serializable
data class RatesResponse(
    val success: Boolean,
    val timestamp: Long? = null,
    val historical: Boolean? = null,
    val base: String,
    val date: String? = null,
    val rates: LiveRates,
) {
    companion object {
        val STUB = RatesResponse(
            success = true,
            base = "EUR",
            rates = LiveRates(
                EUR = 1.0,
                GBP = 0.9,
                USD = 1.1
            )
        )
    }
}

@Serializable
data class LiveRates(
    val EUR: Double = 0.0,
    val GBP: Double = 0.0,
    val USD: Double = 0.0,
)
