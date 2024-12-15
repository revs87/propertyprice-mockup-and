package com.rvcoding.propertypricemockup.ui.navigation

import androidx.lifecycle.ViewModel
import com.rvcoding.propertypricemockup.common.DispatchersProvider
import com.rvcoding.propertypricemockup.domain.navigation.Destination
import com.rvcoding.propertypricemockup.ui.navigation.core.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigator: Navigator,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {
    fun navigator() = navigator
    fun dispatchersProvider() = dispatchersProvider

    companion object {
        val INITIAL_DESTINATION = Destination.YourProperties
    }
}