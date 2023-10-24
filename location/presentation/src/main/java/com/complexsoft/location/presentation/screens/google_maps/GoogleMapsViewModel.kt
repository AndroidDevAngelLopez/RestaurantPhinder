package com.complexsoft.location.presentation.screens.google_maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.complexsoft.common.utils.navigation.events.LocationEvent
import com.complexsoft.common.utils.navigation.events.UIEvent
import com.complexsoft.location.data.model.DirectionDetails
import com.complexsoft.location.data.model.PlaceDetails
import com.complexsoft.location.domain.usecases.GetAllPlaceDetailsFromDbUseCase
import com.complexsoft.location.domain.usecases.GetDirectionsUseCase
import com.complexsoft.location.domain.usecases.GetLocationUpdatesUseCase
import com.complexsoft.location.domain.usecases.InsertPlaceToDbUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleMapsViewModel @Inject constructor(
    private val getLocationUpdatesUseCase: GetLocationUpdatesUseCase,
    private val getDirectionsUseCase: GetDirectionsUseCase,
    private val insertPlaceToDbUseCase: InsertPlaceToDbUseCase
) : ViewModel() {

    private val _currentLocation: MutableStateFlow<LocationEvent> =
        MutableStateFlow(LocationEvent.Idle())
    val currentLocation: StateFlow<LocationEvent> = _currentLocation

    private val _routePoints: MutableStateFlow<DirectionDetails> =
        MutableStateFlow(DirectionDetails())
    val routePoints: StateFlow<DirectionDetails> = _routePoints


    fun getDirections(start: LatLng, destination: LatLng, key: String) {
        viewModelScope.launch {
            getDirectionsUseCase(start, destination, key).collectLatest {
                when (it) {
                    is UIEvent.Loading -> {}
                    is UIEvent.Error -> {}
                    is UIEvent.Success -> {
                        _routePoints.value = it.data!!
                    }
                }
            }
        }
    }

    fun getLocationUpdates(destination: LatLng) {
        viewModelScope.launch {
            getLocationUpdatesUseCase(destination).collectLatest {
                _currentLocation.value = it
            }
        }
    }

    fun insertPlaceDetails(placeDetails: PlaceDetails) = viewModelScope.launch {
        insertPlaceToDbUseCase(placeDetails)
    }
}