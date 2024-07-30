package com.groupd.banquemisrapp.ui.screens.profile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.theme.BanqueMisrAppTheme
import com.groupd.banquemisrapp.ui.theme.Maroon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Magdy ",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color.Red.copy(alpha = 0.2f)
                    )
                )
            )

    ) {

    }

}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color.Red.copy(alpha = 0.2f)
                    )
                )
            )
            .padding(16.dp)
    ) {
        CustomHeader(title = "Profile", onBackClick = {})
        ProfileHeader()
        Spacer(modifier = Modifier.height(16.dp))
        val options = listOf("Personal information", "Setting", "Payment history", "My Favourite list")

        ProfileOptionItem("Personal information"  , "Your information" ,painterResource(id = R.drawable.user))
        Divider()
        ProfileOptionItem("Settings"  , "Change your settings" ,painterResource(id = R.drawable.setting))
        Divider()
        ProfileOptionItem("Payment history"  , "View your transactions" ,painterResource(id = R.drawable.history))
        Divider()
        ProfileOptionItem("My favourite list"  , "View your favourites" ,painterResource(id = R.drawable.favorite))
        Divider()
    }
}

@Composable
fun CustomHeader(title: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 12.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {/*todo*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Back button",
            )
        }
        Spacer(modifier = Modifier.width(90.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            fontWeight = FontWeight(400)
        )
        Spacer(modifier = Modifier.weight(1f))
        // Add more elements here if needed
    }
}

@Composable
fun ProfileHeader(modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Asmaa Dosuky",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight(500)
            )
        }
    }
}

@Composable
fun ProfileOptionItem(
    option: String,
    info : String,
    imageRes: Painter,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { /* Handle click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card (
            modifier = modifier.size(width = 40.dp, height = 40.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDAC7CA))
        ){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ){
                Icon(
                    painter = imageRes ,
                    contentDescription = "$option + Icon",
                    tint = Maroon,
                    modifier = modifier
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }

        }
        Spacer(modifier = modifier.width(16.dp))
        Column {
            Text(text = option, style = MaterialTheme.typography.bodyMedium)
            Text(text = info, color = Color.LightGray)
        }
        Spacer(modifier = modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            tint = Color.LightGray,
            modifier = modifier.size(24.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileScreen()
}