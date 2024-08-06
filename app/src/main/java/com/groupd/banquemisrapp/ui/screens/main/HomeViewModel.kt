package com.groupd.banquemisrapp.ui.screens.main

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.LoginAPIService
import com.groupd.banquemisrapp.api.TokenStorage
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.Country
import com.groupd.banquemisrapp.data.Gender
import com.groupd.banquemisrapp.data.RegisterRequest
import com.groupd.banquemisrapp.data.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _balance = MutableStateFlow<UserDTO?>(null)
    val balance = _balance.asStateFlow()


    fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    UserAPIService.userAPI.getUser()
                _balance.value = response
                Log.d("TAG", "getBalance: $response")
            } catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error getBalance: $e")
            }
        }
    }
}

