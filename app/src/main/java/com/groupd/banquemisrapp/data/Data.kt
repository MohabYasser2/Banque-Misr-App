package com.groupd.banquemisrapp.data

data class Account(
    var cardHolder: String,
    var accountNumber: String,
    var balance: String = "$0.0",
    var currency: String = "",
    var expiryDate: String = "",
    var cvv: String = "",
    val isDefault: Boolean = false
    // Add other account details as needed
)

data class Favourite(
    val name: String,
    val accountNumber: String
)

data class Transaction(
    val successful: Boolean,
    val amount: String,
    val accountName: String,
    val details: String
)

data class User(
    val fullName: String,
    val balance: String,
    val email: String,
    val dateOfBirth: String,
    val country: String,
    var receivingAccount: Account = Account("", ""),
    var savingAccount: Account = Account("", ""),
    val accounts: MutableList<Account>,
    var defaultAccountNumber: String ,
    var favourites: MutableList<Favourite>,
    val transactions: MutableList<Transaction>
)