package com.groupd.banquemisrapp.ui.screens.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.MoreOptionItem
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White
import com.groupd.banquemisrapp.ui.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController, modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                background

            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeader(title = "More") {}
        MoreOptionItem(
            imageRes = painterResource(id = R.drawable.ic_website),
            option = "Transfer From Website"
        )
        HorizontalDivider()
        MoreOptionItem(
            imageRes = painterResource(id = R.drawable.ic_favourite),
            option = "Favourites"
        )
        HorizontalDivider()
        MoreOptionItem(
            imageRes = painterResource(id = R.drawable.ic_profile),
            option = "Profile",
            onClick = {
                navController.navigate(Route.PROFILE)
            })
        HorizontalDivider()
        MoreOptionItem(imageRes = painterResource(id = R.drawable.ic_help), option = "Help",
            onClick = {
                isSheetOpen = !isSheetOpen
            }
        )
        HorizontalDivider()
        MoreOptionItem(
            imageRes = painterResource(id = R.drawable.ic_logout),
            option = "Logout",
            isArrow = false
        )

        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOpen = !isSheetOpen },
                sheetState = sheetState,

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                        colors = CardDefaults.cardColors(White),
                        shape = RoundedCornerShape(5.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                                        data =
                                            Uri.parse("mailto:") // Only email apps should handle this
                                        putExtra(
                                            Intent.EXTRA_EMAIL,
                                            arrayOf("BM19888@banquemisr.com")
                                        ) // Recipients
                                        putExtra(Intent.EXTRA_SUBJECT, "") // Subject
                                        putExtra(Intent.EXTRA_TEXT, "") // Body
                                    }

                                    context.startActivity(intent)


                                },

                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Card(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(64.dp),
                                colors = CardDefaults.cardColors(Maroon.copy(alpha = 0.2f)),
                                shape = RoundedCornerShape(5.dp),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxSize(),
                                    tint = Maroon
                                )
                            }
                            Text(
                                text = "Send Email",
                                modifier = Modifier.padding(bottom = 20.dp),
                                fontWeight = FontWeight(500)
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                        colors = CardDefaults.cardColors(White),
                        shape = RoundedCornerShape(5.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    val intent =
                                        Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19888"))
                                    startActivity(context, intent, null)

                                },

                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(64.dp),
                                colors = CardDefaults.cardColors(Maroon.copy(alpha = 0.2f)),
                                shape = RoundedCornerShape(5.dp),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_call),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxSize(),
                                    tint = Maroon
                                )
                            }
                            Text(
                                text = "Call Us",
                                fontWeight = FontWeight(500),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "19888",
                                modifier = Modifier.padding(bottom = 3.dp),
                                color = Maroon,
                            )
                        }
                    }

                }
            }
        }
    }

}