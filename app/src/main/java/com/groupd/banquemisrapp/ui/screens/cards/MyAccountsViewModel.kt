package com.groupd.banquemisrapp.ui.screens.cards


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.AddAccountRequest
import com.groupd.banquemisrapp.data.ChangeDefaultAccountRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

                Log.d("TAG", "addAccount: $response")
            }
            catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error addAccount: $e")
            }
        }
    }

    fun makeDefaultAccount(accountNumber: ChangeDefaultAccountRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =

                    UserAPIService.userAPI.changeDefaultAccount(accountNumber)

                Log.d("TAG", "makeDefaultAccount: $response")
            }
            catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error makeDefaultAccount: $e")
            }
        }
    }
}
