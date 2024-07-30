package com.groupd.banquemisrapp.ui.screens.startup


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.screens.profile.ProfileScreen
import com.groupd.banquemisrapp.ui.screens.signup.SignUpFirst
import com.groupd.banquemisrapp.ui.screens.signup.SignUpSecond
import com.groupd.banquemisrapp.ui.theme.Black
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.background
import kotlinx.coroutines.launch

class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            OnBoardingScreen()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen1(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 112.dp)
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Image(
            painter = painterResource(id = R.drawable.onboarding_amont),
            contentDescription = "Picture of man pacing fastly",
            Modifier.size(320.dp)
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",

                )
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",
                Modifier.alpha(0.5f)

            )
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",
                Modifier.alpha(0.5f)
            )

        }
        Text(
            text = "Amont",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Black.copy(alpha = 0.6f))) {
                    append("Send money fast with simple steps.")
                }
                append(" Create account, Confirmation, Payment.")

                withStyle(SpanStyle(color = Black.copy(alpha = 0.6f))) {
                    append(" Simple.")
                }
            },

            fontSize = 18.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            textAlign = TextAlign.Center


        )


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen2(modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 112.dp)
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Image(
            painter = painterResource(id = R.drawable.onboarding_confirmation),
            contentDescription = "Picture of man transferring money worldwide",
            Modifier.size(320.dp)
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",
                Modifier.alpha(0.5f)
            )
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",


                )
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",
                Modifier.alpha(0.5f)
            )

        }
        Text(
            text = "Confirmation",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Transfer funds instantly to friends and family worldwide, strong shield protecting money.",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .alpha(0.8f),
            textAlign = TextAlign.Center


        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen3(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 112.dp)
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Image(
            painter = painterResource(id = R.drawable.onboarding_payment),
            contentDescription = "Picture of woman using card on her phone",
            Modifier.size(320.dp)
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",
                Modifier.alpha(0.5f)
            )
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",
                Modifier.alpha(0.5f)

            )
            Image(
                painter = painterResource(id = R.drawable.loading_dot),
                contentDescription = "loading dot",

                )

        }
        Text(
            text = "Payment",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Enjoy peace of mind with our secure platform \n Transfer funds instantly to friends",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .alpha(0.8f),
            textAlign = TextAlign.Center


        )


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = Modifier.fillMaxWidth(),  horizontalArrangement = Arrangement.End)
    {

            Text(text = "Skip", color = Color.Black, fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 64.dp, end = 16.dp)
                    .clickable {
                        //nav to sign up
                    })

    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
    ) { page ->
        when (page) {

            0 -> OnBoardingScreen1()
            1 -> OnBoardingScreen2()
            2 -> OnBoardingScreen3()
        }
    }
    Button(
        onClick = {
            if (pagerState.currentPage < 2) {
                coroutineScope.launch {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }
            } else {
                // Navigate to the Sign in
                
            }
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 620.dp),
        colors = ButtonDefaults.buttonColors(Maroon),


        )
    {
        Text(
            text = "Next",
            modifier = modifier.padding(8.dp),
            color = White,
            fontSize = 18.sp
        )
    }

    }

@Preview(showBackground = true)
@Composable
private fun Preview() {
    OnBoardingScreen()
}