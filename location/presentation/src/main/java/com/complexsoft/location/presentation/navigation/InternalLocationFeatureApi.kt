package com.complexsoft.location.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.complexsoft.common.utils.navigation.FeatureApi
import com.complexsoft.common.utils.navigation.LocationRoutes
import com.complexsoft.common.utils.navigation.NestedNavigationRoute
import com.complexsoft.location.data.model.PlaceDetails
import com.complexsoft.location.presentation.screens.google_maps.GoogleMapsScreen
import com.complexsoft.location.presentation.screens.google_maps.GoogleMapsViewModel
import com.complexsoft.location.presentation.screens.places.PlacesSearchViewModel
import com.complexsoft.location.presentation.screens.places.RestaurantPhinderScreen

object InternalLocationFeatureApi : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController, navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = LocationRoutes.PLACES_SEARCH.route,
            route = NestedNavigationRoute.LOCATION.route
        ) {
            composable(route = LocationRoutes.PLACES_SEARCH.route) {
                val viewModel = hiltViewModel<PlacesSearchViewModel>()
                RestaurantPhinderScreen(navHostController,viewModel) {
                    navHostController.currentBackStackEntry?.savedStateHandle?.set("place", it)
                }

            }
            composable(route = LocationRoutes.GOOGLE_MAPS.route) {
                val place =
                    navHostController.previousBackStackEntry?.savedStateHandle?.get<PlaceDetails>("place")
                place?.let {
                    val viewModel = hiltViewModel<GoogleMapsViewModel>()
                    GoogleMapsScreen(navHostController, viewModel, it)
                }
            }
        }
    }
}