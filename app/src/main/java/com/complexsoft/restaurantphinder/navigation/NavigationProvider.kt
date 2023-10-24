package com.complexsoft.restaurantphinder.navigation

import com.complexsoft.dashboard.presentation.navigation.DashboardApi
import com.complexsoft.location.presentation.navigation.LocationFeatureApi

data class NavigationProvider(
    val dashboardApi: DashboardApi,
    val locationFeatureApi: LocationFeatureApi
)
