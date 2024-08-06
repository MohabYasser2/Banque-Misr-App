package com.groupd.banquemisrapp.ui.screens.errors

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route.INTERNET_ERROR
import com.groupd.banquemisrapp.ui.theme.Maroon

@Composable
fun InternetConnectionErrorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    user: User,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            //.padding(16.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Add Image for No Internet
        Image(
            painter = painterResource(id = R.drawable.no_internet), // Replace with your image resource
            contentDescription = "No Internet",
            modifier = modifier.size(200.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        // Error Text
        Text(
            text = "Internet connection disabled...",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            minLines = 2,
            color = Color.Black
        )
        Spacer(modifier = modifier.height(8.dp))
        // Update Button
        val context = LocalContext.current
        Button(
            onClick = {

                if (isInternetAvailable(context = context)) {
                    navController.popBackStack()
                } else {
                    navController.navigate(INTERNET_ERROR)
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Update", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun InternetConnectionErrorScreenPreview() {

}