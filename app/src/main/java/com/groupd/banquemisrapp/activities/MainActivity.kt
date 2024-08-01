package com.groupd.banquemisrapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

            val navController = rememberNavController()
            Scaffold(bottomBar = {


                var isSelected by remember { mutableStateOf("Home") }
                val context = LocalContext.current



                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically

                    ) {


                        iconNamedVertically(
                            onClick = {
                                if (isSelected != "Home") {
                                    navController.navigate(Route.HOME_SCREEN)
                                    isSelected = "Home"
                                }

                            },
                            imageRes = painterResource(id = R.drawable.ic_nav_home),
                            text = "Home",
                            isSelected = isSelected == "Home"
                        )
                        iconNamedVertically(
                            onClick = {
                                if (isSelected != "Transfer") {
                                    isSelected = "Transfer"

                                    navController.navigate(Route.TRANSFER)
                                }
                            },
                            imageRes = painterResource(id = R.drawable.ic_nav_transfer),
                            text = "Transfer",
                            isSelected = isSelected == "Transfer"
                        )
                        iconNamedVertically(
                            onClick = {
                                if (isSelected != "Transactions") {
                                    isSelected = "Transactions"

                                    navController.navigate(Route.TRANSACTIONS)
                                }
                            },
                            imageRes = painterResource(id = R.drawable.ic_nav_transactions),
                            text = "Transactions",
                            isSelected = isSelected == "Transactions"
                        )
                        iconNamedVertically(
                            onClick = {
                                if (isSelected != "My cards") {

                                    isSelected = "My cards"

                                    navController.navigate(Route.CARDS)
                                }
                            },
                            imageRes = painterResource(id = R.drawable.ic_nav_cards),
                            text = "My cards",
                            isSelected = isSelected == "My cards"
                        )
                        iconNamedVertically(
                            onClick = {
                                if (isSelected != "More") {
                                    isSelected = "More"
                                    navController.navigate(Route.MORE)
                                }
                            },
                            imageRes = painterResource(id = R.drawable.ic_nav_more),
                            text = "More",
                            isSelected = isSelected == "More"
                        )


                    }

                }
            }

            )

            { innerPadding ->
                MainNavHost(navController = navController)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {

}