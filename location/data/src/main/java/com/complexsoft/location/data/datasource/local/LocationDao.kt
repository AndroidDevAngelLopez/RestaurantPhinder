package com.complexsoft.location.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.complexsoft.location.data.model.PlaceDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(placeDetails: PlaceDetails)


    @Query("SELECT * FROM PlaceDetails")
    fun getAllPlaceDetails(): Flow<List<PlaceDetails>>

}