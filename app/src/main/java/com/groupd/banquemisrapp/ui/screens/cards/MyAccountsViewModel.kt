package com.groupd.banquemisrapp.ui.screens.cards


import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.LoginAPIService
import com.groupd.banquemisrapp.api.TokenStorage
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.AddAccountRequest
import com.groupd.banquemisrapp.data.Country
import com.groupd.banquemisrapp.data.Gender
import com.groupd.banquemisrapp.data.RegisterRequest
import com.groupd.banquemisrapp.data.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyAccountsViewModel : ViewModel() {

    private val _accounts = MutableStateFlow<List<AccountDTO>>(emptyList())
    val accounts = _accounts.asStateFlow()


    fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    UserAPIService.userAPI.getAccounts()
                _accounts.value = response
                Log.d("TAG", "getAccounts: $response")
            } catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error getAccounts: $e")
            }
        }
    }

    fun addAccount(account: AddAccountRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    UserAPIService.userAPI.addAccount(account)
                _accounts.value = response
                Log.d("TAG", "addAccount: $response")
            }
            catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error addAccount: $e")
            }
        }
    }
}
