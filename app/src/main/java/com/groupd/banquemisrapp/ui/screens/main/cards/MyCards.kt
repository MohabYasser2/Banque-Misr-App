package com.groupd.banquemisrapp.ui.screens.main.cards


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.Account
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.theme.Black
import com.groupd.banquemisrapp.ui.theme.Maroon
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.groupd.banquemisrapp.data.Favourite
import com.groupd.banquemisrapp.routes.Route.HOME_SCREEN
import com.groupd.banquemisrapp.ui.theme.White
import androidx.lifecycle.viewmodel.compose.viewModel
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.ChangeDefaultAccountRequest


@Composable
fun MyCardsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User,
    viewModel: MyAccountsViewModel = viewModel()
) {
    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }
    viewModel.getAccounts()

    var defaultAccount by remember { mutableStateOf(user.defaultAccountNumber) }
    var isClickable by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()


    )
    {

        CustomHeader(title = "My Accounts") {
            navController.popBackStack()
        }
        val accounts by viewModel.accounts.collectAsState()
        CardList(accounts, isClickable = isClickable, onSelected = {


            viewModel.makeDefaultAccount(ChangeDefaultAccountRequest(it))
            isClickable = false

        })


        Button(
            onClick = { navController.navigate(Route.ADD_CARD) },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(14.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(
                text = "Add New Account",
                Modifier.padding(12.dp),
                color = Color.White,
                fontSize = 18.sp
            )


        }
        Button(
            onClick = {
                isClickable = true

            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            border = BorderStroke(2.dp, Maroon),
            colors = ButtonDefaults.buttonColors(White.copy(alpha = 0.1f)),
        ) {
            Text(
                text = "Select Default Account",
                Modifier.padding(12.dp),
                color = Maroon,
                fontSize = 18.sp
            )

        }


    }


}


@Composable
fun CardList(
    cards: List<AccountDTO>,
    isClickable: Boolean = false,
    onSelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = Modifier.heightIn(max = 500.dp)) {
        items(cards) { card ->
            CardItem(
                name = card.accountHolderName,
                account = card.accountNumber,
                balance = card.balance.toString(),
                isDefault = card.isDefault,
                isClickable = isClickable,
                onSelected = {
                    onSelected(it)

                }
            )
        }

    }

}


@Composable
fun CardItem(
    name: String,
    account: String,
    isDefault: Boolean,
    balance: String,
    onSelected: (String) -> Unit = {},
    isClickable: Boolean = false,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(enabled = isClickable, onClick = {
                onSelected(account)
            }),
        colors = if (isClickable) CardDefaults.cardColors(containerColor = Black.copy(alpha = 0.2f)) else CardDefaults.cardColors(
            containerColor = Maroon.copy(alpha = 0.2f)
        ),

        ) {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { /*TODO*/ },
                Modifier
                    .clip(CircleShape)
                    .background(Maroon.copy(alpha = 0.1f)),
            ) {


                Icon(
                    painter = painterResource(id = R.drawable.ic_bank),
                    contentDescription = "Bank Icon",
                    Modifier
                        .size(36.dp)

                )
            }

            Column(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight(500),
                    fontSize = 22.sp,
                    modifier = modifier.padding(bottom = 12.dp)
                )
                Text(text = account, color = Black.copy(alpha = 0.5f))
                Text(text = "Balance: $balance", color = Color.Green)


            }
            if (isDefault) {
                Column(
                    //modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Card(
                        modifier = Modifier.wrapContentSize(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                    ) {
                        Text(
                            text = "Default",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }


        }
    }


}


@Preview
@Composable
private fun MyCardsScreenPreview() {

}