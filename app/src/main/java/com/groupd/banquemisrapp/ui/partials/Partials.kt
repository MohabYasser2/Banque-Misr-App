package com.groupd.banquemisrapp.ui.partials

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.theme.Maroon


/* contain
        1- named Field
        2- ProfileOptionItem
        3- custom header
 */




@Composable
fun namedField(
    text: String,
    message: String,
    modifier: Modifier = Modifier,
    value: String,
    imageRes: Painter = painterResource(id = R.drawable.loading_dot),
    trailingIconOn: Boolean = false,
    isPassord: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onClick: () -> Unit = {},
    isReadOnly: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val passwordIcon = if (passwordVisible) painterResource(id = R.drawable.show)
    else painterResource(id = R.drawable.hide)
    Column {
        Text(
            text = text,
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 24.dp, top = 8.dp)

        )

        OutlinedTextField(
            label = {
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = Color.Black.copy(alpha = 0.5f)

                )
            },
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clickable { onClick() },
            shape = RoundedCornerShape(8.dp),
            readOnly = isReadOnly,
            trailingIcon = {


                if (trailingIconOn && !isPassord)
                    Image(
                        painter = imageRes,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(24.dp)
                            .clickable {
                                onClick()
                            }
                            .alpha(0.6f)

                    )
                if (isPassord)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Image(
                            painter = passwordIcon,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .size(24.dp)
                                .alpha(0.6f)
                        )
                    }
            },
            visualTransformation = if (passwordVisible || !isPassord    ) VisualTransformation.None else PasswordVisualTransformation(),

            )
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
            .padding(vertical = 24.dp)
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
            Text(text = option,  fontWeight = FontWeight(500))
            Text(text = info, color = Color.Black.copy(alpha = 0.5f))
        }
        Spacer(modifier = modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            modifier = modifier.size(20.dp).alpha(0.5f)
        )

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
