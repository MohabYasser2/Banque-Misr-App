package com.groupd.banquemisrapp.ui.screens.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route.ADD_CARD
import com.groupd.banquemisrapp.routes.Route.HOME_SCREEN
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.theme.Maroon

@Composable
fun OTPConnectedScreen(navController: NavController, modifier: Modifier = Modifier, user: User) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 40.dp)
    ) {
        CustomHeader(title = "Bank Card OTP") {
            navController.popBackStack()
        }

        Spacer(modifier =  Modifier.height(70.dp))
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_success), // Replace with your icon resource
                contentDescription = "Success Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Account Connected \nSuccessfully!",
                fontWeight = FontWeight(500),
                fontSize = 28.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Feel free to connect another account at the same time.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )

        }
        Column (
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 12.dp , vertical = 32.dp)
                .fillMaxHeight()
        ) {
            Button(
                onClick = {
                    navController.navigate(ADD_CARD)
                          },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(Maroon),
            ) {
                Text(text = "Connect another account", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

            }
            Button(
                onClick = { navController.navigate(HOME_SCREEN)  },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, Maroon)
            ) {
                Text(text = "Back to home", Modifier.padding(12.dp), color = Maroon, fontSize = 18.sp)

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OTPConnectedScreenPreview() {

}