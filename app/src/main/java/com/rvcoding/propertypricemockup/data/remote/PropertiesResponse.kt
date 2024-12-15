package com.rvcoding.propertypricemockup.data.remote

import kotlinx.serialization.SerialName

/**
 * Json to Kotlin converter used
 * */
data class PropertiesResponse(
    val properties: List<Property>,
    val location: Location,
    val locationEn: LocationEn,
    val filterData: FilterData,
    val sortOrder: Any?,
    val pagination: Pagination,
)

data class Property(
    val id: Long,
    val isPromoted: Boolean,
    val hbid: Long,
    val name: String,
    val starRating: Long,
    val overallRating: OverallRating,
    val ratingBreakdown: RatingBreakdown,
    val latitude: Double,
    val longitude: Double,
    val isFeatured: Boolean,
    val type: String,
    val address1: String,
    val address2: String,
    val freeCancellationAvailable: Boolean,
    val freeCancellationAvailableUntil: String,
    val district: District?,
    val districts: List<District2>,
    val freeCancellation: FreeCancellation,
    val lowestPricePerNight: LowestPricePerNight,
    val lowestPrivatePricePerNight: LowestPrivatePricePerNight,
    val lowestDormPricePerNight: LowestDormPricePerNight?,
    val lowestAveragePricePerNight: LowestAveragePricePerNight,
    val lowestAverageDormPricePerNight: LowestAverageDormPricePerNight?,
    val lowestAveragePrivatePricePerNight: LowestAveragePrivatePricePerNight,
    val isNew: Boolean,
    val overview: String,
    val isElevate: Boolean,
    val hostelworldRecommends: Boolean,
    val distance: Distance,
    val position: Long,
    val hwExtra: Any?,
    val fabSort: FabSort,
    val promotions: List<Promotion>,
    val rateRuleViolations: List<RateRuleViolation>,
    val originalLowestAveragePricePerNight: OriginalLowestAveragePricePerNight,
    val originalLowestAverageDormPricePerNight: OriginalLowestAverageDormPricePerNight?,
    val originalLowestAveragePrivatePricePerNight: OriginalLowestAveragePrivatePricePerNight,
    val fenceDiscount: Long,
    val veryPopular: Boolean?,
    val images: List<Image>,
    val imagesGallery: List<ImagesGallery>,
    val facilities: List<Facility>,
    val stayRuleViolations: List<StayRuleViolation>?,
)

data class OverallRating(
    val overall: Long,
    val numberOfRatings: String,
)

data class RatingBreakdown(
    val ratingsCount: Long,
    val security: Long,
    val location: Long,
    val staff: Long,
    @SerialName("fun")
    val fun_field: Long,
    val clean: Long,
    val facilities: Long,
    val value: Long,
    val average: Long,
)

data class District(
    val id: String,
    val name: String,
)

data class District2(
    val id: Long,
    val name: String,
)

data class FreeCancellation(
    val label: String,
    val description: String,
)

data class LowestPricePerNight(
    val value: String,
    val currency: String,
)

data class LowestPrivatePricePerNight(
    val value: String,
    val currency: String,
)

data class LowestDormPricePerNight(
    val value: String,
    val currency: String,
)

data class LowestAveragePricePerNight(
    val value: String,
    val currency: String,
    val promotions: Promotions?,
    val original: String?,
)

data class Promotions(
    val promotionsIds: List<Long>,
    val totalDiscount: String,
)

data class LowestAverageDormPricePerNight(
    val value: String,
    val currency: String,
    val promotions: Promotions2?,
    val original: String?,
)

data class Promotions2(
    val promotionsIds: List<Long>,
    val totalDiscount: String,
)

data class LowestAveragePrivatePricePerNight(
    val value: String,
    val currency: String,
    val promotions: Promotions3?,
    val original: String?,
)

data class Promotions3(
    val promotionsIds: List<Long>,
    val totalDiscount: String,
)

data class Distance(
    val value: Double,
    val units: String,
)

data class FabSort(
    val rank1: Long,
    val rank2: Long,
    val rank3: Long,
    val rank4: Long,
    val rank5: Long,
    val rank6: Long,
    val rank7: Long,
    val rank8: Long,
    val rank9: Long,
)

data class Promotion(
    val id: Long,
    val type: String,
    val stack: Boolean,
    val name: String,
    val discount: Long,
)

data class RateRuleViolation(
    @SerialName("NightsStay")
    val nightsStay: NightsStay,
)

data class NightsStay(
    @SerialName("Min")
    val min: Long,
)

data class OriginalLowestAveragePricePerNight(
    val value: String,
    val currency: String,
)

data class OriginalLowestAverageDormPricePerNight(
    val value: String,
    val currency: String,
)

data class OriginalLowestAveragePrivatePricePerNight(
    val value: String,
    val currency: String,
)

data class Image(
    val prefix: String,
    val suffix: String,
)

data class ImagesGallery(
    val prefix: String,
    val suffix: String,
)

data class Facility(
    val name: String,
    val id: String,
    val facilities: List<Facility2>,
)

data class Facility2(
    val name: String,
    val id: String,
)

data class StayRuleViolation(
    val description: String,
)

data class Location(
    val city: City,
    val region: Any?,
)

data class City(
    val id: Long,
    val name: String,
    val idCountry: Long,
    val country: String,
)

data class LocationEn(
    val city: City2,
    val region: Any?,
)

data class City2(
    val id: Long,
    val name: String,
    val idCountry: Long,
    val country: String,
)

data class FilterData(
    val highestPricePerNight: HighestPricePerNight,
    val lowestPricePerNight: LowestPricePerNight2,
)

data class HighestPricePerNight(
    val value: String,
    val currency: String,
)

data class LowestPricePerNight2(
    val value: String,
    val currency: String,
)

data class Pagination(
    val next: String,
    val prev: String,
    val numberOfPages: Long,
    val totalNumberOfItems: Long,
)
