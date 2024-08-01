package com.groupd.banquemisrapp.ui.screens.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.routes.Route.FAVOURITES
import com.groupd.banquemisrapp.routes.Route.PROFILE_INFO
import com.groupd.banquemisrapp.routes.Route.SETTINGS
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.ProfileOptionItem
import com.groupd.banquemisrapp.ui.theme.background2

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                background2
            )
            .padding(16.dp)
    ) {
        CustomHeader(title = "Profile", onBackClick = {})
        ProfileHeader()
        Spacer(modifier = Modifier.height(16.dp))

        ProfileOptionItem(
            "Personal information",
            "Your information",
            painterResource(id = R.drawable.ic_profile),
            onClick = { navController.navigate(PROFILE_INFO) })
        HorizontalDivider()
        ProfileOptionItem(
            "Settings",
            "Change your settings",
            painterResource(id = R.drawable.setting),
            onClick = {
                navController.navigate(
                    SETTINGS
                )
            })
        HorizontalDivider()
        ProfileOptionItem(
            "Payment history",
            "View your transactions",
            painterResource(id = R.drawable.history),

        )
        HorizontalDivider()
        ProfileOptionItem(
            "My favourite list",
            "View your favourites",
            painterResource(id = R.drawable.ic_favourite),
            onClick = {
                navController.navigate(FAVOURITES)
            }
        )

    }
}

@Composable
fun ProfileHeader(modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Asmaa Dosuky",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight(500),
                fontSize = 24.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileScreen(navController = NavController(LocalContext.current))
}