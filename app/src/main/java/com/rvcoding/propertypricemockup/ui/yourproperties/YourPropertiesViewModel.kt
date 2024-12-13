package com.rvcoding.propertypricemockup.ui.yourproperties

import androidx.lifecycle.ViewModel
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.navigation.Actions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class YourPropertiesViewModel : ViewModel() {
    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties.asStateFlow()

    fun onAction(action: Actions.YourProperties) {
        when (action) {
            is Actions.YourProperties.OnPropertyClick -> {}
        }
    }
}