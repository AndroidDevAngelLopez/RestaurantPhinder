package com.complexsoft.location.data.datasource.local

import com.complexsoft.location.data.model.PlaceDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val locationDao: LocationDao
) : LocalDataSource {
    override suspend fun insert(placeDetails: PlaceDetails) {
        locationDao.insert(placeDetails)
    }

    override fun getAllPlaceDetails(): Flow<List<PlaceDetails>> {
        return locationDao.getAllPlaceDetails()
    }
}