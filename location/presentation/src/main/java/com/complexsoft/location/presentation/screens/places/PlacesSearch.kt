package com.complexsoft.location.presentation.screens.places

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.complexsoft.common.utils.navigation.LocationRoutes
import com.complexsoft.common.utils.navigation.events.PlacesResult
import com.complexsoft.location.data.model.PlaceDetails
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("PermissionLaunchedDuringComposition")
@Composable
fun RestaurantPhinderScreen(
    navHostController: NavHostController,
    viewModel: PlacesSearchViewModel,
    goToGoogleMap: (PlaceDetails) -> Unit
) {

    val permissionsToRequest = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    val placesResult = viewModel.search.value
    val query = viewModel.query.collectAsState()

    if (permissionsToRequest.allPermissionsGranted) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (searchCons, listCons) = createRefs()
            TextField(value = query.value, onValueChange = {
                viewModel.updateQuery(it)
            }, modifier = Modifier
                .fillMaxWidth()
                .constrainAs(searchCons) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                })
            when (placesResult) {
                is PlacesResult.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is PlacesResult.Success -> {

                    LazyColumn(modifier = Modifier.constrainAs(listCons) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(searchCons.bottom)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }) {
                        items(placesResult.list) {
                            Text(text = it.getFullText(null).toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .clickable {
                                        viewModel.fetchDetails(it.placeId) { details ->
                                            details.origin = LatLng(
                                                placesResult.location?.latitude!!,
                                                placesResult.location?.longitude!!
                                            )
                                            goToGoogleMap.invoke(details)
                                            navHostController.navigate(LocationRoutes.GOOGLE_MAPS.route)
                                        }
                                    })
                        }
                    }


                }

                is PlacesResult.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = placesResult.message.toString())
                    }
                }

                is PlacesResult.Idle -> {

                }
            }
        }

    } else {
        LaunchedEffect(key1 = Unit, block = {

        })
    }

}