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
 *
 * In Clean Architecture there would be a model in each layer (i.e. Domain, Data, UI and possibly UseCases),
 * but in this case I'm using just one model for all layers.
 *
 * I'm not saying any of the 2 scenarios is how it should fundamentally be,
 * but as per:
 * - the lack of requirements or guidelines during development and
 * - the actual model information does not mutate much from layer to layer
 * then this is perfectly acceptable.
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

    fun isInvalid() = this.id < 0L

    companion object {
        /**
         * Choosing a INITIAL value instead of null - The intent is to avoid introducing nullability.
         * Releases the need to handle potential null values in UI or business logic, increasing complexity and the risk of errors.
         *
         * Nevertheless, this is still an invalid state which we should not consider.
         * The id as -1 will serve as the identifier for an invalid state.
         *
         * Also used for composable previews.
         * */
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