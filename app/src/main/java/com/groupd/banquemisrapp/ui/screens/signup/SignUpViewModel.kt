package com.groupd.banquemisrapp.ui.screens.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.LoginAPIService
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.UserDTO
import com.groupd.banquemisrapp.data.Country
import com.groupd.banquemisrapp.data.Gender
import com.groupd.banquemisrapp.data.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val emptyAccountDTO : UserDTO = UserDTO(
        id = 0,
        username = "",
        email = "",
        phoneNumber = "",
        country = Country.USA,
        gender = Gender.MALE,
        dateOfBirth = "",
        accounts = emptyList()
    )

    private val _account = MutableStateFlow<UserDTO>(emptyAccountDTO)
    val account = _account.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    fun setAccount(accountRequest: RegisterRequest) {

        viewModelScope.launch (Dispatchers.IO) {
            try {
             _account.update {
                 Log.d("TAG", "setAccount: $accountRequest")
                 LoginAPIService.loginAPI.register(accountRequest)
             }
                _hasError.update { false }
            }
            catch (e: Exception) {
                _hasError.update { true }
                Log.d("TAG", "setAccount Error: $e")
            }
        }
    }
}