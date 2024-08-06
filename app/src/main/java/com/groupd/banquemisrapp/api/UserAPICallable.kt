import com.groupd.banquemisrapp.data.UserDTO
import com.groupd.banquemisrapp.data.AddFavoriteRequest
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.AddAccountRequest
import com.groupd.banquemisrapp.data.ChangeDefaultAccountRequest
import com.groupd.banquemisrapp.data.ChangePasswordRequest
import com.groupd.banquemisrapp.data.LoginRequest
import com.groupd.banquemisrapp.data.LoginResponseDTO
import com.groupd.banquemisrapp.data.RegisterRequest
import com.groupd.banquemisrapp.data.RemoveCardRequest
import com.groupd.banquemisrapp.data.TransactionDTO
import com.groupd.banquemisrapp.data.TransferRequest
import com.groupd.banquemisrapp.data.UpdateAccountRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserAPICallable {

    // User Management

    @POST("/api/register")
    suspend fun register(@Body registerRequest: RegisterRequest): UserDTO

    @POST("/api/login")
    suspend fun login(@Body loginRequest: LoginRequest ): LoginResponseDTO

    @POST("/api/logout")
    suspend fun logout(): String

    // Account Information

    @GET("/api/user")
    suspend fun getUser(): UserDTO

    @GET("/api/transactions")
    suspend fun getTransactions(): List<TransactionDTO>

    @GET("/api/accounts")
    suspend fun getAccounts(): List<AccountDTO>

    @PUT("/api/account")
    suspend fun updateAccount(@Body updateAccountRequest: UpdateAccountRequest): UserDTO

    @PUT("/api/password")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Any // Replace 'Any' with actual response type

    // Card Management

    @POST("/api/accounts")
    suspend fun addAccount(@Body addCardRequest: AddAccountRequest): List<AccountDTO>

    @GET("/api/accounts")
    suspend fun getCards(): List<AccountDTO>

    @DELETE("/api/accounts")
    suspend fun removeCard(@Body removeCardRequest: RemoveCardRequest): Any // Replace 'Any' with actual response type

    @PUT("/api/cards/default")
    suspend fun changeDefaultAccount(@Body changeDefaultCardRequest: ChangeDefaultAccountRequest) : Any // Replace 'Any' with actual response type

    // Favorite Management

    @POST("/api/favorites")
    suspend fun addFavorite(@Body addFavoriteRequest: AddFavoriteRequest): List<AccountDTO>

    @GET("/api/favorites")
    suspend fun getFavorites(): List<AccountDTO>

    @DELETE("/api/favorites/{cardNumber}")
    suspend fun removeFavorite(@Path("cardNumber") cardNumber: String): List<AccountDTO>

    // Transfer

    @POST("/api/transfer")
    suspend fun transfer(@Body transferRequest: TransferRequest): Any // Replace 'Any' with actual response type
}