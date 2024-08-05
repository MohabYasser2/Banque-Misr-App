package com.groupd.banquemisrapp.ui.screens.signin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.activities.MainActivity
import com.groupd.banquemisrapp.routes.Route.SIGNUP
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.screens.signup.SignUpViewModel
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background
import androidx.lifecycle.viewmodel.compose.viewModel
import com.groupd.banquemisrapp.data.CardCurrency
import com.groupd.banquemisrapp.data.CardDTO
import com.groupd.banquemisrapp.data.Country
import com.groupd.banquemisrapp.data.Gender
import com.groupd.banquemisrapp.data.LoginRequest
import com.groupd.banquemisrapp.data.RegisterRequest


@Composable
fun SignInScreen(navController: NavController, modifier: Modifier = Modifier,
                 viewModel: SignInViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        val context = navController.context

        val pref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedEmail = pref.getString("email", "")!!
        val savedPassword = pref.getString("password", "")!!

        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf(savedEmail) }
        var password by remember { mutableStateOf(savedPassword) }
        var secondPassword by remember { mutableStateOf("") }
        var cbState by remember { mutableStateOf(true) }


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
            onValueChange = { password = it
            })
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ) {
            Text(text = "Remember me next time?")
            Checkbox(
                checked = cbState,
                onCheckedChange = { cbState = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Maroon,      // Color when the checkbox is checked
                    uncheckedColor = Color.Gray, // Color when the checkbox is unchecked
                    checkmarkColor = Color.White // Color of the checkmark inside the checkbox
                )
            )

        }

        viewModel.login(LoginRequest(email, password))
        val hasError by viewModel.hasError.collectAsState()
        val loginResponse by viewModel.loginResponse.collectAsState()
        Button(
            onClick = {




                if (
                    hasError
                ) {
                    Log.d("TAG", "Error: $hasError")
                    Toast.makeText(context, "Check your info", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d("TAG", "Logging in : ${loginResponse}")
                    saveData(email, password, cbState, context)
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }


            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Sign In", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
        Row {


            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.5f))) {
                        append("Don't have an account?")
                    }

                },
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 16.sp
            )
            TextButton(onClick = { navController.navigate(SIGNUP) }) {
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Maroon, textDecoration = Underline)) {
                        append("Sign Up")
                    }
                }, fontSize = 16.sp)

            }
        }


    }

}


fun saveData(email: String, password: String, cbState: Boolean, context: Context) {
    val editor = context.getSharedPreferences("user_data", Context.MODE_PRIVATE).edit()
    if (cbState) {
        editor.putString("email", email)
        editor.putString("password", password)
    } else {
        editor.putString("email", "")
        editor.putString("password", "")
    }
    editor.apply()
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen(navController = NavController(LocalContext.current))
}