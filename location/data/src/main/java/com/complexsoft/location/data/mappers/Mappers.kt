package com.complexsoft.location.data.mappers

import com.complexsoft.location.data.model.DirectionApiResponse
import com.complexsoft.location.data.model.DirectionDetails
import com.complexsoft.location.data.model.PlaceDetails
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.maps.android.PolyUtil

fun Place.toDomain(placeId : String?): PlaceDetails {
    return PlaceDetails(
        placeId = placeId.orEmpty(),
        name = this.name.orEmpty(),
        destination = this.latLng!!,
        origin = LatLng(0.0, 0.0),
        delivery = this.delivery.equals(Place.BooleanPlaceAttributeValue.TRUE),
        rating = this.rating?.toFloat() ?: 0f
    )
}

fun DirectionApiResponse.toDomain(): DirectionDetails {
    return DirectionDetails(
        points = PolyUtil.decode(this.routes[0].overview_polyline.points),
        distance = this.routes[0].legs[0].distance.toString(),
        duration = this.routes[0].legs[0].duration.toString(),
        html = this.routes[0].legs[0].steps[0].html_instructions
    )
}