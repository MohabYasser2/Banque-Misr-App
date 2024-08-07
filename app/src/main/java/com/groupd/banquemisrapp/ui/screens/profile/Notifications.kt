package com.groupd.banquemisrapp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route.TRANSACTION_DETAILS
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.screens.main.home.TransactionItem
import com.groupd.banquemisrapp.ui.screens.main.transactions.TransactionsViewModel
import com.groupd.banquemisrapp.ui.screens.main.transactions.formatDateString
import com.groupd.banquemisrapp.ui.theme.Maroon

@Composable
fun NotificationScreen(
    navController: NavController, modifier: Modifier = Modifier, user: User,
    TransactionsViewModel: TransactionsViewModel = viewModel()
) {


    TransactionsViewModel.fetchTransactions()
    val transactions by TransactionsViewModel.transactions.collectAsState()


    Column(
        modifier = modifier
            .fillMaxSize()
        //.padding(16.dp)

    ) {
        CustomHeader(title = "Notifications", onBackClick = { navController.popBackStack() })

    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White.copy(alpha = 0f))
            .padding(top = 60.dp),
        contentPadding = PaddingValues(
            horizontal = 8.dp,
        ),

        ) {
        items(transactions.reversed()) { transaction ->
            var state =
                if (transaction.senderAccount.accountHolderName == user.fullName) "Sent" else "Received"
            var fromTo = if (state == "Sent") "To" else "From"
            NotificationItem(
                type = state,
                details = "You have $state ${transaction.amount} EGP $fromTo ${transaction.recipientUser.username}",
                date = formatDateString(transaction.transactionDate),
                iconRes = if (state == "Received") painterResource(id = R.drawable.ic_recieved) else painterResource(
                    id = R.drawable.ic_sent
                )
            ) {

            }

        }

    }
}



@Composable
fun NotificationItem(
    type: String,
    details: String,
    date: String,
    iconRes: Painter,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),
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
                    Icon(
                        painter = iconRes,
                        contentDescription = "Notification Icon",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        tint = Maroon
                    )
                    // Icon(painter = iconRes, contentDescription = "Transaction Icon")
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = type + " Transaction",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = details,
                    color = Color.Black.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = date,
                    color = Color.Black.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 8.dp)
                )


            }


        }


    }

}


@Preview(showBackground = true)
@Composable
private fun NotificationScreenPreview() {

}
