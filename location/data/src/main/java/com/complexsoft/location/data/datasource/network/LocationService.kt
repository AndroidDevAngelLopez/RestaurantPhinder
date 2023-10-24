package com.complexsoft.location.data.datasource.network

import com.complexsoft.location.data.model.DirectionApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    //https://maps.googleapis.com/maps/api/directions/json?destination=Montreal&origin=Toronto&key=AIzaSyBJStR2-XAFQQcoYF3b04yinBraWAIAO1U

    @GET("maps/api/directions/json")
    suspend fun getDirection(
        @Query("destination") destination: String,
        @Query("origin") origin: String,
        @Query("key") key: String
    ): DirectionApiResponse

}