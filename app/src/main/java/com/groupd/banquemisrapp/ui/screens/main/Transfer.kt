package com.groupd.banquemisrapp.ui.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route.HOME_SCREEN
import com.groupd.banquemisrapp.routes.Route.TRANSFER_THREE
import com.groupd.banquemisrapp.routes.Route.TRANSFER_TWO
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.FavouriteItem
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Black
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White
import com.groupd.banquemisrapp.ui.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreenOne(navController: NavController, modifier: Modifier = Modifier, user: User) {
    val scrollableState = rememberScrollState()
    var sentValue by remember { mutableStateOf("") }
    var receivedValue by remember { mutableStateOf("") }
    val sheetStateOne = rememberModalBottomSheetState()
    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var tempName by remember { mutableStateOf("") }
    var tempAccount by remember { mutableStateOf("") }



    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollableState)
    ) {
        CustomHeader(title = "Transfer") {
            navController.popBackStack()
        }

        Column (
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            ProgressBar(mode = 1)
        }


        Text(
            text = "How much are you sending?",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Choose Currency?",
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight(400)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),

            ) {
            Column {


                Text(
                    text = "1 USD = 48.4220 EGP",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 8.dp)
                        .padding(horizontal = 8.dp),
                    fontWeight = FontWeight(450)
                )
                Text(
                    text = "Rate guaranteed (2h)",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(horizontal = 8.dp),
                    fontWeight = FontWeight(500),
                    color = Black.copy(0.5f)
                )
                Text(
                    text = "You Send",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight(400)
                )
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CurrencyDropdown()
                    OutlinedTextField(
                        value = sentValue,
                        onValueChange = { sentValue = it },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(16.dp))
                Text(
                    text = "You Send", fontSize = 20.sp, modifier = Modifier


                        .padding(horizontal = 16.dp), fontWeight = FontWeight(400)
                )
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CurrencyDropdown()
                    OutlinedTextField(
                        value = receivedValue,
                        onValueChange = { receivedValue = it },
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                    )
                }
            }

        }

        Row(
            modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recipient Information",
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.weight(1f),
                color = Black.copy(0.6f)
            )
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                isSheetOneOpen = !isSheetOneOpen
            }

            ) {


                Icon(
                    painter = painterResource(id = R.drawable.ic_favourite),
                    contentDescription = null,
                    tint = Maroon,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Favourite",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Maroon,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    tint = Maroon,
                    modifier = Modifier.size(14.dp)
                )

            }

        }
        namedField(
            text = "Recipient Name",
            message = "Enter Cardholder Name",
            value = tempName,
            onValueChange = { tempName = it }
        )
        namedField(
            text = "Recipient Account",
            message = "Enter Cardholder Account",
            value = tempAccount,
            onValueChange = { tempAccount = it }

        )

        Button(
            onClick = {
                navController.navigate(TRANSFER_TWO)

            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(
                text = "Continue",
                Modifier.padding(12.dp),
                color = Color.White,
                fontSize = 18.sp
            )

        }
    }
    if (isSheetOneOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOneOpen = !isSheetOneOpen },
            sheetState = sheetStateOne,
            containerColor = White

        ) {
            val favourites = remember {
                mutableStateListOf(

                    Pair("Asmaa Dosuky", "Account xxxx7890"),
                    Pair("Ahmed Mohamed", "Account xxxx71234"),
                    Pair("Mohab Yasser", "Account xxxx6969"),
                    Pair("Mohamed Magdy", "Account xxxx4200"),
                    Pair("Asmaa Dosuky", "Account xxxx7890"),
                    Pair("Ahmed Mohamed", "Account xxxx71234"),
                    Pair("Mohab Yasser", "Account xxxx6969"),
                    Pair("Mohamed Magdy", "Account xxxx4200"),

                    )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favourite),
                    contentDescription = null,
                    tint = Maroon,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Favourite",
                    fontSize = 32.sp,
                    fontWeight = FontWeight(200),
                    color = Maroon,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            LazyColumn(
                modifier = Modifier.heightIn(max = 400.dp)
            ) {
                items(favourites.size) { index ->
                    val (name, account) = favourites[index]
                    var tempName by remember { mutableStateOf("") }
                    var tempAccount by remember { mutableStateOf("") }
                    var selectedItemIndex by remember { mutableStateOf(0) }
                    FavouriteItem(
                        name = name,
                        account = account,
                    )
                }
            }
        }
    }
}

