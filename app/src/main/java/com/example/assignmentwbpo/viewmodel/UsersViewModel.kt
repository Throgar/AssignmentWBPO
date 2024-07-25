package com.example.assignmentwbpo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.assignmentwbpo.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class LoginSuccess(
    var loginSuccessful: Boolean = false,
    var errorMessage: String? = null
)

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val stateStateHnadle: SavedStateHandle,
    private val repository: Repository
): ViewModel() {

    private val _loginState = MutableStateFlow(LoginSuccess())
    val loginState: StateFlow<LoginSuccess> = _loginState.asStateFlow()

    suspend fun userLogin(token: String?) {
        //TODO: implement login API
        /*_loginState.update { currentState ->
            currentState.copy(
                repository.tryLogin(token)
            )
        }*/
    }

    suspend fun userList(page: Int, perPage: Int) {
        //TODO: implement API for user list
//        repository.getUsers(page, perPage)
    }

}