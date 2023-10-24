package com.complexsoft.location.data.repository

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.complexsoft.common.utils.navigation.events.LocationEvent
import com.complexsoft.common.utils.navigation.events.PlacesResult
import com.complexsoft.location.data.datasource.local.LocalDataSource
import com.complexsoft.location.data.datasource.network.LocationService
import com.complexsoft.location.data.mappers.toDomain
import com.complexsoft.location.data.model.DirectionDetails
import com.complexsoft.location.data.model.PlaceDetails
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.LocationRestriction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val placesClient: PlacesClient,
    private val locationService: LocationService,
    private val localDataSource: LocalDataSource
) : LocationRepository {


    private var currentLocation: LatLng = LatLng(0.0, 0.0)
    private val token = AutocompleteSessionToken.newInstance()
    override suspend fun insert(placeDetails: PlaceDetails) {
        localDataSource.insert(placeDetails)
    }

    override fun getAllPlaceDetails(): Flow<List<PlaceDetails>> {
        return localDataSource.getAllPlaceDetails()
    }

    override fun getLocation(destination: LatLng): Flow<LocationEvent> = callbackFlow {
        val locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 100).setIntervalMillis(5000)
                .build()
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                currentLocation = LatLng(
                    locationResult.locations[0].latitude, locationResult.locations[0].longitude
                )
                if (isReachedToTheDestination(currentLocation, destination)) {
                    trySend(LocationEvent.ReachedDestination())
                } else {
                    trySend(LocationEvent.LocationInProgress(locationResult.locations[0]))
                }
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    private fun isReachedToTheDestination(origin: LatLng, destination: LatLng): Boolean {
        val array = FloatArray(1)

        Location.distanceBetween(
            origin.latitude, origin.longitude, destination.latitude, destination.longitude, array
        )
        Log.d("OMG IS REACHED", "LOCATION REACHED : ${array[0] <= 15f}")
        return array[0] <= 15f
    }

    override fun getLocationOnce(location: (Location) -> Unit) {


        val locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 100).setIntervalMillis(1000)
                .setMaxUpdates(1).build()
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                p0.locations[0]?.let {
                    currentLocation = LatLng(it.latitude, it.longitude)
                    location.invoke(it)
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)

    }

    override fun searchRestaurants(q: String): Flow<PlacesResult> = callbackFlow {

        getLocationOnce { location ->
            val locationRestriction = findLocationRestriction(location)
            val request = FindAutocompletePredictionsRequest.builder().setSessionToken(token)
                .setCountries(mutableListOf("MX")).setQuery(q)
                .setOrigin(LatLng(location.latitude, location.longitude))
                .setTypesFilter(mutableListOf("restaurant"))
                .setLocationRestriction(locationRestriction).build()
            placesClient.findAutocompletePredictions(request).addOnSuccessListener {
                trySend(PlacesResult.Success(location, it.autocompletePredictions))
            }.addOnFailureListener {
                trySend(PlacesResult.Error(it.message.toString()))
            }
        }
        awaitClose { }
    }

    override fun fetchPlace(placeId: String): Flow<PlaceDetails> = callbackFlow {
        val placesList = listOf(
            Place.Field.DELIVERY,
            Place.Field.LAT_LNG,
            Place.Field.ADDRESS,
            Place.Field.PHONE_NUMBER,
            Place.Field.NAME,
            Place.Field.RATING
        )
        val request = FetchPlaceRequest.builder(placeId, placesList).build()

        placesClient.fetchPlace(request).addOnSuccessListener {
            trySend(it.place.toDomain(placeId))
        }
        awaitClose { }

    }

    override suspend fun getDirection(
        start: LatLng, destination: LatLng, key: String
    ): DirectionDetails {
        val startLatLng = "${start.latitude},${start.longitude}"
        val destinationLatLng = "${destination.latitude},${destination.longitude}"
        val response = locationService.getDirection(startLatLng, destinationLatLng, key)
        Log.d("list response", response.status)
        return response.toDomain()
    }

    private fun findLocationRestriction(location: Location): LocationRestriction {
        return RectangularBounds.newInstance(
            LatLng(location.latitude - 0.9, location.longitude - 0.9),
            LatLng(location.latitude + 0.9, location.longitude + 0.9)
        )
    }


}