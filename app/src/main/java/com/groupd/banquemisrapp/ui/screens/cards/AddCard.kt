package com.groupd.banquemisrapp.ui.screens.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.routes.Route.ADD_CARD_DETAILS
import com.groupd.banquemisrapp.routes.Route.SIGNUP2
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.screens.signup.CountryList
import com.groupd.banquemisrapp.ui.theme.Maroon

@Composable
fun AddCardScreen(navController: NavController, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }

    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("") }

    Column (
        modifier = modifier.fillMaxSize()
    ){
        CustomHeader(title = "Select Currency") {
            navController.popBackStack()
        }


        CountryList(currentCountry = selectedCountry, onCountrySelected = {
            isSheetOneOpen = !isSheetOneOpen
            selectedCountry = it
        })

        Button(
            onClick = {

                navController.navigate("$ADD_CARD_DETAILS/${selectedCountry}")

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

@Preview(showBackground = true)
@Composable
private fun AddCardScreenPreview() {

}