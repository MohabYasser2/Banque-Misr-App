package com.groupd.banquemisrapp.ui.screens.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.UpdateAccountRequest
import com.groupd.banquemisrapp.data.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        fun editProfile(request: UpdateAccountRequest, context:Context) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response =
                        UserAPIService.userAPI.updateAccount(request)
                    _balance.value = response
                    Log.d("TAG", "getProfile: $response")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Account Edited Successfully", Toast.LENGTH_SHORT).show()
                    }
                    //Toast.makeText(context, "Account Edited Successfully", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    // Handle the error appropriately
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to edit account", Toast.LENGTH_SHORT).show()
                    }
                    Log.d("TAG", "error get Profile: $e")
                }
            }
        }
}