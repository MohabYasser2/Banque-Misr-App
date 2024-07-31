package com.groupd.banquemisrapp.ui.screens.errors

import androidx.compose.runtime.Composable

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.activities.MainActivity
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background
import com.groupd.banquemisrapp.ui.theme.background2


@Composable
fun ServerErrorScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            //.padding(16.dp)
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Add Image for Error
        Image(
            painter = painterResource(id = R.drawable.ic_server_error), // Replace with your image resource
            contentDescription = "Server Error",
            modifier = modifier.size(250.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        // Error Text
        Text(
            text = "Server error...",
            fontWeight = FontWeight(500),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = Color.Black
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "It seems like we're having some difficulties, please don't leave your aspirations, we are sending for help",
            textAlign = TextAlign.Center,
            color = Color.Black.copy(alpha = 0.5f)
        )
        Spacer(modifier = modifier.height(16.dp))
        // Call Us Button
        Button(
            onClick = {
                val intent =
                    Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19888"))
                startActivity(context, intent, null)

            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Call Us", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data =
                        Uri.parse("mailto:") // Only email apps should handle this
                    putExtra(
                        Intent.EXTRA_EMAIL,
                        arrayOf("BM19888@banquemisr.com")
                    ) // Recipients
                    putExtra(Intent.EXTRA_SUBJECT, "Server Error Report") // Subject
                    putExtra(Intent.EXTRA_TEXT, "Server Error Report") // Body
                }

                context.startActivity(intent)
            },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            border = BorderStroke(2.dp, Maroon),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
        ) {
            Text(text = "Message Us", Modifier.padding(12.dp), color = Maroon, fontSize = 18.sp)

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ServerErrorScreenPreview() {
    ServerErrorScreen(navController = NavController(LocalContext.current))
}