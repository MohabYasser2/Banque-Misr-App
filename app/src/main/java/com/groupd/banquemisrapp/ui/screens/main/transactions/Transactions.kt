package com.groupd.banquemisrapp.ui.screens.main.transactions

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.Transaction
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route.TRANSACTION_DETAILS
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.theme.Green
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.Red
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.data.TransactionDTO
import com.groupd.banquemisrapp.routes.Route


@Composable
fun TransactionsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User,
    viewModel: TransactionsViewModel = viewModel()
) {
    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }
    viewModel.fetchTransactions()
    val transactions by viewModel.transactions.collectAsState()
    val hasError by viewModel.hasError.collectAsState()


    Log.d("TAG", "TransactionsScreen: $transactions")

    Column(
        modifier = modifier
            .fillMaxSize()
        //.padding(16.dp)


    ) {
        CustomHeader(title = "Transactions") {
            navController.popBackStack()
        }
        Text(
            text = "Your Last Transactions",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = Color.Black,
            //fontSize = 20.sp,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        TransactionList(transactions = transactions, navController = navController)

    }
}


@Composable
fun TransactionList(
    transactions: List<TransactionDTO>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    LazyColumn(modifier = modifier) {
        items(transactions) { transaction ->
            TransactionItem(
                transaction.recipientAccount.accountHolderName,
                transaction.recipientAccount.accountNumber,
                "EGP" + transaction.amount.toString(),
                painterResource(id = R.drawable.visa),
                transaction.successful, // Assuming you want to highlight all items; adjust as needed
                onClick = { navController.navigate("$TRANSACTION_DETAILS/${transaction.transactionId}") }  // Pass the transaction to the onClick callback
            )
        }
    }


}


@Composable
fun TransactionItem(
    name: String,
    details: String,
    amount: String,
    //status: String,
    iconRes: Painter,
    isSuccessfulTransaction: Boolean,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
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
                Text(text = details, color = Color.Gray)

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.End) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = "forward arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(bottom = 10.dp)
                        .alpha(0.5f)
                        .clickable {}
                        .align(Alignment.End)
                )
                if (isSuccessfulTransaction) {
                    Card(
                        modifier = Modifier.wrapContentSize(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = 0.15f))
                    ) {
                        Text(
                            text = "Successful",
                            color = Green,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                } else {
                    Card(
                        modifier = Modifier.wrapContentSize(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.15f))
                    ) {
                        Text(
                            text = "Failed",
                            color = Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
        Row {
            Text(
                text = amount,
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                color = Maroon,
                modifier = Modifier.padding(start = 80.dp, bottom = 8.dp)
            )
        }

    }
}


@Composable
fun TransactionDetailsScreen(
    transaction: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User
) {

    val amount = user.transactions[transaction - 1].amount
    val from = user.fullName
    val fromAccount = user.defaultAccountNumber
    val to = user.transactions[transaction - 1].accountName
    val toAccount = user.transactions[transaction - 1].accountNumber
    val reference = "$transaction"
    val status = user.transactions[transaction - 1].successful
    val date = "20 Jul 2024 7:50 PM"


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val header = if (status) "Successful Transaction" else "Failed Transaction"

        CustomHeader(title = header) {
            navController.popBackStack()
        }
        // Status Icon
        if (status) {
            Icon(
                painter = painterResource(id = R.drawable.ic_success), // Replace with your icon resource
                contentDescription = "Success Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(120.dp)
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_x), // Replace with your icon resource
                contentDescription = "failed Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = amount,
            fontWeight = FontWeight(500),
            fontSize = 28.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Transfer amount",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Text(
            text = "Send money",
            style = MaterialTheme.typography.bodyLarge,
            color = Maroon
        )
        Spacer(modifier = Modifier.height(16.dp))
        TransactionDetails(
            from,
            fromAccount,
            to,
            toAccount,
            amount,
            status,
            reference,
            modifier = modifier
        )
        /*TransactionInfoSection(
            from = from,
            to = to,
            transferAmount = transferAmount,
            reference = reference,
            date = date
        )*/
    }
}


@Composable
fun TransactionDetails(
    from: String,
    fromAccount: String,
    to: String,
    toAccount: String,
    amount: String,
    status: Boolean,
    ref: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // For scrolling if content overflows
    ) {
        // From section
        TransactionDetailCard(
            label = "From",
            name = from,
            account = fromAccount,
            icon = painterResource(id = R.drawable.ic_bank)
        )

        // Divider with a check mark in the center


        // To section
        TransactionDetailCard(
            label = "To",
            name = to,
            account = toAccount,
            icon = painterResource(id = R.drawable.ic_bank)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                //.padding(vertical = 8.dp)
                .offset(y = (-140).dp)
        ) {
            //HorizontalDivider()
            if (status) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = Color.White,
                    modifier = Modifier
                        .background(Color(0xFFb08645), shape = CircleShape)
                        .size(42.dp)
                        .padding(4.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "uncheck",
                    tint = Color.White,
                    modifier = Modifier
                        .background(Color(0xFFb08645), shape = CircleShape)
                        .size(42.dp)
                        .padding(4.dp)
                )
            }

        }

        //Spacer(modifier = Modifier.height(16.dp))

        TransactionDetailItem(amount, ref)

    }
}

@Composable
fun TransactionDetailCard(label: String, name: String, account: String, icon: Painter) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color(0xFFDAC7CA))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = "Transaction Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                    // Icon(painter = iconRes, contentDescription = "Transaction Icon")
                }

            }


            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(color = Maroon),
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)

                )
                Text(
                    text = account,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun TransactionDetailItem(amount: String, id: String) {

    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(Color(0xFFDAC7CA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Transfer amount",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = amount,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Reference",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = id,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Date",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "20 Jul 2024 7:50 PM",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun TransactionsScreenPreview() {

}