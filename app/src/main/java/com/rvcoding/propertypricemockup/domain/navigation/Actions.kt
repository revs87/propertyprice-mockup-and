package com.rvcoding.propertypricemockup.domain.navigation

sealed interface Actions {
    sealed interface YourProperties : Actions {
        data class OnPropertyClick(val id: Int) : YourProperties
    }
}