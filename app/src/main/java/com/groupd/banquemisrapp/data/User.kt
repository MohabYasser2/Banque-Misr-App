package com.groupd.banquemisrapp.data

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import java.util.UUID

data class UserDTO(
    val id: Long,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val country: String,
    val gender: Gender,
    val dateOfBirth: String,
    val accounts: List<AccountDTO>
)

data class AccountDTO(
    @NotNull val accountNumber: String,
    @NotNull val accountHolderName: String,
    val expirationDate: String,
    val cvv: String,
    val isDefault: Boolean,
    val accountCurrency: String,
    val balance: Double
)

data class receipientDTO(
    val accountNumber: String,
    val accountHolderName: String,
)


data class TransactionDTO(
    val transactionId: Int,
    val transactionDate: String,
    val senderAccount: AccountDTO,
    val recipientAccount: AccountDTO,
    val amount: Double,
    val senderUser: UserDTO,
    val recipientUser: UserDTO,
    val successful: Boolean
)

enum class Country {
    USA, CANADA, MEXICO, BRAZIL
}

enum class Gender {
    MALE, FEMALE, OTHER
}

enum class CardCurrency {
    USD, EUR, GBP, CAD
}

data class LoginResponseDTO(
    val token: String,
    val tokenType: String,
    val message: String,
    val status: String // Assuming HttpStatus is a string
)


data class RegisterRequest(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val address: String = "", // Optional
    val country: String,
    val gender: String,
    val dateOfBirth: String,
    val password: String,
     // Optional for mobile app
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class UpdateAccountRequest(
    val username: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val country: String? = null,
    val dateOfBirth: String? = null,
    val password: String? = null
)

data class ChangePasswordRequest(

    val password: String
)

data class AddAccountRequest(
    val accountNumber: String,
    val accountHolderName: String,
    val expirationDate: String,
    val cvv: String
)

data class RemoveCardRequest(
    val accountNumber: String
)

data class ChangeDefaultAccountRequest(
    val accountNumber: String
)

data class AddFavoriteRequest(
    val accountNumber: String,
    val accountHolderName: String
)

data class TransferRequest(
    val accountDTO: receipientDTO,
    val sentAmount: Double,
    val sendingCurrency: String,
    val receivingCurrency: String
)





data class CountryDTO(
     val id: Int,
     val currency: String,
     val flag: String,
     val country: String,
     val currencySymbol: String,
     val rateToDollar: Double
)


