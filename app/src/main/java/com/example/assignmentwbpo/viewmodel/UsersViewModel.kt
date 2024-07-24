package com.example.assignmentwbpo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.assignmentwbpo.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val stateStateHnadle: SavedStateHandle,
    private val repository: Repository
): ViewModel() {

}