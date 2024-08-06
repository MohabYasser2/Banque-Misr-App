package com.groupd.banquemisrapp.ui.screens.signup
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.LoginAPIService
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.CountryDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val _countries = MutableStateFlow<List<CountryDTO>>(emptyList())
    val countries = _countries.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    LoginAPIService.loginAPI.getCountries()
                _countries.value = response
                Log.d("TAG", "getAccounts: $response")
            } catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error getAccounts: $e")
            }
        }
    }
}
