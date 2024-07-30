package com.groupd.banquemisrapp.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background


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
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var secondPassword by remember { mutableStateOf("") }


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
        namedField(text = "Password",
            message = "Enter your password",
            value = password,
            isPassord = true,
            onValueChange = { password = it })
        namedField(text = "Re-enter Password",
            message = "Re- enter your password",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpSecond(modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Text(
            text = "Speedo Transfer",
            fontSize = 24.sp,
            modifier = modifier.padding(40.dp),
            fontWeight = FontWeight(500)
        )
        namedField(
            text = "Country",
            message = "Select your Country",
            value = "",
            onClick =  {

                    isSheetOpen = !isSheetOpen

            },
            onValueChange = { },
            imageRes = painterResource(id = R.drawable.ic_down),
            trailingIconOn = true,
            isReadOnly = true
        )
        namedField(
            text = "Date Of Birth",
            message = "DD/MM/YYYY",
            value = "",
            onClick =  {

                isSheetOpen = !isSheetOpen

            },
            onValueChange = { },
            imageRes = painterResource(id = R.drawable.ic_calendar),
            trailingIconOn = true,
            isReadOnly = true,

        )

        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOpen = !isSheetOpen },
                sheetState = sheetState
            )
            {

            }
        }

        Button(
            onClick = {isSheetOpen = !isSheetOpen},
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Continue", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }


    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SignUpFirst()
}

