package com.groupd.banquemisrapp.data

import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class AccountDTO(
    val id: Long,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val country: Country,
    val gender: Gender,
    val dateOfBirth: String,
    val cards: List<CardDTO>
)

data class CardDTO(
    @NotNull val cardNumber: String,
    @NotNull val cardHolderName: String,
    val expirationDate: String,
    val cvv: String,
    val isDefault: Boolean,
    val cardCurrency: CardCurrency
)

data class TransactionDTO(
    val transactionId: UUID,
    val transactionDate: LocalDateTime,
    val senderCard: CardDTO, // Assuming a different entity
    val recipientCard: CardDTO,
    val amount: Double,
    val isSuccessful: Boolean,
    val senderAccount: Account, // Assuming a different entity
    val recipientAccount: Account
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
    val card: CardDTO? = null // Optional for mobile app
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

data class AddCardRequest(
    val cardNumber: String,
    val cardHolderName: String,
    val expirationDate: String,
    val cvv: String
)

data class RemoveCardRequest(
    val cardNumber: String
)

data class ChangeDefaultCardRequest(
    val cardNumber: String
)

data class AddFavoriteRequest(
    val cardNumber: String,
    val cardHolderName: String
)

data class TransferRequest(
    val cardDTO: CardDTO,
    val sendingCurrency: String,
    val receivingCurrency: String,
    val sentAmount: Double,
    val receivedAmount: Double
)




