package com.complexsoft.location.data.di

import android.content.Context
import androidx.room.Room
import com.complexsoft.location.data.datasource.local.LocalDataSource
import com.complexsoft.location.data.datasource.local.LocalDataSourceImpl
import com.complexsoft.location.data.datasource.local.LocationDao
import com.complexsoft.location.data.datasource.local.PlacesDatabase
import com.complexsoft.location.data.datasource.network.LocationService
import com.complexsoft.location.data.repository.LocationRepository
import com.complexsoft.location.data.repository.LocationRepositoryImpl
import com.complexsoft.location.data.util.Constants.API_KEY
import com.complexsoft.location.data.util.Constants.DATABASE_NAME
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Singleton
    @Provides
    fun providePlacesClient(@ApplicationContext context: Context): PlacesClient {
        Places.initialize(context, API_KEY)
        return Places.createClient(context)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(
        fusedLocationProviderClient: FusedLocationProviderClient,
        placesClient: PlacesClient,
        locationService: LocationService,
        localDataSource: LocalDataSource
    ): LocationRepository {
        return LocationRepositoryImpl(
            fusedLocationProviderClient, placesClient, locationService, localDataSource
        )
    }


    @Provides
    @Singleton
    fun provideLocationService(@LocationRetrofit retrofit: Retrofit): LocationService {
        return retrofit.create(LocationService::class.java)
    }

    @Provides
    @Singleton
    @LocationRetrofit
    fun provideLocationRetrofit(@DirectionApiBaseUrl s: String): Retrofit {
        return Retrofit.Builder().baseUrl(s).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @DirectionApiBaseUrl
    fun provideDirectionApiBaseUrl(): String {
        return "https://maps.googleapis.com/"
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        locationDao: LocationDao
    ): LocalDataSource = LocalDataSourceImpl(locationDao)


    @Singleton
    @Provides
    fun provideLocationDao(placesDatabase: PlacesDatabase) = placesDatabase.locationDao()


    @Singleton
    @Provides
    fun providePlacesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PlacesDatabase::class.java, DATABASE_NAME).build()


}


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DirectionApiBaseUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocationRetrofit
