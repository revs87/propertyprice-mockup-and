package com.rvcoding.propertypricemockup.domain.navigation

sealed interface Actions {
    sealed interface YourProperties : Actions {
        data object Refresh : YourProperties
        data object GoToTop : YourProperties
        data class OnPropertyClick(val id: Long) : YourProperties
    }
    sealed interface PropertyDetails : Actions {
        data class Refresh(val id: Long) : PropertyDetails
        data object NavigateBack : PropertyDetails
    }
}