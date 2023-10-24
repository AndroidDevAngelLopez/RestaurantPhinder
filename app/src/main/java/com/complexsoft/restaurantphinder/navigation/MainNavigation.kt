package com.complexsoft.restaurantphinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.complexsoft.common.utils.navigation.NestedNavigationRoute

@Composable
fun MainNavigation(navHostController: NavHostController, navigationProvider: NavigationProvider) {


    NavHost(
        navController = navHostController, startDestination = NestedNavigationRoute.DASHBOARD.route
    ) {
        navigationProvider.dashboardApi.registerGraph(navHostController, this)
        navigationProvider.locationFeatureApi.registerGraph(navHostController, this)
    }


}