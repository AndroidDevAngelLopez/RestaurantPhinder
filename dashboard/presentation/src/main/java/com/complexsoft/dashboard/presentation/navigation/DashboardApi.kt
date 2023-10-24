package com.complexsoft.dashboard.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.complexsoft.common.utils.navigation.FeatureApi

interface DashboardApi : FeatureApi

class DashboardApiImpl : DashboardApi {
    override fun registerGraph(
        navHostController: NavHostController, navGraphBuilder: NavGraphBuilder
    ) {
        InternalDashboardFeatureApi.registerGraph(navHostController, navGraphBuilder)
    }

}