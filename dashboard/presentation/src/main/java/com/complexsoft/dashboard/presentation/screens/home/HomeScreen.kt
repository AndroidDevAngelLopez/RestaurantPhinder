package com.complexsoft.dashboard.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.complexsoft.common.utils.navigation.LocationRoutes
import com.complexsoft.location.data.model.PlaceDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController, homeScreenViewModel: HomeScreenViewModel) {


    val list = homeScreenViewModel.list.collectAsState(initial = emptyList())

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Home Page") }, actions = {
            IconButton(onClick = {
                navHostController.navigate(LocationRoutes.PLACES_SEARCH.route)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        })
    }) {
        Log.d("TAG", "Home Screen: $it")
        if (list.value.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No destination found!")
            }
        } else {
            LazyColumn {
                items(list.value) {
                    LocationListItem(it)
                }
            }
        }
    }


}

@Composable
fun LocationListItem(it: PlaceDetails) {

    Spacer(modifier = Modifier.height(12.dp))
    Text(text = "Start : ${it.origin.latitude} , ${it.origin.longitude}")
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Destination : ${it.destination.latitude} , ${it.destination.longitude}")
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Name : ${it.name} ")
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Rating : ${it.rating}")
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = if (it.delivery) "Delivery is available" else "No delivery")
    Spacer(modifier = Modifier.height(12.dp))
}
