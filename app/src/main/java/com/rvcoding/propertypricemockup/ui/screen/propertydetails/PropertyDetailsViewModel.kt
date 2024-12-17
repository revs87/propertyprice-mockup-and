package com.rvcoding.propertypricemockup.ui.screen.propertydetails

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcoding.propertypricemockup.core.DispatchersProvider
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.data.repository.PropertyRepository
import com.rvcoding.propertypricemockup.domain.navigation.Actions
import com.rvcoding.propertypricemockup.ui.navigation.core.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    val dispatchersProvider: DispatchersProvider,
    private val navigator: Navigator
) : ViewModel() {

    private val propertyId = MutableStateFlow(-1L)
    fun setPropertyId(id: Long) = propertyId.update { id }

    val isLoading = MutableStateFlow(false)
    val errors = propertyRepository.errors.receiveAsFlow()
    val property: StateFlow<Property> = combine(
        tickFor5Seconds(),
        snapshotFlow { propertyId.value }
    ) { _, propertyId ->
        isLoading.update { true }
        val details = propertyRepository.refreshDetails(propertyId)
        isLoading.update { false }
        details
    }
        .filterNotNull()
        .stateIn(
            scope = viewModelScope + dispatchersProvider.io,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Property.INITIAL
        )
    private fun tickFor5Seconds() = flow<Long> {
        while (true) {
            emit(System.currentTimeMillis())
            delay(5_000L)
        }
    }
    @SuppressLint("DefaultLocale")
    val extraCurrencies = property
        .combine(tickFor5Seconds()) { property, _ ->
            Pair(property.lowestPricePerNight, property.lowestPricePerNightCurrency)
        }
        .map {
            val (price, currency) = it
            val rates = propertyRepository.rates()
            rates.also(::println)
            mapOf(
                "EUR" to String.format("%.2f", price),
                "USD" to String.format("%.2f", price.times(rates.rates.usd)),
                "GBP" to String.format("%.2f", price.times(rates.rates.gbp))
            )
        }
        .stateIn(
            scope = viewModelScope + dispatchersProvider.io,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = mapOf()
        )

    /**
     * RXJava3 POC
     * */
//    val propertyRX: Observable<Property> = Observable.combineLatest(
//        Observable.interval(5, TimeUnit.SECONDS),
//        Observable.just(propertyId.value)
//    ) { _, propertyId: Long ->
//        runBlocking { propertyRepository.refreshDetails(propertyId) } ?: Property.INITIAL
//    }
//        .filter { it.name != Property.INITIAL.name }
//    val propertyRXStream = propertyRX
//        .subscribeOn(Schedulers.io())
//        .subscribe { property ->
//            property.also(::println)
//        }
//    override fun onCleared() {
//        propertyRXStream.dispose()
//        super.onCleared()
//    }


    fun onAction(action: Actions.PropertyDetails) {
        when (action) {
            is Actions.PropertyDetails.Refresh -> propertyId.update { action.id }
            Actions.PropertyDetails.NavigateBack -> viewModelScope.launch {
                navigator.navigateUp()
            }
        }
    }
}