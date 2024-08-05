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

    private val emptyLoginDTO : LoginResponseDTO = LoginResponseDTO(
        token = "",
        tokenType = "",
        message = "",
        status = ""
    )

    private val _loginResponse = MutableStateFlow<LoginResponseDTO>(emptyLoginDTO)
    val loginResponse = _loginResponse.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    fun login(loginRequest: LoginRequest)  {

       // Log.d("TAG", "Logging in: $loginRequest")
        viewModelScope.launch (Dispatchers.IO) {
            try {
                Log.d("TAG", "Logging in: $loginRequest")
                _loginResponse.update {
                    UserAPIService.userAPI.login(loginRequest)
                }
                _hasError.update { false }
            }
            catch (e: Exception) {

                _hasError.update { true }
                Log.d("TAG", "Logging in Error: ${e.message}")

            }
        }
    }
}