package com.rvcoding.propertypricemockup.domain

import androidx.annotation.VisibleForTesting
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
    val location: String,
    val overview: String,
    val imgUrl: String,
    val isFeatured: Boolean,
    val rating: Double,
    val lowestPricePerNight: Double,
    val lowestPricePerNightCurrency: String,
) {
    companion object {
        val INITIAL = Property(
            id = -1,
            name = "Initial",
            location = "Porto",
            overview = "A Hostel in Porto",
            imgUrl = "https://res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto//v1/propertyimages/1/100/36.jpg",
            isFeatured = false,
            rating = 0.0,
            lowestPricePerNight = 1.0,
            lowestPricePerNightCurrency = "EUR"
        )
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun Property.ratingFormatted(): String {
    val df = DecimalFormat("0.0")
    df.roundingMode = RoundingMode.HALF_UP
    return df.format(this.rating.coerceAtLeast(0.0).coerceAtMost(100.0) / 10)
}