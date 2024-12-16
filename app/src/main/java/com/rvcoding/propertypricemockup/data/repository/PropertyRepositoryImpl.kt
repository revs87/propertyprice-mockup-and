package com.rvcoding.propertypricemockup.data.repository

import com.rvcoding.propertypricemockup.data.db.PropertyDao
import com.rvcoding.propertypricemockup.data.remote.PropertiesResponse
import com.rvcoding.propertypricemockup.data.remote.RatesResponse
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.data.remote.api.PropertyApi
import com.rvcoding.propertypricemockup.domain.data.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


internal class PropertyRepositoryImpl @Inject constructor(
    private val propertyDao: PropertyDao,
    private val propertyApi: PropertyApi
) : PropertyRepository {
    override fun listing(): Flow<List<Property>> = propertyDao.getProperties()
    override suspend fun refresh() {
        val response: PropertiesResponse = propertyApi.fetchProperties()
        response.properties.forEach { property ->
            propertyDao.insertProperty(property.toDomain(response.location()))
        }
    }

    override suspend fun refreshDetails(id: Long): Property? {
        val response: PropertiesResponse = propertyApi.fetchProperties()
        response.properties.find { it.id == id }?.let { property ->
            propertyDao.insertProperty(property.toDomain(response.location()))
            return property.toDomain(response.location())
        }
        return null
    }

    override suspend fun rates(): RatesResponse = propertyApi.fetchRates()
}

private fun PropertiesResponse.location() = "${locationEn.city.name}, ${locationEn.city.country}"

typealias PropertyFromApi = com.rvcoding.propertypricemockup.data.remote.Property
private fun PropertyFromApi.toDomain(location: String): Property = Property(
    id = id,
    name = name,
    location = location,
    overview = overview,
    imgUrl = imagesGallery.firstOrNull()?.let { "https://${it.prefix}${it.suffix}" } ?: "",
    isFeatured = isFeatured,
    rating = overallRating.overall.toDouble(),
    lowestPricePerNight = lowestPricePerNight.value.toDouble(),
    lowestPricePerNightCurrency = lowestPricePerNight.currency,
)
