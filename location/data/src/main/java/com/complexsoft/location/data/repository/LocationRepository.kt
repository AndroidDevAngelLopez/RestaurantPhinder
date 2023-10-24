package com.complexsoft.location.data.repository

import android.location.Location
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.complexsoft.common.utils.navigation.events.LocationEvent
import com.complexsoft.common.utils.navigation.events.PlacesResult
import com.complexsoft.location.data.model.DirectionDetails
import com.complexsoft.location.data.model.PlaceDetails
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow


interface LocationRepository {


    suspend fun insert(placeDetails: PlaceDetails)
    fun getAllPlaceDetails(): Flow<List<PlaceDetails>>
    fun getLocation(destination: LatLng):Flow<LocationEvent>
    fun getLocationOnce(location: (Location) -> Unit)
    fun searchRestaurants(q: String): Flow<PlacesResult>
    fun fetchPlace(placeId: String): Flow<PlaceDetails>
    suspend fun getDirection(start: LatLng, destination: LatLng, key: String): DirectionDetails

}