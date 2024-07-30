package com.groupd.banquemisrapp.ui.screens.signup

import android.annotation.SuppressLint
import android.text.format.Formatter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


@Composable
fun SignUpFirst(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var secondPassword by remember { mutableStateOf("") }


        Text(
            text = "Sign Up",
            fontSize = 20.sp,
            modifier = modifier.padding(20.dp),
            fontWeight = FontWeight(400)
        )
        Text(
            text = "Speedo Transfer",
            fontSize = 24.sp,
            modifier = modifier.padding(40.dp),
            fontWeight = FontWeight(500)
        )
        namedField(
            text = "Full name",
            message = "Enter your full name",
            value = fullName,
            onValueChange = { fullName = it },
            imageRes = painterResource(id = R.drawable.ic_profile),
            trailingIconOn = true
        )
        namedField(
            text = "Email",
            message = "Enter your email address",
            value = email,
            onValueChange = { email = it },
            imageRes = painterResource(id = R.drawable.ic_email),
            trailingIconOn = true
        )
        namedField(text = "Password",
            message = "Enter your password",
            value = password,
            isPassord = true,
            onValueChange = { password = it })/*namedField(text = "Re-enter Password",
            message = "Re- enter your password",
            value = secondPassword,
            isPassord = true,
            onValueChange = { secondPassword = it })*/

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Sign Up", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
        Row {


            Text(text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.5f))) {
                    append("Already have an account? ")
                }

            },
                modifier = Modifier.align(Alignment.CenterVertically),fontSize = 16.sp)
            TextButton(onClick = {/*Navigate to sign in*/ }) {
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Maroon, textDecoration = Underline)) {
                        append("Sign In")
                    }
                },fontSize = 16.sp)

            }
        }



    }

}


@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpSecond(modifier: Modifier = Modifier) {
    val sheetStateOne = rememberModalBottomSheetState()
    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Text(
            text = "Speedo Transfer",
            fontSize = 24.sp,
            modifier = modifier
                .padding(40.dp)
                .padding(top = 120.dp),
            fontWeight = FontWeight(500)
        )
        Text(
            text = "Welcome to Banque Misr!",
            fontSize = 24.sp,
            modifier = modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
            fontWeight = FontWeight(500)
        )
        Text(
            text = "Lets Complete your Profile",
            fontSize = 18.sp,
            modifier = modifier.padding(20.dp),
            fontWeight = FontWeight(350)
        )
        namedField(text = "Country",
            message = "Select your Country",
            value = selectedCountry,
            onClick = {

                isSheetOneOpen = !isSheetOneOpen

            },
            onValueChange = { },
            imageRes = painterResource(id = R.drawable.ic_down),
            trailingIconOn = true,
            isReadOnly = true
        )
        namedField(
            text = "Date Of Birth",
            message = "DD/MM/YYYY",
            value = selectedDate,
            onClick = {
                openDialog.value = true
            },
            onValueChange = { },
            imageRes = painterResource(id = R.drawable.ic_calendar),
            trailingIconOn = true,
            isReadOnly = true,

            )

        if (isSheetOneOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOneOpen = !isSheetOneOpen },
                sheetState = sheetStateOne,

                ) {
                CountryList(currentCountry = selectedCountry, onCountrySelected = {
                    isSheetOneOpen = !isSheetOneOpen
                    selectedCountry = it
                })
            }
        }
        if (openDialog.value) {
            DatePickerDialog(onDismissRequest = { openDialog.value = false }, confirmButton = {
                Text(
                    text = "Confirm",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            openDialog.value = false
                            val calendar = Calendar.getInstance()
                            calendar.timeInMillis = datePickerState.selectedDateMillis!!
                            selectedDate = formatter.format(calendar.time)

                        },
                    color = Maroon,

                    )
            }) {
                DatePicker(state = datePickerState)
            }
        }





        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Continue", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
    }

}


@Composable

fun CountryList(
    currentCountry: String = "", onCountrySelected: (String) -> Unit = {}
) {
    val selectedCountry = remember { mutableStateOf("") }
    val countries = listOf(
        Pair("Egypt", "🇪🇬"),
        Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
        Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
        Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),

        Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
        Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
        Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
        Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
        Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
        Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
        Pair("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
        Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
        Pair("Spain", "\uD83C\uDDEA\uD83C\uDDF8"),
        Pair("Italy", "\uD83C\uDDEE\uD83C\uDDF9"),

        )

    LazyColumn(
        modifier = Modifier.heightIn(max = 300.dp)
    ) {
        items(countries) { (country, flag) ->

            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .clickable {
                        selectedCountry.value = country
                        onCountrySelected(country)

                    },
                colors = if (currentCountry == country) CardDefaults.cardColors(Maroon.copy(alpha = 0.2f)) else CardDefaults.cardColors(
                    Color.White
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp, horizontal = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Text(

                            text = flag, fontSize = 24.sp, modifier = Modifier.padding(end = 16.dp)
                        )
                        Text(
                            text = country, fontWeight = FontWeight.Medium
                        )
                    }
                    if (currentCountry == country) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Maroon,
                            modifier = Modifier.padding(start = 8.dp)

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
    SignUpFirst()
}

