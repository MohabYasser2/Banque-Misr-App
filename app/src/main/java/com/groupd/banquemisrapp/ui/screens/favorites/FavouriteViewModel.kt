package com.groupd.banquemisrapp.ui.screens.favorites
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.AddFavoriteRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouritesViewModel : ViewModel() {

    private val _favourites = MutableStateFlow<List<AccountDTO>>(emptyList())
    val favourites = _favourites.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()


    fun getFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    UserAPIService.userAPI.getFavorites()
                _favourites.value = response
                Log.d("TAG", "getFavourites: $response")
            } catch (e: Exception) {
                // Handle the error appropriately


                Log.d("TAG", " error getFavourites: $e")
            }
        }
    }

    fun addFavourites(favourite: AddFavoriteRequest ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("TAG", "addFavourite: $favourite")
                val response =
                    UserAPIService.userAPI.addFavorite(favourite)
                _error.value = ""
                Log.d("TAG", "addFavourite: $response")
            }
            catch (e: Exception) {
                // Handle the error appropriately
                _error.value = e.message ?: "Unknown error"
                Log.d("TAG", " error addFavourite: $e")
            }
        }
    }

    fun deleteFavourites(favouriteNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("TAG", "removeAccount: $favouriteNumber")
                val response =
                    UserAPIService.userAPI.deleteFavorite(favouriteNumber)

                Log.d("TAG", "removeAccount: $response")
            }
            catch (e: Exception) {
                // Handle the error appropriately
                Log.d("TAG", " error removeAccount: $e")
            }
        }
    }


}
