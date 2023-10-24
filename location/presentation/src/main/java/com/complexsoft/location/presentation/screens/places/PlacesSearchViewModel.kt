package com.complexsoft.location.presentation.screens.places

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.complexsoft.common.utils.navigation.events.PlacesResult
import com.complexsoft.location.data.model.PlaceDetails
import com.complexsoft.location.domain.usecases.FetchRestaurantUseCase
import com.complexsoft.location.domain.usecases.SearchRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesSearchViewModel @Inject constructor(
    private val searchRestaurantsUseCase: SearchRestaurantsUseCase,
    private val fetchRestaurantUseCase: FetchRestaurantUseCase
) : ViewModel() {

    private val _search: MutableState<PlacesResult> = mutableStateOf(PlacesResult.Idle())
    val search: State<PlacesResult> = _search
    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> = _query


    init {
        viewModelScope.launch {
            _query.debounce(500).collectLatest {
                searchRestaurants(it)
            }
        }
    }


    fun updateQuery(q: String) {
        _query.value = q
    }

    private fun searchRestaurants(q: String) = viewModelScope.launch {
        searchRestaurantsUseCase(q).collectLatest {
            _search.value = it
        }
    }

    fun fetchDetails(placeId : String,result: (PlaceDetails) ->Unit) = viewModelScope.launch{
        fetchRestaurantUseCase(placeId).collectLatest {
            result.invoke(it)
        }
    }


}