package com.groupd.banquemisrapp.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.FavouriteItem
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White

@Composable
fun FavouriteScreen(navController: NavController, modifier: Modifier = Modifier, user: User) {

    Column(
        modifier = modifier
            .fillMaxSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        CustomHeader(title = "Favourite") {
            navController.popBackStack()

        }
        Text(
            text = "Your favourite list",
            fontSize = 24.sp,
            modifier = modifier.padding(40.dp),
            fontWeight = FontWeight(500)
        )
        FavouritsList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritsList(modifier: Modifier = Modifier) {

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    val selectedCountry = remember { mutableStateOf("") }
    val favourites = remember {
        mutableStateListOf(

            Pair("Asmaa Dosuky", "Account xxxx7890"),
            Pair("Ahmed Mohamed", "Account xxxx71234"),
            Pair("Mohab Yasser", "Account xxxx6969"),
            Pair("Mohamed Magdy", "Account xxxx4200"),

        )
    }


    LazyColumn(
        modifier = Modifier.heightIn(max = 600.dp)
    ) {
        items(favourites.size) { index ->
            val (name, account) = favourites[index]
            var tempName by remember { mutableStateOf("") }
            var tempAccount by remember { mutableStateOf("") }
            var selectedItemIndex by remember { mutableStateOf(0) }
            FavouriteItem(
                name = name,
                account = account,
                onEdit = {
                    isSheetOpen = !isSheetOpen
                    selectedItemIndex = index
                },
                onDelete = {
                    favourites.removeAt(index)
                           },
                isEditable = true
            )
            if (isSheetOpen && selectedItemIndex == index) {
                ModalBottomSheet(
                    onDismissRequest = { isSheetOpen = !isSheetOpen },
                    sheetState = sheetState,
                    containerColor = White

                    ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)

                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "Edit Icon",
                                Modifier
                                    .size(32.dp),
                                tint = Maroon
                            )
                            Text(
                                text = "Edit",
                                fontWeight = FontWeight(500),
                                fontSize = 18.sp,
                                modifier = modifier.padding(start = 8.dp)
                            )
                        }

                        namedField(
                            text = "Recipient Name",
                            message = "Enter Cardholder Name",
                            value = tempName,
                            onValueChange = { tempName = it }
                        )
                        namedField(
                            text = "Recipient Account",
                            message = "Enter Cardholder Account",
                            value = tempAccount,
                            onValueChange = { tempAccount = it }

                        )

                        Button(
                            onClick = {
                                isSheetOpen = !isSheetOpen
                                favourites[selectedItemIndex] = Pair(tempName, tempAccount)
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            colors = ButtonDefaults.buttonColors(Maroon),
                        ) {
                            Text(
                                text = "Save",
                                Modifier.padding(12.dp),
                                color = Color.White,
                                fontSize = 18.sp
                            )

                        }


                    }

                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun Preview() {



}