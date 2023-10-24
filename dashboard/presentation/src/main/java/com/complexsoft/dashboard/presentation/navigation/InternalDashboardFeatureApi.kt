package com.complexsoft.dashboard.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.complexsoft.common.utils.navigation.DashboardRoutes
import com.complexsoft.common.utils.navigation.FeatureApi
import com.complexsoft.common.utils.navigation.NestedNavigationRoute
import com.complexsoft.dashboard.presentation.screens.home.HomeScreen
import com.complexsoft.dashboard.presentation.screens.home.HomeScreenViewModel

object InternalDashboardFeatureApi : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController, navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = DashboardRoutes.HOME_SCREEN.route,
            route = NestedNavigationRoute.DASHBOARD.route
        ) {

            composable(route = DashboardRoutes.HOME_SCREEN.route) {
                val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                HomeScreen(navHostController, homeScreenViewModel)
            }
        }
    }
}