package com.groupd.banquemisrapp.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route.CHANGE_PASSWORD
import com.groupd.banquemisrapp.routes.Route.EDIT_PROFILE
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.ProfileOptionItem

@Composable
fun SettingsScreen(navController: NavController, modifier: Modifier = Modifier, user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Text("Settings", style = MaterialTheme.typography.headlineSmall)
        CustomHeader(title = "Setting") {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOptionItem(
            "Change Password",
            "Change Password",
            painterResource(id = R.drawable.password_outline),
            onClick = { navController.navigate(CHANGE_PASSWORD) }
        )


        ProfileOptionItem(
            "Edit Profile",
            "Change your information",
            painterResource(id = R.drawable.ic_edit),
            onClick = { navController.navigate(EDIT_PROFILE) }
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {

    
}