package com.groupd.banquemisrapp.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background

@Composable
fun namedField(
    text: String, message: String, modifier: Modifier = Modifier, value: String,
    imageRes: Painter = painterResource(
        id = R.drawable.loading_dot
    ), trailingIconOn: Boolean = false, isPassord: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val passwordIcon = if (passwordVisible) painterResource(id = R.drawable.show)
    else painterResource(id = R.drawable.hide)
    Column {
        Text(
            text = text,
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 24.dp, top = 8.dp),
        )

        OutlinedTextField(
            label = {
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            },
            value = value,
            onValueChange = { },
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {



                if(trailingIconOn && isPassord)
                Image(painter = imageRes, contentDescription = "")
                if (isPassord)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Image(
                        painter = passwordIcon,
                        contentDescription = "",
                        modifier = Modifier.padding(end = 15.dp)
                            .size(24.dp)
                    )
                }
            },

        )
    }
}

@Composable
fun SignUpFirst(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        val fullName by remember { mutableStateOf("") }
        val email  by remember { mutableStateOf("") }
        val password by remember { mutableStateOf("") }
        val secondPassword  by remember { mutableStateOf("") }


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
        namedField(text = "Full name", message = "Enter your full name", value = fullName)
        namedField(text = "Email", message = "Enter your password", value = email)
        namedField(text = "Password", message = "Enter your password", value = password , isPassord = true)
        namedField(
            text = "Re-enter Password",
            message = "Re- enter your password",
            value = secondPassword, isPassord = true
        )

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Sign Up", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
        Row {


            Text(text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.5f))) {
                    append("Already have an account? ")
                }

            })
            ClickableText(onClick = {/*Navigate to sign in*/ }, text = buildAnnotatedString {
                withStyle(SpanStyle(color = Maroon, textDecoration = Underline)) {
                    append("Sign In")
                }
            }


            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SignUpFirst()
}