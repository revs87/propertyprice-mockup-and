package com.rvcoding.propertypricemockup.data.repository

import com.rvcoding.propertypricemockup.data.db.PropertyDao
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
        propertyApi.fetchProperties().properties.forEach { property ->
            propertyDao.insertProperty(property.toDomain())
        }
    }
}

typealias PropertyFromApi = com.rvcoding.propertypricemockup.data.remote.Property

private fun PropertyFromApi.toDomain(): Property = Property(
    id = id,
    name = name,
    isFeatured = isFeatured,
    rating = overallRating.overall.toDouble(),
    lowestPricePerNight = lowestPricePerNight.value.toDouble(),
    lowestPricePerNightCurrency = lowestPricePerNight.currency,
)