@Composable
fun TransferScreenTwo(navController: NavController, modifier: Modifier = Modifier, user: User) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeader(title = "Transfer") {
            navController.popBackStack()
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp).padding(bottom = 64.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            ProgressBar(mode = 2)
            Text(
                text = "1000 USD",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Transfer amount",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight(400),
                color = Black.copy(0.5f)
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(text = "Total amount", fontWeight = FontWeight(500), fontSize = 18.sp)
                Text(
                    text = "48,422 EGP",
                    fontWeight = FontWeight(450),
                    fontSize = 18.sp,
                    color = Black.copy(0.5f)
                )

            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            // From section
            TransactionDetailCard(
                label = "From",
                name = "Asmaa Dosuky",
                account = "Account xxxx7890",
                icon = painterResource(id = R.drawable.ic_bank)
            )

            // Divider with a check mark in the center


            // To section
            TransactionDetailCard(
                label = "To",
                name = "Jonathon Smith",
                account = "Account xxxx7890",
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_transfer_arrows),
                    contentDescription = "Check",
                    tint = Color.White,
                    modifier = Modifier
                        .background(Color(0xFFb08645), shape = CircleShape)
                        .size(42.dp)
                        .padding(8.dp)
                )

            }
            Button(
                onClick = {
                    navController.navigate(TRANSFER_THREE)

                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(),


                colors = ButtonDefaults.buttonColors(Maroon),
            ) {
                Text(
                    text = "Continue",
                    Modifier.padding(12.dp),
                    color = Color.White,
                    fontSize = 18.sp
                )

            }
            Button(
                onClick = {
                    navController.popBackStack()

                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),

border = BorderStroke(2.dp, Maroon),
                colors = ButtonDefaults.buttonColors(White.copy(alpha = 0.1f)),
            ) {
                Text(
                    text = "Previous",
                    Modifier.padding(12.dp),
                    color = Maroon,
                    fontSize = 18.sp
                )

            }
        }

    }
}

@Composable
fun TransferScreenThree(navController: NavController, modifier: Modifier = Modifier, user: User) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeader(title = "Transfer") {
            navController.popBackStack()
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp).padding(bottom = 64.dp)
                .verticalScroll(rememberScrollState()) // For scrolling if content overflows
        ) {
            ProgressBar(mode = 3)

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(painter = painterResource(id = R.drawable.check_mark), contentDescription ="" )
                Text(
                    text = "Your transfer was successful",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            // From section
            TransactionDetailCard(
                label = "From",
                name = "Asmaa Dosuky",
                account = "Account xxxx7890",
                icon = painterResource(id = R.drawable.ic_bank)
            )

            // Divider with a check mark in the center


            // To section
            TransactionDetailCard(
                label = "To",
                name = "Jonathon Smith",
                account = "Account xxxx7890",
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
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = Color.White,
                    modifier = Modifier
                        .background(Color(0xFFb08645), shape = CircleShape)
                        .size(42.dp)
                        .padding(4.dp)
                )

            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)

                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(text = "Total amount", fontWeight = FontWeight(500), fontSize = 18.sp, color = Black.copy(0.5f))
                Text(
                    text = "48,422 EGP",
                    fontWeight = FontWeight(450),
                    fontSize = 18.sp,
                    color = Black.copy(0.5f)
                )

            }
            HorizontalDivider(modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp))
            Button(
                onClick = {
                    navController.navigate(HOME_SCREEN)

                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(),


                colors = ButtonDefaults.buttonColors(Maroon),
            ) {
                Text(
                    text = "Back to Home",
                    Modifier.padding(12.dp),
                    color = Color.White,
                    fontSize = 18.sp
                )

            }

            Button(
                onClick = {
                    navController.popBackStack()


                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),

                border = BorderStroke(2.dp, Maroon),
                colors = ButtonDefaults.buttonColors(White.copy(alpha = 0.1f)),
            ) {
                Text(
                    text = "Previous",
                    Modifier.padding(12.dp),
                    color = Maroon,
                    fontSize = 18.sp
                )

            }

        }

    }
}
@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(Modifier.background(background)) {



    }

}

