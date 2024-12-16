package com.rvcoding.propertypricemockup.di

import android.app.Application
import com.rvcoding.propertypricemockup.core.DispatchersProvider
import com.rvcoding.propertypricemockup.core.StandardDispatchersProvider
import com.rvcoding.propertypricemockup.data.db.PropertyDao
import com.rvcoding.propertypricemockup.data.db.PropertyDatabase
import com.rvcoding.propertypricemockup.data.remote.api.PropertyService
import com.rvcoding.propertypricemockup.data.remote.api.StatsInterceptor
import com.rvcoding.propertypricemockup.data.repository.PropertyRepositoryImpl
import com.rvcoding.propertypricemockup.domain.data.remote.api.PropertyDataSource
import com.rvcoding.propertypricemockup.domain.data.repository.PropertyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvider = StandardDispatchersProvider

    /**
     * Error handler for our custom coroutine scope instance.
     * */
    @Provides
    @Singleton
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            println("CoroutineException: ${throwable.printStackTrace()}")
        }

    /**
     * A custom coroutine scope available to serve as a parent for all our coroutines.
     * Enable us to cancel all threads within any scope (coroutineContext.cancelChildren()) without cancelling this scope itself.
     * coroutineContext = Dispatcher.IO + coroutineExceptionHandler
     * */
    @Provides
    @Singleton
    fun provideCoroutineScope(
        dispatchersProvider: DispatchersProvider,
        coroutineExceptionHandler: CoroutineExceptionHandler
    ) =
        CoroutineScope(dispatchersProvider.io + coroutineExceptionHandler)

    /**
     * Source of Truth defined in a Room database.
     * */
    @Provides
    @Singleton
    fun providePropertyDao(app: Application): PropertyDao = PropertyDatabase.createDb(app).dao

    /**
     * Network calls are made using Retrofit + Gson
     * */
    @Provides
    @Singleton
    fun providesPropertyDataSource(
        provideStatsInterceptor: StatsInterceptor
    ): PropertyDataSource = PropertyService(provideStatsInterceptor)

    /**
     * The Bonus Tracker for network calls
     * */
    @Provides
    @Singleton
    fun provideStatsInterceptor(
        provideDispatchers: DispatchersProvider,
        provideCoroutineScope: CoroutineScope
    ): StatsInterceptor = StatsInterceptor(provideDispatchers, provideCoroutineScope)

    /**
     * Repository to handle db and network data
     * */
    @Provides
    @Singleton
    fun providePropertiesRepository(
        propertyDao: PropertyDao,
        propertyDataSource: PropertyDataSource
    ): PropertyRepository = PropertyRepositoryImpl(propertyDao, propertyDataSource)

}