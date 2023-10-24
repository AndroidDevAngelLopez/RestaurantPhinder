package com.complexsoft.location.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.complexsoft.common.utils.navigation.FeatureApi

interface LocationFeatureApi : FeatureApi {

}


class LocationFeatureApiImpl : LocationFeatureApi{
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalLocationFeatureApi.registerGraph(navHostController,navGraphBuilder)
    }

}