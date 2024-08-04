package com.groupd.banquemisrapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.Account
import com.groupd.banquemisrapp.data.Favourite
import com.groupd.banquemisrapp.data.Transaction
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.MainNavHost
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.routes.Route.ADD_CARD
import com.groupd.banquemisrapp.routes.Route.ADD_CARD_DETAILS
import com.groupd.banquemisrapp.routes.Route.APP_CONNECTION
import com.groupd.banquemisrapp.routes.Route.CARDS
import com.groupd.banquemisrapp.routes.Route.CHANGE_PASSWORD
import com.groupd.banquemisrapp.routes.Route.EDIT_PROFILE
import com.groupd.banquemisrapp.routes.Route.HOME_SCREEN
import com.groupd.banquemisrapp.routes.Route.INTERNET_ERROR
import com.groupd.banquemisrapp.routes.Route.MORE
import com.groupd.banquemisrapp.routes.Route.OTP
import com.groupd.banquemisrapp.routes.Route.OTP_CONNECTED
import com.groupd.banquemisrapp.routes.Route.PROFILE_INFO
import com.groupd.banquemisrapp.routes.Route.SERVER_ERROR
import com.groupd.banquemisrapp.routes.Route.SETTINGS
import com.groupd.banquemisrapp.routes.Route.TRANSACTIONS
import com.groupd.banquemisrapp.routes.Route.TRANSFER
import com.groupd.banquemisrapp.ui.partials.iconNamedVertically
import com.groupd.banquemisrapp.ui.theme.background
import com.groupd.banquemisrapp.ui.theme.background2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var user by remember {
                mutableStateOf(
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
                        )
                    )
                )
            }
            //Log.d("TAG", "onCreate: $user")

            var isSelected by remember { mutableStateOf(HOME_SCREEN) }
            val navController = rememberNavController()
            var backgroundColor by remember { mutableStateOf(background) }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                isSelected = destination.route ?: HOME_SCREEN
                backgroundColor =
                    if (destination.route == Route.SIGNIN /*ADD PAGES LIKE ADD NEW CARD ETC*/) {
                        background
                    } else {
                        background2
                    }

            }
            if (isSelected != ADD_CARD
                && isSelected != ADD_CARD_DETAILS
                && isSelected != APP_CONNECTION
                && isSelected != OTP
                && isSelected != OTP_CONNECTED
                && isSelected != PROFILE_INFO
                && isSelected != SETTINGS
                && isSelected != EDIT_PROFILE
                && isSelected != CHANGE_PASSWORD
                && isSelected != INTERNET_ERROR
                && isSelected != SERVER_ERROR
            ) {
                Scaffold(bottomBar = {


                    val context = LocalContext.current
                    Log.d("TAG", "onCreate: $isSelected")
                    navController.addOnDestinationChangedListener { _, destination, _ ->

                        isSelected = destination.route ?: HOME_SCREEN
                    }


                    Card(
                        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                            containerColor = Color.White, disabledContainerColor = Color.White
                        ), elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ), shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically

                        ) {


                            iconNamedVertically(
                                onClick = {
                                    if (isSelected != HOME_SCREEN) {
                                        navController.navigate(Route.HOME_SCREEN)
                                        isSelected =
                                            navController.currentDestination?.route ?: "home_screen"
                                    }

                                },
                                imageRes = painterResource(id = R.drawable.ic_home_3x),
                                text = "Home",
                                isSelected = isSelected == HOME_SCREEN
                            )
                            iconNamedVertically(
                                onClick = {
                                    if (isSelected != TRANSFER) {
                                        navController.navigate(TRANSFER)
                                        isSelected =
                                            navController.currentDestination?.route ?: "transfer"
                                    }
                                },
                                imageRes = painterResource(id = R.drawable.ic_transfer_3x),
                                text = "Transfer",
                                isSelected = isSelected == "Transfer" || isSelected == TRANSFER
                            )
                            iconNamedVertically(
                                onClick = {
                                    if (isSelected != TRANSACTIONS) {
                                        navController.navigate(Route.TRANSACTIONS)
                                        isSelected = navController.currentDestination?.route
                                            ?: "transactions"
                                    }

                                },
                                imageRes = painterResource(id = R.drawable.ic_history_3x),
                                text = "Transactions",
                                isSelected = isSelected == "Transactions" || isSelected == TRANSACTIONS
                            )
                            iconNamedVertically(
                                onClick = {
                                    if (isSelected != CARDS) {
                                        navController.navigate(Route.CARDS)
                                        isSelected =
                                            navController.currentDestination?.route ?: "cards"
                                    }
                                },
                                imageRes = painterResource(id = R.drawable.ic_card_3x),
                                text = "My Accounts",
                                isSelected = isSelected == "My cards" || isSelected == CARDS
                            )
                            iconNamedVertically(
                                onClick = {
                                    if (isSelected != MORE) {
                                        navController.navigate(Route.MORE)
                                        isSelected =
                                            navController.currentDestination?.route ?: "more"
                                    }
                                },
                                imageRes = painterResource(id = R.drawable.ic_more),
                                text = "More",
                                isSelected = isSelected == "More" || isSelected == MORE
                            )


                        }

                    }
                }


                )


                { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                    ) {
                        MainNavHost(
                            navController = navController, user = user, modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            } else {
                Scaffold { _ ->


                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor, alpha = 1.0f)
                    ) {
                        MainNavHost(
                            navController = navController,
                            user = user,
                            modifier = Modifier
                        )

                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {

}