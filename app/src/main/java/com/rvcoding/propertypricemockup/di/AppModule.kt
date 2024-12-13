package com.rvcoding.propertypricemockup.di

import com.rvcoding.propertypricemockup.common.DispatchersProvider
import com.rvcoding.propertypricemockup.common.StandardDispatchersProvider
import com.rvcoding.propertypricemockup.ui.yourproperties.YourPropertiesViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    /**
     * Setup
     * */
    single<DispatchersProvider> { StandardDispatchersProvider }
    val coExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineException: ${throwable.printStackTrace()}")
    }
    single<CoroutineExceptionHandler> { coExceptionHandler }
    single<CoroutineScope> { CoroutineScope(StandardDispatchersProvider.io + coExceptionHandler) }

    /**
     * Navigation
     * */
//    single<Navigator> {
//        DefaultNavigator(startDestination = Destination.YourAlarms)
//    }

    /**
     * Databases
     * */
//    single { AlarmsDatabase.createDb(androidContext()).dao }

    /**
     * Repositories
     * */
//    single<AlarmRepository> { AlarmRepositoryImpl(get()) }

    /**
     * ViewModels
     * */
    viewModel { YourPropertiesViewModel() }
}