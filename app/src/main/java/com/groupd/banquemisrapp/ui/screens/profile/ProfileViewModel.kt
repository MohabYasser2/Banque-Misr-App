package com.groupd.banquemisrapp.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel(){
        private val _balance = MutableStateFlow<UserDTO?>(null)
        val balance = _balance.asStateFlow()


        fun getProfile() {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response =
                        UserAPIService.userAPI.getUser()
                    _balance.value = response
                    Log.d("TAG", "getProfile: $response")
                } catch (e: Exception) {
                    // Handle the error appropriately
                    Log.d("TAG", "error get Profile: $e")
                }
            }
        }
}