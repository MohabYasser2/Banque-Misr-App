package com.groupd.banquemisrapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.MoreOptionItem
import com.groupd.banquemisrapp.ui.partials.iconNamedVertically
import com.groupd.banquemisrapp.ui.theme.BanqueMisrAppTheme
import com.groupd.banquemisrapp.ui.theme.White
import com.groupd.banquemisrapp.ui.theme.background
import com.groupd.banquemisrapp.ui.theme.background2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Scaffold(
                bottomBar = {
                    var isSelected by remember { mutableStateOf("More") }


                    Card(
                        colors = CardDefaults.cardColors(White),
                        elevation = CardDefaults.elevatedCardElevation(0.dp),

                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    ) {


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly

                        ) {

                            iconNamedVertically(
                                onClick = {
                                    isSelected = "Home"
                                },
                                imageRes = painterResource(id = R.drawable.ic_nav_home),
                                text = "Home",
                                isSelected = isSelected == "Home"
                            )
                            iconNamedVertically(
                                onClick = {
                                    isSelected = "Transfer"
                                },
                                imageRes = painterResource(id = R.drawable.ic_nav_transfer),
                                text = "Transfer",
                                isSelected = isSelected == "Transfer"
                            )
                            iconNamedVertically(
                                onClick = {
                                    isSelected = "Transactions"
                                },
                                imageRes = painterResource(id = R.drawable.ic_nav_transactions),
                                text = "Transactions",
                                isSelected = isSelected == "Transactions"
                            )
                            iconNamedVertically(
                                onClick = {
                                    isSelected = "My cards"
                                },
                                imageRes = painterResource(id = R.drawable.ic_nav_cards),
                                text = "My cards",
                                isSelected = isSelected == "My cards"
                            )
                            iconNamedVertically(
                                onClick = {
                                    isSelected = "More"
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
                MoreScreen()
            }
        }

    }
}

@Composable
fun TransferScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background2),


        ) {

    }
}

@Composable
fun MoreScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background

            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeader(title = "More") {}
        MoreOptionItem(imageRes = painterResource(id = R.drawable.ic_website), option = "Transfer From Website")
        HorizontalDivider()
        MoreOptionItem(imageRes = painterResource(id = R.drawable.ic_favourite), option = "Favourites")
        HorizontalDivider()
        MoreOptionItem(imageRes = painterResource(id = R.drawable.ic_profile), option = "Profile")
        HorizontalDivider()
        MoreOptionItem(imageRes = painterResource(id = R.drawable.ic_help), option = "Help")
        HorizontalDivider()
        MoreOptionItem(imageRes = painterResource(id = R.drawable.ic_logout), option = "Logout", isArrow = false)
        HorizontalDivider()

    }

}

@Preview (showBackground = true)
@Composable
private fun Preview() {
    MoreScreen()
}