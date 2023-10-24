package com.complexsoft.location.domain.usecases

import com.complexsoft.location.data.repository.LocationRepository
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class GetLocationUpdatesUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    operator fun invoke(destination: LatLng) = locationRepository.getLocation(destination)
}