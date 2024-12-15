package com.rvcoding.propertypricemockup.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Properties: Destination

    @Serializable
    data class PropertyDetails(val id: Int): Destination
}