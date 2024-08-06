package com.groupd.banquemisrapp.ui.screens.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.data.Account
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.routes.Route.OTP_CONNECTED
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.theme.Maroon

@Composable
fun OTPEnteredScreen(navController: NavController, modifier: Modifier = Modifier, user: User) {
    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }
    // State to hold the OTP input values
    var otpValues by remember { mutableStateOf(listOf("", "", "", "", "", "")) }
    // Create focus requesters for each OTP field
    val focusRequesters = List(6) { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        CustomHeader(title = "Bank Card OTP") {
            navController.popBackStack()
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(
                text = "Enter the digits verification code\nsent to Email@gmail.com",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // OTP Input Fields
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                otpValues.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = { newValue ->
                            if (newValue.length <= 1) {
                                // Update the state with the new value for the current index
                                otpValues = otpValues.toMutableList().apply {
                                    this[index] = newValue
                                }

                                // Move focus to the next field
                                if (newValue.isNotEmpty() && index < focusRequesters.size - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(48.dp)
                            .height(56.dp)
                            .focusRequester(focusRequesters[index]),
                        textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (index < focusRequesters.size - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row {


                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.5f))) {
                        append("Don't receive OTP? ")
                    }

                },
                    modifier = Modifier.align(Alignment.CenterVertically),fontSize = 16.sp)
                TextButton(onClick = {}) {
                    Text(text = buildAnnotatedString {
                        withStyle(SpanStyle(color = Maroon)) {
                            append("Resend OTP")
                        }
                    },fontSize = 16.sp)

                }
            }
            Spacer(modifier = Modifier.height(200.dp))

            // Check if all fields are filled
            val isButtonEnabled = otpValues.all { it.isNotEmpty() }

            Button(
                onClick = {
                    user.accounts.add(user.savingAccount)
                    user.savingAccount = Account("", "")
                    // Handle sign-in action
                    navController.navigate(OTP_CONNECTED)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Maroon),
                enabled = isButtonEnabled
            ) {
                Text(text = "Sign on", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)
            }

        }

    }
}

@Preview
@Composable
private fun OTPEnteredScreenPreview() {

}