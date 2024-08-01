package com.groupd.banquemisrapp.ui.screens.cards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.ui.theme.Maroon

@Composable
fun ConnectingScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Speedo \nTransfer",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = modifier.offset(y = 190.dp)
        )
        CircularProgressIndicator(
            color = Maroon,
            modifier = modifier.size(170.dp) // Customize the size as needed
        )
        //Spacer(modifier = modifier.height(26.dp))

        //Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "Connecting to Speedo Transfer\nCredit card",
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
    }
}


@Preview
@Composable
private fun ConnectingScreenPreview() {
    ConnectingScreen(NavController(LocalContext.current))
}