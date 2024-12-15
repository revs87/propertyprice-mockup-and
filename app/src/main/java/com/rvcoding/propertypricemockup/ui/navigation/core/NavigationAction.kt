package com.rvcoding.propertypricemockup.ui.navigation.core

import androidx.navigation.NavOptionsBuilder
import com.rvcoding.propertypricemockup.domain.navigation.Destination


sealed interface NavigationAction {

    data class Navigate(
        val destination: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationAction

    data object NavigateUp : NavigationAction
}