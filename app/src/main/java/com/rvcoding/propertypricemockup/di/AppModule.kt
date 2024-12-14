package com.rvcoding.propertypricemockup.di

import com.rvcoding.propertypricemockup.common.DispatchersProvider
import com.rvcoding.propertypricemockup.common.StandardDispatchersProvider
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

    @Provides
    @Singleton
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            println("CoroutineException: ${throwable.printStackTrace()}")
        }

    @Provides
    @Singleton
    fun provideCoroutineScope(
        dispatchersProvider: DispatchersProvider,
        coroutineExceptionHandler: CoroutineExceptionHandler
    ) =
        CoroutineScope(dispatchersProvider.io + coroutineExceptionHandler)

}