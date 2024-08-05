package com.groupd.banquemisrapp.ui.screens.signin


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.LoginRequest
import com.groupd.banquemisrapp.data.LoginResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val emptyLoginDTO: LoginResponseDTO = LoginResponseDTO(
        token = "",
        tokenType = "",
        message = "",
        status = ""
    )

    private val _loginResponse = MutableStateFlow<LoginResponseDTO?>(emptyLoginDTO)
    val loginResponse = _loginResponse.asStateFlow()


    fun saveLoginResponse(loginResponseDTO: LoginResponseDTO?) {
        _loginResponse.update {
            loginResponseDTO
        }
        Log.d("TAG", "saveLoginResponse: ${loginResponse.value}")

    }
}