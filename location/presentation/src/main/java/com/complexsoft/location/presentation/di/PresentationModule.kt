package com.complexsoft.location.presentation.di

import com.complexsoft.location.presentation.navigation.LocationFeatureApi
import com.complexsoft.location.presentation.navigation.LocationFeatureApiImpl
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
    fun provideLocationFeatureApi(): LocationFeatureApi {
        return LocationFeatureApiImpl()
    }

}