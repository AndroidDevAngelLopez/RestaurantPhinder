package com.complexsoft.location.domain.usecases

import com.complexsoft.location.data.repository.LocationRepository
import javax.inject.Inject

class FetchRestaurantUseCase @Inject constructor(private val locationRepository: LocationRepository) {


    operator fun invoke(placeId: String) = locationRepository.fetchPlace(placeId)

}