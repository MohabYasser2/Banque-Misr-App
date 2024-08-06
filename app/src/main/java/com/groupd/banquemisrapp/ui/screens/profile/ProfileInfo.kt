package com.groupd.banquemisrapp.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.groupd.banquemisrapp.activities.isInternetAvailable
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.screens.main.HomeViewModel

@Composable
fun ProfileInformationScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User,
    viewModel: ProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    if (!isInternetAvailable(context)) {
        navController.navigate(Route.INTERNET_ERROR)
    }
    val profile by viewModel.balance.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomHeader(title = "Profile Information") {
            navController.popBackStack()
        }
        // Ensure profile is not null before accessing its properties
        val accounts = profile?.accounts ?: emptyList()
        var accountNumber: String = ""

// Iterate over accounts safely
        for (account in accounts) {
            if (account.isDefault) {
                accountNumber = account.accountNumber ?: ""  // Ensure accountNumber is not null
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        ProfileDetail("Full Name", profile?.username ?: "")
        ProfileDetail("Email", profile?.email ?: "")
        ProfileDetail("Date Of Birth", profile?.dateOfBirth ?: "")
        ProfileDetail("Country", profile?.country ?: "")
        ProfileDetail("Bank Account", accountNumber)
    }
}

@Composable
fun ProfileDetail(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight(500),
            modifier = modifier.padding(4.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black.copy(alpha = 0.5f),
            modifier = modifier.padding(4.dp)
        )
    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
private fun ProfileInformationScreenPreview() {

}
