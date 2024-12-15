package com.rvcoding.propertypricemockup.di

import com.rvcoding.propertypricemockup.ui.navigation.NavigationViewModel
import com.rvcoding.propertypricemockup.ui.navigation.core.DefaultNavigator
import com.rvcoding.propertypricemockup.ui.navigation.core.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideNavigator(): Navigator = DefaultNavigator(NavigationViewModel.INITIAL_DESTINATION)


}