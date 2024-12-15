package com.rvcoding.propertypricemockup.ui.screen.yourproperties

import com.rvcoding.propertypricemockup.domain.Property

data class YourPropertiesState(
    val properties: List<Property> = emptyList(),
    val isLoading: Boolean = false,
)