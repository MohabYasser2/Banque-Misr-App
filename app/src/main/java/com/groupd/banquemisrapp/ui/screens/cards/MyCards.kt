package com.groupd.banquemisrapp.ui.screens.cards



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.data.User
import com.groupd.banquemisrapp.routes.Route
import com.groupd.banquemisrapp.ui.partials.CustomHeader
import com.groupd.banquemisrapp.ui.theme.Black
import com.groupd.banquemisrapp.ui.theme.Maroon

@Composable
fun MyCardsScreen(navController: NavController, modifier: Modifier = Modifier, user: User) {
    Column(
        modifier = modifier
            .fillMaxSize()


    )
    {
        CustomHeader(title = "My Cards") {
            navController.popBackStack()
        }


        CardList(){
            navController.navigate(Route.ADD_CARD)
        }




    }



}


@Composable
fun CardList(modifier: Modifier = Modifier , onClick : () -> Unit) {
    Column (modifier = modifier.verticalScroll(rememberScrollState())){
        CardItem("Asmaa Dosuky", "Account xxxx7890" , true)
        CardItem("Asmaa Dosuky", "Account xxxx7890" , false)
        CardItem("Asmaa Dosuky", "Account xxxx7890" , false)
        CardItem("Asmaa Dosuky", "Account xxxx7890" , false)
        CardItem("Asmaa Dosuky", "Account xxxx7890" , false)


        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(14.dp),
            colors = ButtonDefaults.buttonColors(Maroon),
        ) {
            Text(text = "Add New Account", Modifier.padding(12.dp), color = Color.White, fontSize = 18.sp)

        }
    }
}





@Composable
fun CardItem(
    name: String,
    account: String,
    isDefault : Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth(),

        ) {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { /*TODO*/ },
                Modifier
                    .clip(CircleShape)
                    .background(Maroon.copy(alpha = 0.1f)),
            ) {


                Icon(
                    painter = painterResource(id = R.drawable.ic_bank),
                    contentDescription = "Bank Icon",
                    Modifier
                        .size(36.dp)

                )
            }

            Column(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight(500),
                    fontSize = 22.sp,
                    modifier = modifier.padding(bottom = 12.dp)
                )
                Text(text = account, color = Black.copy(alpha = 0.5f))

            }
            if(isDefault){
                Column (
                    //modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Top
                ){
                    Card(
                        modifier = Modifier.wrapContentSize(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                    ) {
                        Text(
                            text = "Default",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }


        }
    }


}









@Preview
@Composable
private fun MyCardsScreenPreview() {

}