package com.rvcoding.propertypricemockup.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.RoundingMode
import java.text.DecimalFormat


/**
 * Property serves as the model for all layers of the app: Domain, Data and UI.
 * Not ideal if we want to digest the data from raw data into a more UI friendly model,
 * but this approach's gain is a reasonable trade-off for a mockup app.
 * */
@Entity(tableName = "properties")
data class Property(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val name: String,
    val isFeatured: Boolean,
    val rating: Double,
    val lowestPricePerNight: Double,
    val lowestPricePerNightCurrency: String,
) {
    companion object {
        val INITIAL = Property(
            id = -1,
            name = "Initial",
            isFeatured = false,
            rating = 0.0,
            lowestPricePerNight = 1.0,
            lowestPricePerNightCurrency = "EUR"
        )
    }
}

/**
 * Examples:
 * 1.01234 -> "1.0"
 * 1.05000 -> "1.1"
 * 1.07500 -> "1.1"
 * 1.09999 -> "1.1"
 * */
fun Property.ratingFormatted(): String {
    val df = DecimalFormat("#.0")
    df.roundingMode = RoundingMode.HALF_UP
    return df.format(this.rating / 10)
}