package com.groupd.banquemisrapp.ui.screens.main.transfer


import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.TransferRequest
import com.groupd.banquemisrapp.data.receipientDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.launch

class TransferViewModel : ViewModel() {

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    private val _receiver = MutableStateFlow(
        TransferRequest(
            receipientDTO(
                "",""
            )
            , 0.0, "", ""

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



    fun transfer(transferRequest: TransferRequest): Flow<Boolean> {
        return flow {
            try {
                Log.d("TEST", "Transfer request: $transferRequest")
                UserAPIService.userAPI.transfer(transferRequest)
                emit(true) // Transfer successful
            } catch (e: Exception) {



                if (e.message == "End of input at line 1 column 1 path $") {
                    emit(true) // Transfer Done
                }else{
                    emit(false) // Transfer failed
                }

            }
        }
    }


    fun getError(): Boolean {
        return error.value
    }
}


