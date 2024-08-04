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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.ui.partials.CustomHeader

@Composable
fun ProfileInformationScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: User
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomHeader(title = "Profile Information") {
            navController.popBackStack()
        }
        Spacer(modifier = modifier.height(16.dp))
        ProfileDetail("Full Name", user.fullName)
        ProfileDetail("Email", user.email)
        ProfileDetail("Date Of Birth", user.dateOfBirth)
        ProfileDetail("Country", user.country)
        ProfileDetail("Bank Account", user.defaultAccountNumber)
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
