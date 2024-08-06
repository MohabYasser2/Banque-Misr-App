package com.groupd.banquemisrapp.ui.screens.profile

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.data.MockData.user
import com.groupd.banquemisrapp.data.UpdateAccountRequest
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.screens.signup.CountryList
import com.groupd.banquemisrapp.ui.theme.Maroon
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User,
    viewModel: ProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }
    val profile by viewModel.balance.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }
    val sheetStateOne = rememberModalBottomSheetState()
    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(profile?.country ?: "") }
    val openDialog = remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(profile?.dateOfBirth ?: "") }
    val datePickerState = rememberDatePickerState()
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    var fullName by remember { mutableStateOf(profile?.username ?: "") }
    var email by remember { mutableStateOf(profile?.email ?: "") }
    var dateOfBirthError by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var fullNameError by remember { mutableStateOf("") }

    val isButtonEnabled by remember {
        derivedStateOf {
            fullName.isNotEmpty() &&
                    email.isNotEmpty() &&
                    emailError.isEmpty() &&
                    fullNameError.isEmpty() &&
                    selectedDate.isNotEmpty() &&
                    selectedCountry.isNotEmpty() &&
                    dateOfBirthError.isEmpty()

        }
    }

    fun validateEmail(email: String) {
        emailError =
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) "" else "Invalid email format"
    }


    fun validateFullName(fullName: String) {
        fullNameError = when {
            fullName.isEmpty() -> "Full name is required"
            fullName.split(" ").size != 2 -> "Full name must contain a first and last name"
            !fullName.split(" ")
                .all { it.isNotEmpty() && it[0].isUpperCase() } -> "Each name must start with a capital letter"

            else -> ""
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


    // Update values and validate
    fun updateValuesAndValidate(
        fullName1: String,
        email1: String,
        dateOfBirth: String
    ) {
        fullName = fullName1
        email = email1
        selectedDate = dateOfBirth

        validateDateOfBirth(dateOfBirth)
        validateEmail(email)
        validateFullName(fullName)
    }



    LaunchedEffect(profile) {
        profile?.let {
            fullName = it.username
            email = it.email
            selectedCountry = it.country
            selectedDate = it.dateOfBirth
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        CustomHeader(title = "Edit Profile") {
            navController.popBackStack()
        }

        namedField(
            text = "Full name",
            message = "Enter your full name",
            value = fullName,
            onValueChange = { updateValuesAndValidate(it, email, selectedDate) },
            imageRes = painterResource(id = R.drawable.ic_profile),
            trailingIconOn = true,
            error = fullNameError
        )
        namedField(
            text = "Email",
            message = "Enter your Email",
            value = email,
            onValueChange = { updateValuesAndValidate(fullName, it, selectedDate) },
            imageRes = painterResource(id = R.drawable.ic_email),
            trailingIconOn = true,
            error = emailError
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
                            updateValuesAndValidate(fullName,email,selectedDate)
                        },
                    color = Maroon,

                    )
            }) {
                DatePicker(state = datePickerState)
            }


        }
        Button(
            onClick = {
                val request = UpdateAccountRequest(
                    username = fullName,
                    email = email,
                    country = selectedCountry,
                    dateOfBirth = selectedDate
                )
                viewModel.editProfile(request, context = context)
            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
            enabled = isButtonEnabled
        ) {
            Text(text = "Save", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }


    }
}

@Composable
fun PasswordChangeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User
) {
    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        var password by remember { mutableStateOf("") }
        var secondPassword by remember { mutableStateOf("") }

        var passwordError by remember { mutableStateOf("") }
        var secondPasswordError by remember { mutableStateOf("") }

        val isButtonEnabled by remember {
            derivedStateOf {
                password.isNotEmpty() &&
                secondPassword.isNotEmpty() &&
                passwordError.isEmpty() &&
                secondPasswordError.isEmpty() &&
                password != secondPassword
            }
        }

        fun validatePassword(password:String ,secondPassword: String) {
            secondPasswordError = when {
                secondPassword.length < 6 -> "Password must be at least 6 characters"
                password == secondPassword -> "Passwords match"
                !secondPassword.any { it.isUpperCase() } -> "Password must contain at least one capital letter"
                !secondPassword.any { it.isLowerCase() } -> "Password must contain at least one small letter"
                !secondPassword.any { it in "!@#$%^&*()-_+=<>?/,.|\\~`" } -> "Password must contain at least one special character"
                else -> ""
            }
            passwordError = when {
                password.length < 6 -> "Password must be at least 6 characters"
                password == secondPassword -> "Passwords match"
                !password.any { it.isUpperCase() } -> "Password must contain at least one capital letter"
                !password.any { it.isLowerCase() } -> "Password must contain at least one small letter"
                !password.any { it in "!@#$%^&*()-_+=<>?/,.|\\~`" } -> "Password must contain at least one special character"
                else -> ""
            }
        }


        fun updateValuesAndValidate(
            password1: String,
            secondPassword1: String
        ) {
            password = password1
            secondPassword = secondPassword1


            validatePassword(password,secondPassword)
        }


        CustomHeader(title = "Change Password") {
            navController.popBackStack()
        }
        namedField(text = "Current Password",
            message = "Enter your password",
            value = password,
            isPassord = true,
            onValueChange = { updateValuesAndValidate(it,secondPassword) },
            error = passwordError)
        namedField(text = "New Password",
            message = "Enter new password",
            value = secondPassword,
            isPassord = true,
            onValueChange = { updateValuesAndValidate(password,it) },
            error = secondPasswordError)

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
            enabled = isButtonEnabled
        ) {
            Text(text = "Save", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen(navController = NavController(LocalContext.current), user = user)
}