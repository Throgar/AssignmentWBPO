package com.example.assignmentwbpo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.assignmentwbpo.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.util.Objects
import javax.inject.Inject


sealed class RegistrationResponse {
    object Idle: RegistrationResponse()
    object Failed: RegistrationResponse()
}

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val stateStateHnadle: SavedStateHandle,
    private val repository: Repository
): ViewModel() {

    val _loginState = MutableStateFlow<RegistrationResponse>(RegistrationResponse.Idle)
    val loginState: StateFlow<RegistrationResponse> = _loginState.asStateFlow()

    suspend fun userRegistration(email: String, password: String) {
        //TODO: implement login API
        withContext(Dispatchers.IO) {
        val resultRegister = repository.registerUser(email, password)
        if (resultRegister.isFailure) {
            _loginState.value = RegistrationResponse.Failed
        } else {

        }
            }

    }

    suspend fun userList(page: Int, perPage: Int) {
        //TODO: implement API for user list
//        repository.getUsers(page, perPage)
    }

    fun hasToken(): Boolean{
        return repository.hasToken()
    }

}