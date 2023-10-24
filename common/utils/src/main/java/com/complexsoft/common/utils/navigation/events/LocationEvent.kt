package com.complexsoft.common.utils.navigation.events

import android.location.Location

sealed class LocationEvent(val location: Location? = null) {
    class LocationInProgress(location: Location) : LocationEvent(location)
    class ReachedDestination : LocationEvent()
    class Idle : LocationEvent()
}