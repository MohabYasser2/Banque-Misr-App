package com.groupd.banquemisrapp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.ProfileOptionItem
import com.groupd.banquemisrapp.ui.theme.background2

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background2)
            .padding(16.dp)
    ) {
        //Text("Settings", style = MaterialTheme.typography.headlineSmall)
        CustomHeader(title = "Setting") {
            
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOptionItem(
            "Change Password",
            "Change Password",
            painterResource(id = R.drawable.password_outline)
        )


        ProfileOptionItem(
            "Edit Profile",
            "Change your information",
            painterResource(id = R.drawable.edit)
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(navController = NavController(LocalContext.current))
    
}