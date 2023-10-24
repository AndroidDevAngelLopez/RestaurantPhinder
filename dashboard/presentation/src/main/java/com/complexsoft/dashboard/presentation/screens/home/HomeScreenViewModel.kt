package com.complexsoft.dashboard.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.complexsoft.location.domain.usecases.GetAllPlaceDetailsFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val getAllPlaceDetailsFromDbUseCase: GetAllPlaceDetailsFromDbUseCase) :
    ViewModel() {

    val list = getAllPlaceDetailsFromDbUseCase()
}