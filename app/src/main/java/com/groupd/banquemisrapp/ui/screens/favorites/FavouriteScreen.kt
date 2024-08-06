package com.groupd.banquemisrapp.ui.screens.favorites

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.AccountDTO
import com.groupd.banquemisrapp.data.AddFavoriteRequest
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.partials.FavouriteItem
import com.groupd.banquemisrapp.ui.partials.namedField
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White

@Composable
fun FavouriteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    favouritesViewModel: FavouritesViewModel = viewModel(),

    ) {
val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        CustomHeader(title = "Favourite") {
            navController.popBackStack()

        }
        Text(
            text = "Your favourite list",
            fontSize = 24.sp,
            modifier = modifier
                .padding(16.dp)
                .padding(top = 16.dp),
            fontWeight = FontWeight(600)
        )
        favouritesViewModel.getFavourites()

        FavouritsList(

            onDelete = {
                favouritesViewModel.deleteFavourites(it)

            },
            onAddToFavourites = {
                favouritesViewModel.addFavourites(it)
            },

            )
        val error by favouritesViewModel.error.collectAsState()
        if (error == "HTTP 404 ") {
        Log.d("TAG", "FavouriteScreen: $error")
            Toast.makeText(LocalContext.current, "Account Not Found , Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritsList(
    favouritesViewModel: FavouritesViewModel = viewModel(),
    onDelete: (accountNumber: String) -> Unit,
    onAddToFavourites: (account: AddFavoriteRequest) -> Unit,
    modifier: Modifier = Modifier
) {
    favouritesViewModel.getFavourites()
    val favourites by favouritesViewModel.favourites.collectAsState()

    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableStateOf(0) }
    val sheet2State = rememberModalBottomSheetState()
    var isSheetOpen2 by rememberSaveable { mutableStateOf(false) }
    var tempName by remember { mutableStateOf("") }
    var tempAccount by remember { mutableStateOf("") }


    LazyColumn(
        modifier = Modifier.heightIn(max = 500.dp)
    ) {
        favouritesViewModel.getFavourites()
        items(favourites.size) { index ->
            var tempName by remember { mutableStateOf("") }
            var tempAccount by remember { mutableStateOf("") }



            FavouriteItem(
                name = favourites[index].accountHolderName,
                account = favourites[index].accountNumber,
                onEdit = {
                    isSheetOpen = !isSheetOpen
                    selectedItemIndex = index
                },
                onDelete = {
                    onDelete(favourites[index].accountNumber)
                },
                isEditable = true
            )
            if (isSheetOpen && selectedItemIndex == index) {
                ModalBottomSheet(
                    onDismissRequest = { isSheetOpen = !isSheetOpen },
                    sheetState = sheetState,
                    containerColor = White

                ) {
                    favouritesViewModel.getFavourites()
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
                                if (tempName.isEmpty() || tempAccount.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please Fill all Fields!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    isSheetOpen = !isSheetOpen


                                }
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
    Button(
        onClick = {
            isSheetOpen2 = !isSheetOpen2
        },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(Maroon),
    ) {
        Text(
            text = "Add New Favourite",
            Modifier.padding(12.dp),
            color = Color.White,
            fontSize = 18.sp
        )

    }




    if (isSheetOpen2) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen2 = !isSheetOpen2 },
            sheetState = sheet2State,
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
                        text = "Add",
                        fontWeight = FontWeight(500),
                        fontSize = 18.sp,
                        modifier = modifier.padding(start = 8.dp)
                    )
                }

                namedField(
                    text = "Favourite Account Name",
                    message = "Enter Cardholder Name",
                    value = tempName,
                    onValueChange = { tempName = it }
                )
                namedField(
                    text = "Favourite Account Number",
                    message = "Enter Favourite Account Number",
                    value = tempAccount,
                    onValueChange = { tempAccount = it },
                    error = if (tempAccount.length != 16) "Should be 16 digits" else null

                )

                Button(

                    onClick = {
                        if (tempName.isEmpty() || tempAccount.isEmpty()) {
                            Toast.makeText(context, "Please Fill all Fields!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            onAddToFavourites(AddFavoriteRequest(tempAccount, tempName))
                            isSheetOpen2 = !isSheetOpen2
                        }

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


@Preview(showBackground = true)
@Composable
private fun Preview() {


}