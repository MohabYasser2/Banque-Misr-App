package com.groupd.banquemisrapp.data

data class Account(
    val cardHolder: String,
    val accountNumber: String,
    val isDefault : Boolean
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
    val accounts: List<Account>,
    val favourites: List<Favourite>,
    val transactions: List<Transaction>
)