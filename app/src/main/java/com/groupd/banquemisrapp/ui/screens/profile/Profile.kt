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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.Account
import com.groupd.banquemisrapp.data.Favourite
import com.groupd.banquemisrapp.data.Transaction
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.routes.Route.FAVOURITES
import com.groupd.banquemisrapp.routes.Route.PROFILE_INFO
import com.groupd.banquemisrapp.routes.Route.SETTINGS
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.ProfileOptionItem
import com.groupd.banquemisrapp.ui.theme.Black
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background2

@Composable
fun ProfileScreen(
    user: User,
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {

    val profile by viewModel.balance.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()

            .padding(16.dp)
    ) {
        CustomHeader(title = "Profile", onBackClick = { navController.popBackStack() })
        ProfileHeader(profile?.username?:"")
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
            painterResource(id = R.drawable.ic_settings),
            onClick = {
                navController.navigate(
                    SETTINGS
                )
            })
        HorizontalDivider()
        ProfileOptionItem(
            "Payment history",
            "View your transactions",
            painterResource(id = R.drawable.ic_history_3x),
            onClick = { navController.navigate(Route.TRANSACTIONS) },

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
fun ProfileHeader(name: String, modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Black.copy(alpha = 0.1f)),
            onClick = { /*TODO*/ },
        ) {
            val initials = if (name.isNotBlank()) {
                val names = name.split(" ")
                names.joinToString("") { it.firstOrNull()?.toString() ?: "" }
            } else {
                ""  // Provide default initials if name is empty
            }
            Text(
                text = initials,
                fontSize = 24.sp,
                color = Black.copy(alpha = 0.6f),
                fontWeight = FontWeight(500)
            )


        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = name,
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
    // ProfileScreen(User(user),navController = NavController(LocalContext.current))
}