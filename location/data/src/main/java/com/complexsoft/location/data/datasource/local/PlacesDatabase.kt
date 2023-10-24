package com.complexsoft.location.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.complexsoft.location.data.model.LatLngTypeConverters
import com.complexsoft.location.data.model.PlaceDetails


@Database(entities = [PlaceDetails::class], version = 1, exportSchema = false)
@TypeConverters(LatLngTypeConverters::class)
abstract class PlacesDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}