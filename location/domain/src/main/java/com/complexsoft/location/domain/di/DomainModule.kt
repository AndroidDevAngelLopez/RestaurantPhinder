package com.complexsoft.location.domain.di

import com.complexsoft.location.data.repository.LocationRepository
import com.complexsoft.location.domain.usecases.FetchRestaurantUseCase
import com.complexsoft.location.domain.usecases.GetAllPlaceDetailsFromDbUseCase
import com.complexsoft.location.domain.usecases.GetDirectionsUseCase
import com.complexsoft.location.domain.usecases.GetLocationUpdatesUseCase
import com.complexsoft.location.domain.usecases.InsertPlaceToDbUseCase
import com.complexsoft.location.domain.usecases.SearchRestaurantsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {


    @Singleton
    @Provides
    fun provideSearchRestaurantUseCase(locationRepository: LocationRepository): SearchRestaurantsUseCase {
        return SearchRestaurantsUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideFetchRestaurantUseCase(locationRepository: LocationRepository): FetchRestaurantUseCase {
        return FetchRestaurantUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideGetDirectionsUseCase(locationRepository: LocationRepository): GetDirectionsUseCase {
        return GetDirectionsUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideGetLocationUpdatesUseCase(locationRepository: LocationRepository): GetLocationUpdatesUseCase {
        return GetLocationUpdatesUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideInsertPlaceToDbUseCase(locationRepository: LocationRepository): InsertPlaceToDbUseCase {
        return InsertPlaceToDbUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllPlaceDetailsFromDbUseCase(locationRepository: LocationRepository): GetAllPlaceDetailsFromDbUseCase {
        return GetAllPlaceDetailsFromDbUseCase(locationRepository)
    }

}