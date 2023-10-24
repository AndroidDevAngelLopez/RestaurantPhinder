package com.complexsoft.common.utils.navigation

enum class NestedNavigationRoute(val route: String) {
    DASHBOARD("dashboard"), LOCATION("location")
}


enum class DashboardRoutes(val route: String) {
    HOME_SCREEN("home")
}

enum class LocationRoutes(val route: String) {
    PLACES_SEARCH("places"), GOOGLE_MAPS("google_maps")
}