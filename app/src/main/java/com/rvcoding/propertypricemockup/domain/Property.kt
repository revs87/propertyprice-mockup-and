package com.rvcoding.propertypricemockup.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.RoundingMode
import java.text.DecimalFormat

@Entity(tableName = "properties")
data class Property(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val name: String,
    val isFeatured: Boolean,
    val rating: Double,
    val lowestPricePerNight: Double,
    val lowestPricePerNightCurrency: String,
)

fun Property.ratingFormatted(): String {
    val df = DecimalFormat("#.0")
    df.roundingMode = RoundingMode.HALF_UP
    return df.format(this.rating / 10)
}