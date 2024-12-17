package com.rvcoding.propertypricemockup.domain


/**
 * Pick only whats needed for the app.
 * */
data class Rates(
    val success: Boolean,
    val base: String,
    val rates: Top3Rates
) {
    companion object {
        val FAILURE = Rates(
            success = false,
            base = "EUR",
            rates = Top3Rates()
        )
    }
}

data class Top3Rates(
    val eur: Double = 0.0,
    val gbp: Double = 0.0,
    val usd: Double = 0.0
)