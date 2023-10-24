package com.complexsoft.location.domain.usecases

import com.complexsoft.location.data.repository.LocationRepository
import javax.inject.Inject

class SearchRestaurantsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(q:String) = locationRepository.searchRestaurants(q)
}