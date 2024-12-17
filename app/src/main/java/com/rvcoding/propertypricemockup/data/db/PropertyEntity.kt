package com.rvcoding.propertypricemockup.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * This is a copy&Paste version of domain model Property, without the Entity annotation.
 *
 * With this we grant Separation of Concerns from data to domain - Ideal from a Clean Architecture perspective.
 * */
@Entity(tableName = "properties")
data class PropertyEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val name: String,
    val location: String,
    val overview: String,
    val imgUrl: String,
    val isFeatured: Boolean,
    val rating: Double,
    val lowestPricePerNight: Double,
    val lowestPricePerNightCurrency: String,
)