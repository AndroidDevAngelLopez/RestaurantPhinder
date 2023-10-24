package com.complexsoft.location.domain.usecases

import com.complexsoft.common.utils.navigation.events.UIEvent
import com.complexsoft.location.data.repository.LocationRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDirectionsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    operator fun invoke(start: LatLng, destination: LatLng, key: String) =
        flow {
            emit(UIEvent.Loading())
            emit(UIEvent.Success(data = locationRepository.getDirection(start, destination, key)))
        }.catch {
            emit(UIEvent.Error(message = it.message.toString()))
        }.flowOn(Dispatchers.IO)
}