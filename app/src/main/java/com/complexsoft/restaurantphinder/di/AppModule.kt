package com.complexsoft.restaurantphinder.di

import com.complexsoft.dashboard.presentation.navigation.DashboardApi
import com.complexsoft.location.presentation.navigation.LocationFeatureApi
import com.complexsoft.restaurantphinder.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideNavigationProvider(
        dashboardApi: DashboardApi,
        locationFeatureApi: LocationFeatureApi
    ): NavigationProvider {
        return NavigationProvider(dashboardApi, locationFeatureApi)
    }

}