package com.groupd.banquemisrapp.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.theme.background

@Composable
fun FavouriteScreen(modifier: Modifier = Modifier) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(
                background
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        CustomHeader(title = "Favourite") {


        }
    }
}

@Preview (showBackground = true)
@Composable
private fun Preview() {
    FavouriteScreen()


}