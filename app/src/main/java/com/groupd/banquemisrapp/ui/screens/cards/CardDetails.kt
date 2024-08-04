package com.groupd.banquemisrapp.ui.screens.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route.OTP
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon


@Composable
fun CardDetailsScreen(navController: NavController, modifier: Modifier = Modifier, user: User) {

    var cardholderName by remember { mutableStateOf("") }
    var cardNo by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var CVV by remember { mutableStateOf("") }

    Column (
        modifier = modifier.fillMaxSize()
    ){
        CustomHeader(title = "Add Card") {
            navController.popBackStack()
        }


        namedField(
            text = "Cardholder name",
            message = "Enter Cardholder name",
            value = cardholderName,
            onValueChange = { cardholderName = it },
            imageRes = painterResource(id = R.drawable.ic_profile),
            trailingIconOn = false
        )
        namedField(
            text = "card No ",
            message = "Enter Card Number",
            value = cardNo,
            onValueChange = { cardNo = it },
            imageRes = painterResource(id = R.drawable.ic_profile),
            trailingIconOn = false,
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            OutlinedTextField(
                value = expiryDate,
                onValueChange = { newValue ->
                    if (newValue.length <= 5 && newValue.all { it.isDigit() || it.isSpecialCharacter() }) {
                        expiryDate = newValue
                    }
                },
                label = { Text("MM/YY") },
                placeholder = { Text("MM/YY") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Unspecified),
                //visualTransformation = { VisualTransformation.None }
            )

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                value = CVV,
                onValueChange = { newValue ->
                    if (newValue.length <= 3 && newValue.all { it.isDigit() }) {
                        CVV = newValue
                    }
                },
                label = { Text("CVV") },
                placeholder = { Text("CVV") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
               // visualTransformation = { VisualTransformation.None }
            )
        }

        Button(
            onClick = {
                user.savingAccount.cardHolder = cardholderName
                user.savingAccount.accountNumber = cardNo
                user.savingAccount.expiryDate = expiryDate
                user.savingAccount.cvv = CVV
                navController.navigate(OTP)
                      },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Get Started ", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
    }


}

fun Char.isSpecialCharacter(): Boolean {
    return !this.isLetterOrDigit() && !this.isWhitespace()
}

@Preview(showBackground = true)
@Composable
private fun CardDetailsScreenPreview() {

}