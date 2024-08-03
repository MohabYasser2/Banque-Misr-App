package com.groupd.banquemisrapp.ui.screens.profile

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.screens.signup.CountryList
import com.groupd.banquemisrapp.ui.theme.Maroon
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController, modifier: Modifier = Modifier, user: User) {
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
            ,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var dateOfBirth by remember { mutableStateOf("") }

        CustomHeader(title = "Edit Profile") {
            navController.popBackStack()
        }

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
            message = "Enter your Email",
            value = email,
            onValueChange = { email = it },
            imageRes = painterResource(id = R.drawable.ic_email),
            trailingIconOn = true
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



            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                colors = ButtonDefaults.buttonColors(Maroon),
            ) {
                Text(text = "Save", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

            }
        }


        }
    }

    @Composable
    fun PasswordChangeScreen(
        navController: NavController,
        modifier: Modifier = Modifier,
        user: User
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                ,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            var password by remember { mutableStateOf("") }
            var secondPassword by remember { mutableStateOf("") }



            CustomHeader(title = "Change Password") {
                navController.popBackStack()
            }
            namedField(text = "Current Password",
                message = "Enter your password",
                value = password,
                isPassord = true,
                onValueChange = { password = it })
            namedField(text = "New Password",
                message = "Enter new password",
                value = secondPassword,
                isPassord = true,
                onValueChange = { secondPassword = it })

            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                colors = ButtonDefaults.buttonColors(Maroon),
            ) {
                Text(text = "Save", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

            }
        }
    }


@Preview(showBackground = true)
@Composable
private fun EditProfileScreenPreview() {

}