package com.groupd.banquemisrapp.ui.screens.signup

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.routes.Route.SIGNIN
import com.groupd.banquemisrapp.routes.Route.SIGNUP2
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Calendar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.api.LoginAPIService
import com.groupd.banquemisrapp.api.UserAPIService
import com.groupd.banquemisrapp.data.RegisterRequest
import com.groupd.banquemisrapp.data.UserDTO
import com.groupd.banquemisrapp.routes.Route
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun SignUpFirst(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var secondPassword by remember { mutableStateOf("") }

        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf("") }
        var secondPasswordError by remember { mutableStateOf("") }
        var fullNameError by remember { mutableStateOf("") }

        val isButtonEnabled by remember {
            derivedStateOf {
                fullName.isNotEmpty() &&
                        email.isNotEmpty() &&
                        password.isNotEmpty() &&
                        secondPassword.isNotEmpty() &&
                        emailError.isEmpty() &&
                        passwordError.isEmpty() &&
                        secondPasswordError.isEmpty() &&
                        password == secondPassword &&
                        fullNameError.isEmpty()
            }
        }

        fun validateEmail(email: String) {
            emailError =
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) "" else "Invalid email format"
        }

        // Password strength validation
        fun validatePassword(password: String) {
            passwordError = when {
                password.length < 6 -> "Password must be at least 6 characters"
                !password.any { it.isUpperCase() } -> "Password must contain at least one capital letter"
                !password.any { it.isLowerCase() } -> "Password must contain at least one small letter"
                !password.any { it in "!@#$%^&*()-_+=<>?/,.|\\~`" } -> "Password must contain at least one special character"
                else -> ""
            }
        }

        // Confirm password validation
        fun validateSecondPassword(password: String, secondPassword: String) {
            secondPasswordError = if (password == secondPassword) "" else "Passwords do not match"
        }

        fun validateFullName(fullName: String) {
            fullNameError = when {
                fullName.isEmpty() -> "Full name is required"
                fullName.split(" ").size != 2 -> "Full name must contain a first and last name"
                !fullName.split(" ").all { it.isNotEmpty() && it[0].isUpperCase() } -> "Each name must start with a capital letter"
                else -> ""
            }
        }



        // Update values and validate
        fun updateValuesAndValidate(
            fullName1: String,
            email1: String,
            password1: String,
            secondPassword1: String
        ) {
            fullName = fullName1
            email = email1
            password = password1
            secondPassword = secondPassword1

            validateEmail(email)
            validatePassword(password)
            validateSecondPassword(password, secondPassword)
            validateFullName(fullName)
        }

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
            onValueChange = { updateValuesAndValidate(it, email, password, secondPassword) },
            imageRes = painterResource(id = R.drawable.ic_profile),
            trailingIconOn = true,
            error = fullNameError
        )
        namedField(
            text = "Email",
            message = "Enter your email address",
            value = email,
            onValueChange = { updateValuesAndValidate(fullName, it, password, secondPassword) },
            imageRes = painterResource(id = R.drawable.ic_email),
            trailingIconOn = true,
            error = emailError
        )
        namedField(
            text = "Password",
            message = "Enter your password",
            value = password,
            isPassord = true,
            onValueChange = { updateValuesAndValidate(fullName, email, it, secondPassword) },
            error = passwordError
        )

        namedField(
            text = "Re-enter Password",
            message = "Re- enter your password",
            value = secondPassword,
            isPassord = true,
            onValueChange = { updateValuesAndValidate(fullName, email, password, it) },
            error = secondPasswordError
        )


        Button(
            onClick = {
                navController.navigate("$SIGNUP2/${fullName}/${email}/${password}")
            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
            enabled = isButtonEnabled
        ) {
            Text(text = "Sign Up", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
        Row {


            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.5f))) {
                        append("Already have an account? ")
                    }

                },
                modifier = Modifier.align(Alignment.CenterVertically), fontSize = 16.sp
            )

            TextButton(onClick = { navController.navigate(SIGNIN) }) {
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Maroon, textDecoration = Underline)) {
                        append("Sign In")
                    }
                }, fontSize = 16.sp)

            }
        }


    }

}


