package com.rvcoding.propertypricemockup.data.repository

import com.rvcoding.propertypricemockup.core.domain.util.DataError
import com.rvcoding.propertypricemockup.core.domain.util.Result
import com.rvcoding.propertypricemockup.data.db.PropertyDao
import com.rvcoding.propertypricemockup.data.remote.PropertiesResponse
import com.rvcoding.propertypricemockup.data.remote.RatesResponse
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.data.remote.api.PropertyDataSource
import com.rvcoding.propertypricemockup.domain.data.repository.PropertyRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


internal class PropertyRepositoryImpl @Inject constructor(
    private val propertyDao: PropertyDao,
    private val propertyDataSource: PropertyDataSource
) : PropertyRepository {
    override val errors = Channel<DataError>()

    override fun listing(): Flow<List<Property>> = propertyDao.getProperties()
    override suspend fun refresh() {
        when (val response = propertyDataSource.fetchProperties()) {
            is Result.Error -> errors.send(response.error)
            is Result.Success -> {
                response.data.properties.forEach { property ->
                    propertyDao.insertProperty(property.toDomain(response.data.location()))
                }
            }
        }
    }

    override suspend fun refreshDetails(id: Long): Property? {
        when (val response = propertyDataSource.fetchProperties()) {
            is Result.Error -> errors.send(response.error)
            is Result.Success -> {
                response.data.properties.find { it.id == id }?.let { property ->
                    propertyDao.insertProperty(property.toDomain(response.data.location()))
                    return property.toDomain(response.data.location())
                }
            }
        }
        return null
    }

    override suspend fun rates(): RatesResponse {
        when (val response = propertyDataSource.fetchRates()) {
            is Result.Error -> errors.send(response.error)
            is Result.Success -> return response.data
        }
        return RatesResponse.FAILURE
    }
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
