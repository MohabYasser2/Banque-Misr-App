package com.groupd.banquemisrapp.ui.screens.main.transfer


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.TransferRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransferViewModel : ViewModel() {

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _receiver = MutableStateFlow(
        TransferRequest(
            AccountDTO(
                "",
                "",
                "",
                "",
                false,
                "",
                0.0,
            ),
            "",
            "",
            0.0,
            0.0
        )
    )
    val receiver = _receiver.asStateFlow()


    fun saveAmount(amount: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _amount.value = amount

            } catch (e: Exception) {

            }
        }
    }

    fun saveReceiver(receiver: TransferRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _receiver.value = receiver

            } catch (e: Exception) {

            }
        }
    }



    fun transfer(transferRequest: TransferRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    UserAPIService.userAPI.transfer(transferRequest)

                Log.d("TAG", "addAccount: $response")
            } catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error addAccount: $e")
            }
        }
    }
}


