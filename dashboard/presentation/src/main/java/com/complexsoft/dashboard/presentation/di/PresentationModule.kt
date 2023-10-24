package com.complexsoft.dashboard.presentation.di

import com.complexsoft.dashboard.presentation.navigation.DashboardApi
import com.complexsoft.dashboard.presentation.navigation.DashboardApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PresentationModule {


    @Singleton
    @Provides
    fun provideDashboardApi(): DashboardApi {
        return DashboardApiImpl()
    }

}