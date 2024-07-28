package com.example.assignmentwbpo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.assignmentwbpo.data.UserData
import com.example.assignmentwbpo.data.UserJsonResponse
import com.example.assignmentwbpo.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class UiStateDialog {
    object Idle: UiStateDialog()
    object Loading: UiStateDialog()
    object Success: UiStateDialog()
    object Failed: UiStateDialog()
}

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

    val _dialogState = MutableStateFlow<UiStateDialog>(UiStateDialog.Idle)
    val dialogState: StateFlow<UiStateDialog> = _dialogState.asStateFlow()

    val _userData = MutableStateFlow(listOf(UserData()))
    val userData: StateFlow<List<UserData>> = _userData.asStateFlow()

    suspend fun userRegistration(email: String, password: String) {

        withContext(Dispatchers.IO) {
        _dialogState.value = UiStateDialog.Loading
        val resultRegister = repository.registerUser(email, password)
        if (resultRegister.isFailure) {
            _loginState.value = RegistrationResponse.Failed
            _dialogState.value = UiStateDialog.Failed
        } else {
            _dialogState.value = UiStateDialog.Success
        }
            }
    }

    suspend fun userList(page: Int, perPage: Int): List<UserData> {
        val result = repository.getUsers(page, perPage)
        if (result.isSuccess) {
            val something: UserJsonResponse = result.getOrDefault(UserJsonResponse())
            _userData.value = something.data!!
            _dialogState.value = UiStateDialog.Success
        } else {
            _dialogState.value = UiStateDialog.Failed
        }
    }

    fun hasToken(): Boolean{
        return repository.hasToken()
    }

}