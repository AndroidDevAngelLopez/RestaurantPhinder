package com.complexsoft.location.data.datasource.local

import com.complexsoft.location.data.model.PlaceDetails
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insert(placeDetails: PlaceDetails)
    fun getAllPlaceDetails(): Flow<List<PlaceDetails>>
}