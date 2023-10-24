package com.complexsoft.location.domain.usecases

import com.complexsoft.location.data.model.PlaceDetails
import com.complexsoft.location.data.repository.LocationRepository
import javax.inject.Inject

class InsertPlaceToDbUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(placeDetails: PlaceDetails) = repository.insert(placeDetails)

}