@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpSecond(
    fullName: String,
    email: String,
    password: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()
) {
    val sheetStateOne = rememberModalBottomSheetState()
    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var dateOfBirthError by remember { mutableStateOf("") }


        val isButtonEnabled by remember {
            derivedStateOf {
                selectedDate.isNotEmpty() && selectedCountry.isNotEmpty() && dateOfBirthError.isEmpty()
            }
        }

        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        fun validateDateOfBirth(dateOfBirth: String) {
            try {
                val parsedDate = LocalDate.parse(dateOfBirth, dateFormatter)
                val currentDate = LocalDate.now()
                val minimumAgeDate = currentDate.minusYears(18)

                if (parsedDate.isAfter(currentDate)) {
                    dateOfBirthError = "Date of birth cannot be in the future."
                } else if (parsedDate.isAfter(minimumAgeDate)) {
                    dateOfBirthError = "You must be at least 18 years old."
                } else {
                    dateOfBirthError = ""
                }
            } catch (e: DateTimeParseException) {
                dateOfBirthError = "Invalid date format. Please use yyyy-MM-dd."
            }
        }


        fun updateValuesAndValidate(
            dateOfBirth: String
        ) {
            selectedDate = dateOfBirth
            validateDateOfBirth(dateOfBirth)
        }

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
        namedField(
            text = "Country",
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
            text = "Number",
            message = "ex : +201234567891",
            value = number,
            error = if (number.length < 11) "Invalid number" else if (!number.startsWith("+")) "Number Should Start With Country Code" else "",

            onValueChange = {
                number = it
            },
            imageRes = painterResource(id = R.drawable.ic_down),
            trailingIconOn = false,
            isReadOnly = false
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
            error = dateOfBirthError

        )

        if (isSheetOneOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOneOpen = !isSheetOneOpen },
                sheetState = sheetStateOne,
                containerColor = White

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
                            updateValuesAndValidate(selectedDate)


                        },
                    color = Maroon,

                    )
            }) {
                DatePicker(state = datePickerState)
            }
        }


        // val hasError by viewModel.hasError.collectAsState()
        //val loginResponse by viewModel. .collectAsState()


        var signupResponse by remember { mutableStateOf<UserDTO?>(null) }


        Button(
            onClick = {

                val registerRequest = RegisterRequest(
                    username = fullName,
                    email = email,
                    password = password,
                    phoneNumber = number,
                    country = selectedCountry,
                    gender = "MALE",
                    dateOfBirth = selectedDate,
                )

                viewModel.viewModelScope.launch {
                    try {
                        Log.d("TAG", "Logging in: $registerRequest")
                        signupResponse = LoginAPIService.loginAPI.register(registerRequest)
                        Log.d("TAG", "Logging in: $signupResponse")
                        navController.navigate(SIGNIN)


                    } catch (e: Exception) {
                        Log.d("TAG", "Logging in Error: ${e.message}")
                    }
                }

            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
            enabled = isButtonEnabled
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
        Pair("EGYPT", "🇪🇬"),
        Pair("USA", "\uD83C\uDDFA\uD83C\uDDF8"),
        Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
        Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),

        Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
        Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
        Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
        Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
        Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
        Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
        Pair("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
        Pair("UK", "\uD83C\uDDEC\uD83C\uDDE7"),
        Pair("Spain", "\uD83C\uDDEA\uD83C\uDDF8"),
        Pair("Italy", "\uD83C\uDDEE\uD83C\uDDF9"),
        // i added them to test the scrolling
        Pair("Mexico", "🇲🇽"),
        Pair("Argentina", "🇦🇷"),
        Pair("South Korea", "🇰🇷"),
        Pair("KSA", "🇸🇦"),
        Pair("South Africa", "🇿🇦"),

        )

    LazyColumn(
        modifier = Modifier.heightIn(max = 400.dp)
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
    SignUpFirst(navController = NavController(LocalContext.current))
}

