package com.rvcoding.propertypricemockup.ui.screen.yourproperties

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcoding.propertypricemockup.common.DispatchersProvider
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.data.repository.PropertyRepository
import com.rvcoding.propertypricemockup.ui.navigation.Actions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class YourPropertiesViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {

    /**
     * I favour StateFlows.
     * Their update method is multi thread safe: isLoading.update { true }
     * */
    val isLoading = MutableStateFlow(false)

    private var hasRefreshed = false
    val properties: StateFlow<List<Property>> = propertyRepository.listing()
        .onStart {
            /**
             * This is used for the 1st data load.
             * Test-friendly approach.
             * */
            if (!hasRefreshed) {
                refresh()
            }
            hasRefreshed = true
        }
        .stateIn(
            /**
             * Default viewModelScope dispatcher is Dispatchers.Main.immediate.
             * So we update it to Dispatchers.IO to release the UI from unneeded overheads.
             * */
            scope = viewModelScope + dispatchersProvider.io,
            /**
             * Data persisted for 5 seconds upon configuration changes.
             * No need for new calls within the 5s timespan.
             * */
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    /**
     * MVI presentation pattern used on user actions all through this method.
     * sealed interface used to define immutable actions.
     * */
    fun onAction(action: Actions.YourProperties) {
        when (action) {
            Actions.YourProperties.Refresh -> refresh()
            Actions.YourProperties.GoToTop -> goToTop()
            is Actions.YourProperties.OnPropertyClick -> goToPropertyDetails(action.id)
        }
    }


    /**
     * Just an extra to show a goToTop button when the list is scrolled.
     * Defining it in the viewModel to handle state upon configuration changes.
     * */
    val canScrollToTop = MutableStateFlow(false)
    val lazyGridState = LazyGridState()
    init {
        snapshotFlow { lazyGridState.firstVisibleItemIndex }
            .map { index -> index > 5 } /* shows after 5th element is no longer visible */
            .onEach { scroll -> canScrollToTop.update { scroll } }
            .launchIn(viewModelScope)
    }
    private fun goToTop() {
        viewModelScope.launch {
            lazyGridState.scrollToItem(0)
        }
    }

    /**
     * Always calls the network for fresh data.
     * Used on screen 1st load and on screen pullToRefresh
     * */
    private fun refresh() {
        viewModelScope.launch(dispatchersProvider.io) {
            isLoading.update { true }
            propertyRepository.refresh()
            isLoading.update { false }
        }
    }

    private fun goToPropertyDetails(id: Long) {
        println(id)
    }
}