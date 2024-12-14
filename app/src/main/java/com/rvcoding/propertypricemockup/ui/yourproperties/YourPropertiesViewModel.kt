package com.rvcoding.propertypricemockup.ui.yourproperties

import androidx.lifecycle.ViewModel
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.navigation.Actions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class YourPropertiesViewModel @Inject constructor() : ViewModel() {
    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties.asStateFlow()

    fun onAction(action: Actions.YourProperties) {
        when (action) {
            is Actions.YourProperties.OnPropertyClick -> {}
        }
    }
}