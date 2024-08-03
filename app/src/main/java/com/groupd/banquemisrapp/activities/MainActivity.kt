package com.groupd.banquemisrapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.BottomAppBar

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.routes.AppNavHost
import com.groupd.banquemisrapp.routes.MainNavHost
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.routes.Route.ADD_CARD
import com.groupd.banquemisrapp.routes.Route.ADD_CARD_DETAILS
import com.groupd.banquemisrapp.routes.Route.APP_CONNECTION
import com.groupd.banquemisrapp.routes.Route.CARDS
import com.groupd.banquemisrapp.routes.Route.CHANGE_PASSWORD
import com.groupd.banquemisrapp.routes.Route.EDIT_PROFILE
import com.groupd.banquemisrapp.routes.Route.HOME
import com.groupd.banquemisrapp.routes.Route.HOME_SCREEN
import com.groupd.banquemisrapp.routes.Route.INTERNET_ERROR
import com.groupd.banquemisrapp.routes.Route.MORE
import com.groupd.banquemisrapp.routes.Route.OTP
import com.groupd.banquemisrapp.routes.Route.OTP_CONNECTED
import com.groupd.banquemisrapp.routes.Route.PROFILE_INFO
import com.groupd.banquemisrapp.routes.Route.SERVER_ERROR
import com.groupd.banquemisrapp.routes.Route.SETTINGS
import com.groupd.banquemisrapp.routes.Route.SIGNIN
import com.groupd.banquemisrapp.routes.Route.TRANSACTIONS
import com.groupd.banquemisrapp.routes.Route.TRANSFER
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.MoreOptionItem
import com.groupd.banquemisrapp.ui.partials.iconNamedVertically
import com.groupd.banquemisrapp.ui.screens.signup.CountryList
import com.groupd.banquemisrapp.ui.theme.BanqueMisrAppTheme
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White
import com.groupd.banquemisrapp.ui.theme.background
import com.groupd.banquemisrapp.ui.theme.background2
import com.groupd.banquemisrapp.ui.theme.whiteBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                && isSelected != SERVER_ERROR) {
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
                                text = "My cards",
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
                            navController = navController, modifier = Modifier.padding(innerPadding)
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
                            navController = navController, modifier = Modifier
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