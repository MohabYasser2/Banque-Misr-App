package com.groupd.banquemisrapp.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.routes.Route.PROFILE
import com.groupd.banquemisrapp.ui.partials.iconNamedVertically
import com.groupd.banquemisrapp.ui.theme.Gold
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White

private const val s = "My Accounts"

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User,
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val balance by viewModel.balance.collectAsState()

    // Trigger the API call
    LaunchedEffect(Unit) {
        viewModel.getBalance()
    }


    Column(
        modifier = modifier
            .fillMaxSize(),


        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 16.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Maroon.copy(alpha = 0.1f)),
                onClick = { navController.navigate(PROFILE) },
            ) {
                val names = user.fullName.split(" ")
                val intials = names.joinToString("") { it.first().toString() }

                Text(
                    text = intials,
                    fontSize = 24.sp,
                    color = Maroon.copy(alpha = 0.6f),
                    fontWeight = FontWeight(500)
                )


            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {


                Text(
                    text = "Welcome Back,",
                    fontSize = 18.sp,
                    color = Maroon,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = user.fullName,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(500)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_bell_2x),
                contentDescription = "bell icon",
                Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Route.NOTIFICATIONS) }
            )
        }
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Maroon),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        )
        {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start


            ) {
                Text(
                    text = "Current Balance",
                    fontSize = 24.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    fontWeight = FontWeight(350)
                )
                Text(
                    text = balance?.accounts?.firstOrNull()?.balance?.toString() ?: "0",
                    fontSize = 32.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight(500)
                )
            }
        }


        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(White),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        )
        {
            Column {
                Text(
                    text = "Services",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(16.dp)

                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    iconNamedVertically(
                        onClick = {
                            navController.navigate(Route.TRANSFER)
                        },
                        imageRes = painterResource(id = R.drawable.ic_transfer_3x),
                        text = "Transfer",
                        imageSize = 44.dp,
                        fontSize = 14.sp,
                        tint = Gold,
                        textColor = Color.Black

                    )
                    iconNamedVertically(
                        onClick = {
                            navController.navigate(Route.TRANSACTIONS)

                        },
                        imageRes = painterResource(id = R.drawable.ic_history_3x),
                        text = "Transactions",
                        imageSize = 44.dp,
                        fontSize = 14.sp,
                        tint = Gold,
                        textColor = Color.Black

                    )
                    iconNamedVertically(
                        onClick = {
                            navController.navigate(Route.CARDS)

                        },
                        imageRes = painterResource(id = R.drawable.ic_card_3x),
                        text = "My Accounts",
                        imageSize = 44.dp,
                        fontSize = 14.sp,
                        tint = Gold,
                        textColor = Color.Black

                    )
                    iconNamedVertically(
                        onClick = {
                            navController.navigate(Route.PROFILE)

                        },
                        imageRes = painterResource(id = R.drawable.ic_account_3x),
                        text = "Account",
                        imageSize = 44.dp,
                        fontSize = 14.sp,
                        tint = Gold,
                        textColor = Color.Black


                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent transactions",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight(500),
            )
            Text(
                text = "View All",
                fontSize = 20.sp,
                color = Color.Black.copy(alpha = 0.5f),
                fontWeight = FontWeight(500),
                modifier = Modifier.clickable { navController.navigate(Route.TRANSACTIONS) }
            )
        }

        val scrollableState = rememberScrollState()



        Card(

            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()


        ) {

            LazyColumn(

            ) {


                items(user.transactions.subList(0, 3)) { transaction ->
                    TransactionItem(
                        transaction.accountName,
                        transaction.details,
                        transaction.amount,
                        painterResource(id = R.drawable.visa),
                        onClick = {}
                    )
                    if (transaction != user.transactions[2])
                        HorizontalDivider()


                }
            }
        }
    }
}


//TODO: add recent transactions


@Composable
fun TransactionItem(
    name: String,
    details: String,
    amount: String,
    iconRes: Painter,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
        //.padding(12.dp)
        , elevation = CardDefaults.elevatedCardElevation(40.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDAC7CA))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Image(
                        painter = iconRes,
                        contentDescription = "Transaction Icon",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                    // Icon(painter = iconRes, contentDescription = "Transaction Icon")
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(
                    text = details,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

            }
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = amount,
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                color = Maroon,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {


}