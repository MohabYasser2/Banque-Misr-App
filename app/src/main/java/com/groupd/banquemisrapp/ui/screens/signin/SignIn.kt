package com.groupd.banquemisrapp.ui.screens.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background

@Composable
fun SignInScreen(navController: NavController, modifier: Modifier = Modifier) {
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
            text = "Sign In",
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
            onValueChange = { password = it })
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Sign In", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
        Row {


            Text(text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.5f))) {
                    append("Don't have an account?")
                }

            },
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 16.sp)
            TextButton(onClick = {/*Navigate to sign in*/ }) {
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Maroon, textDecoration = Underline)) {
                        append("Sign Up")
                    }
                },fontSize = 16.sp)

            }
        }



    }

}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen(navController = NavController(LocalContext.current))
}