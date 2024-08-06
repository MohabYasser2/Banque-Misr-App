package com.groupd.banquemisrapp.ui.screens.main.transactions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.LoginAPIService
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.Transaction
import com.groupd.banquemisrapp.data.TransactionDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel(){
    private val _transactions = MutableStateFlow<List<TransactionDTO>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    fun fetchTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    UserAPIService.userAPI.getTransactions()
                _transactions.value = response
                Log.d("TAG", "getTransactions: $response")
            } catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error getAccounts: $e")
            }

        }
    }
}