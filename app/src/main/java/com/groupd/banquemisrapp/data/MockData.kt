package com.groupd.banquemisrapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

object MockData {
    var user =
        User(
            fullName = "Mohab Yasser",
            balance = "$10.3",
            email = "mohab@gmail.com",
            dateOfBirth = "1/07/2002",
            country = "Egypt",
            defaultAccountNumber = "Account xxxx6969",
            accounts = mutableStateListOf(
                Account("Long Saving Account", "Account xxxx6969"),
                Account("Current Account", "Account xxxx1111"),
                Account("Credit Account", "Account xxxx2222"),

                ),
            favourites = mutableStateListOf(
                Favourite("Favourite 1", "1234567890"),
                Favourite("Favourite 2", "9876543210")
            ),
            transactions = mutableStateListOf(
                Transaction(
                    true,
                    "$2.3",
                    "Mohanad Yasser",
                    "Visa  - 1234\nToday 11:00 - Received"
                ),
                Transaction(
                    false,
                    "$1.1",
                    "Mohamed Magdy",
                    "Visa  - 1234\nToday 11:00 - Received"
                ),
                Transaction(
                    true,
                    "$16.2",
                    "Asmaa Desouky",
                    "Visa  - 1234\nToday 11:00 - Received"
                ),
                Transaction(
                    true,
                    "$16.2",
                    "Asmaa Desouky",
                    "Visa  - 1234\nToday 11:00 - Received"
                ),
                Transaction(
                    true,
                    "$16.2",
                    "Asmaa Desouky",
                    "Visa  - 1234\nToday 11:00 - Received"
                ),
                Transaction(
                    true,
                    "$16.2",
                    "Asmaa Desouky",
                    "Visa  - 1234\nToday 11:00 - Received"
                ),
            )
        )

}
