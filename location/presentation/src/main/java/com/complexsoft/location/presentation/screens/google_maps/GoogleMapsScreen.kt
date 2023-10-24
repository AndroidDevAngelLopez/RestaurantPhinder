package com.complexsoft.location.presentation.screens.google_maps

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.complexsoft.common.utils.navigation.LocationRoutes
import com.complexsoft.common.utils.navigation.events.LocationEvent
import com.complexsoft.location.data.model.PlaceDetails
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun GoogleMapsScreen(
    navHostController: NavHostController, viewModel: GoogleMapsViewModel, place: PlaceDetails
) {


    val currentLocation = viewModel.currentLocation.collectAsState()
    val route = viewModel.routePoints.collectAsState()
    val context = LocalContext.current
    val destination = place.destination
    val scope = rememberCoroutineScope()
    when (currentLocation.value) {
        is LocationEvent.Idle -> {}
        is LocationEvent.LocationInProgress -> {
            currentLocation.value.location?.let { location ->
                val cameraPosition = rememberCameraPositionState {
                    position =
                        CameraPosition.builder().zoom(17f).bearing(location.bearing).tilt(45f)
                            .target(
                                LatLng(location.latitude, location.longitude)
                            ).build()
                }
                LaunchedEffect(key1 = route.value, block = {
                    scope.launch {
                        cameraPosition.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition.builder().zoom(17f).bearing(location.bearing)
                                    .tilt(45f).target(
                                        LatLng(location.latitude, location.longitude)
                                    ).build()
                            ), durationMs = 1000
                        )
                    }
                })

                LaunchedEffect(key1 = currentLocation.value, block = {
                    viewModel.getDirections(
                        start = LatLng(location.latitude, location.longitude),
                        destination = destination,
                        key = "AIzaSyBJStR2-XAFQQcoYF3b04yinBraWAIAO1U"
                    )
                })

                GoogleMap(modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPosition) {
                    Polyline(points = route.value.points, color = Color.Blue, width = 20f)
                }

                Log.d("current position", route.value.points.toString())
            }
        }

        is LocationEvent.ReachedDestination -> {
            viewModel.insertPlaceDetails(place)
            Toast.makeText(context, "reach to destination", Toast.LENGTH_SHORT).show()
            navHostController.popBackStack(LocationRoutes.PLACES_SEARCH.route, inclusive = true)
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getLocationUpdates(destination)
    })

}