@Composable
fun ProgressBar(mode: Int, modifier: Modifier = Modifier) {

    val colorOne = Maroon
    val colorTwo = if (mode == 2 || mode == 3) Maroon else Color.Black.copy(alpha = 0.3f)
    val colorThree = if (mode == 3) Maroon else Color.Black.copy(alpha = 0.3f)


    val backColourTwo = if (mode == 2 || mode == 3) White else Color.White.copy(alpha = 0.5f)
    val backColourThree = if (mode == 3) White else Color.White.copy(alpha = 0.5f)

//experimental
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 8.dp),

        ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .padding(16.dp)
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "01", fontSize = 16.sp, color = colorOne, modifier = Modifier.drawBehind {
                drawCircle(
                    color = White, radius = 20.dp.toPx()
                )
                drawCircle(
                    color = colorOne, radius = 20.dp.toPx(), style = Stroke(width = 3.dp.toPx())
                )
            })

            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(0.3f),
                color = colorOne,
                thickness = 1.5.dp
            )

            Text(text = "02", fontSize = 16.sp, color = colorTwo, modifier = Modifier.drawBehind {
                drawCircle(

                    color = backColourTwo, radius = 20.dp.toPx()
                )
                drawCircle(
                    color = colorTwo, radius = 20.dp.toPx(), style = Stroke(width = 3.dp.toPx())
                )
            })


            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(0.7f),
                color = colorThree,
                thickness = 1.5.dp
            )
            Text(text = "03", fontSize = 16.sp, color = colorThree, modifier = Modifier.drawBehind {
                drawCircle(

                    color = backColourThree, radius = 20.dp.toPx()
                )
                drawCircle(
                    color = colorThree,
                    radius = 20.dp.toPx(),
                    style = Stroke(width = 3.dp.toPx())
                )
            })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Amount", fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.weight(0.66f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                text = "Confirmation",
                fontWeight = if (mode == 2 || mode == 3) FontWeight.Bold else FontWeight(350)
            )
            Text(
                text = "Payment", fontWeight = if (mode == 3) FontWeight.Bold else FontWeight(350)
            )

        }
    }
}


data class Currency(val code: String, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyDropdown() {
    val currencies = listOf(
        Pair("EGP", "🇪🇬"), // Egyptian Pound
        Pair("USD", "\uD83C\uDDFA\uD83C\uDDF8"), // US Dollar
        Pair("CAD", "\uD83C\uDDE8\uD83C\uDDE6"), // Canadian Dollar
        Pair("INR", "\uD83C\uDDEE\uD83C\uDDF3"), // Indian Rupee
        Pair("EUR", "\uD83C\uDDE9\uD83C\uDDEA"), // Euro (Germany, France, and others)
        Pair("JPY", "\uD83C\uDDEF\uD83C\uDDF5"), // Japanese Yen
        Pair("CNY", "\uD83C\uDDE8\uD83C\uDDF3"), // Chinese Yuan Renminbi
        Pair("BRL", "\uD83C\uDDE7\uD83C\uDDF7"), // Brazilian Real
        Pair("AUD", "\uD83C\uDDE6\uD83C\uDDFA"), // Australian Dollar
        Pair("RUB", "\uD83C\uDDF7\uD83C\uDDFA"), // Russian Ruble
        Pair("GBP", "\uD83C\uDDEC\uD83C\uDDE7"), // British Pound Sterling
        Pair("EUR", "\uD83C\uDDEA\uD83C\uDDF8"), // Euro (Spain)
        Pair("EUR", "\uD83C\uDDEE\uD83C\uDDF9"), // Euro (Italy)
        Pair("MXN", "🇲🇽"), // Mexican Peso
        Pair("ARS", "🇦🇷"), // Argentine Peso
        Pair("KRW", "🇰🇷"), // South Korean Won
        Pair("SAR", "🇸🇦"), // Saudi Riyal
        Pair("ZAR", "🇿🇦"), // South African Rand
    )


    var expanded by remember { mutableStateOf(false) }
    var selectedCurrency by remember { mutableStateOf(currencies.first()) }




    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        BasicTextField(readOnly = true,
            value = "${selectedCurrency.second} ${selectedCurrency.first}",
            onValueChange = {},

            modifier = Modifier.padding(16.dp),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true)
                ) {
                    Text(
                        text = "${selectedCurrency.second} ${selectedCurrency.first}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(end = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = Maroon

                    )
                    Icon(painter = painterResource(id = R.drawable.ic_down),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { expanded = true })
                }
            })
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {

            currencies.forEach { (first, second) ->
                DropdownMenuItem(text = {
                    Text(
                        "$second $first",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                        color = Maroon
                    )
                }, onClick = {
                    selectedCurrency = Pair(first, second)

                    expanded = false
                })
                HorizontalDivider(modifier = Modifier.padding(16.dp))
            }

        }

    }
}








