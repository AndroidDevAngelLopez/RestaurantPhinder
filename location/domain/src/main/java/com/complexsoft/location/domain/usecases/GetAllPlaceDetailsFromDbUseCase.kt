package com.complexsoft.location.domain.usecases

import com.complexsoft.location.data.repository.LocationRepository
import javax.inject.Inject

class GetAllPlaceDetailsFromDbUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    operator fun invoke() = repository.getAllPlaceDetails()
}