package com.rvcoding.propertypricemockup.ui.navigation

sealed interface Actions {
    sealed interface YourProperties : Actions {
        data object Refresh : YourProperties
        data object GoToTop : YourProperties
        data class OnPropertyClick(val id: Long) : YourProperties
    }
}