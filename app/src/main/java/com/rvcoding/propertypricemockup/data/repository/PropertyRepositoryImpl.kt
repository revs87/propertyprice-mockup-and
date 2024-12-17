package com.rvcoding.propertypricemockup.data.repository

import com.rvcoding.propertypricemockup.core.domain.util.DataError
import com.rvcoding.propertypricemockup.core.domain.util.Result.Error
import com.rvcoding.propertypricemockup.core.domain.util.Result.Success
import com.rvcoding.propertypricemockup.core.domain.util.dbCall
import com.rvcoding.propertypricemockup.data.db.PropertyDao
import com.rvcoding.propertypricemockup.data.db.PropertyEntity
import com.rvcoding.propertypricemockup.data.remote.ApiProperty
import com.rvcoding.propertypricemockup.data.remote.PropertiesResponse
import com.rvcoding.propertypricemockup.data.remote.RatesResponse
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.Rates
import com.rvcoding.propertypricemockup.domain.Top3Rates
import com.rvcoding.propertypricemockup.domain.data.remote.api.PropertyDataSource
import com.rvcoding.propertypricemockup.domain.data.repository.PropertyRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


internal class PropertyRepositoryImpl @Inject constructor(
    private val propertyDao: PropertyDao,
    private val propertyDataSource: PropertyDataSource
) : PropertyRepository {
    override val errors = Channel<DataError>()

    override fun listing(): Flow<List<Property>> = propertyDao.getProperties().map { properties -> properties.map { it.toDomain() } }
    override suspend fun refresh() {
        when (val response = propertyDataSource.fetchProperties()) {
            is Error -> errors.send(response.error)
            is Success -> {
                response.data.properties.forEach { property ->
                    dbCall {
                        propertyDao.insertProperty(property.toEntity(response.data.location()))
                    }.also { result ->
                        when (result) {
                            is Error -> {
                                errors.send(result.error)
                                return@refresh
                            }
                            is Success -> {}
                        }
                    }
                }
            }
        }
    }

    override suspend fun refreshDetails(id: Long): Property? {
        when (val response = propertyDataSource.fetchProperties()) {
            is Error -> errors.send(response.error)
            is Success -> {
                response.data.properties.find { it.id == id }?.let { property ->
                    dbCall {
                        propertyDao.insertProperty(property.toEntity(response.data.location()))
                    }.also { result ->
                        when (result) {
                            is Error -> {
                                errors.send(result.error)
                                return@refreshDetails property
                                    .toEntity(response.data.location())
                                    .toDomain()
                            }
                            is Success -> {}
                        }
                    }
                    return property
                        .toEntity(response.data.location())
                        .toDomain()
                }
            }
        }
        return null
    }

    override suspend fun rates(): Rates {
        when (val response = propertyDataSource.fetchRates()) {
            is Error -> errors.send(response.error)
            is Success -> return response.data.toDomain()
        }
        return Rates.FAILURE
    }
}

private fun PropertiesResponse.location() = "${locationEn.city.name}, ${locationEn.city.country}"
private fun ApiProperty.toEntity(location: String): PropertyEntity = PropertyEntity(
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

private fun PropertyEntity.toDomain(): Property = Property(
    id = id,
    name = name,
    location = location,
    overview = overview,
    imgUrl = imgUrl,
    isFeatured = isFeatured,
    rating = rating,
    lowestPricePerNight = lowestPricePerNight,
    lowestPricePerNightCurrency = lowestPricePerNightCurrency,
)

private fun RatesResponse.toDomain() = Rates(
    success = success,
    base = base,
    rates = Top3Rates(
        eur = this.rates.EUR,
        gbp = this.rates.GBP,
        usd = this.rates.USD
    )
)