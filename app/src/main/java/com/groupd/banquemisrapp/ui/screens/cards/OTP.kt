package com.groupd.banquemisrapp.ui.screens.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.routes.Route.APP_CONNECTION
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.theme.Black
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background
import java.time.format.TextStyle

@Composable
fun OTPEnteredScreen(navController: NavController , modifier: Modifier = Modifier) {
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
            Text(
                text = "Don't receive OTP? Resend OTP",
                textAlign = TextAlign.Center,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(200.dp))

            // Check if all fields are filled
            val isButtonEnabled = otpValues.all { it.isNotEmpty() }

            Button(
                onClick = {
                    // Handle sign-in action
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
    OTPEnteredScreen(NavController(LocalContext.current))
}