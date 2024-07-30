package com.groupd.banquemisrapp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background
import com.groupd.banquemisrapp.ui.theme.background2

@Composable
fun EditProfileScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background2
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var dateOfBirth by remember { mutableStateOf("") }

        CustomHeader(title = "Edit Profile") {

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
            message = "Enter your password",
            value = email,
            onValueChange = { email = it },
            imageRes = painterResource(id = R.drawable.ic_email),
            trailingIconOn = true
        )



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

@Composable
fun PasswordChangeScreen(navController: NavController,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background2
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        var password by remember { mutableStateOf("") }
        var secondPassword by remember { mutableStateOf("") }



        CustomHeader(title = "Change Password") {
            
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
    PasswordChangeScreen(navController = NavController(LocalContext.current))
}