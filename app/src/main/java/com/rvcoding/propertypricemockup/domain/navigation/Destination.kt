package com.rvcoding.propertypricemockup.domain.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object YourProperties: Destination

    @Serializable
    data class PropertyDetails(val id: Long): Destination
}