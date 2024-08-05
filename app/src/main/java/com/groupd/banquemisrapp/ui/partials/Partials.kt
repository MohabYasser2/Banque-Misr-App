package com.groupd.banquemisrapp.ui.partials

import android.graphics.Rect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.groupd.banquemisrapp.R
import com.groupd.banquemisrapp.ui.theme.Black
import com.groupd.banquemisrapp.ui.theme.Maroon
import com.groupd.banquemisrapp.ui.theme.White
import com.groupd.banquemisrapp.ui.theme.whiteBackground


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
    isReadOnly: Boolean = false,
    error: String? = null // New parameter for error message
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val passwordIcon = if (passwordVisible) painterResource(id = R.drawable.show)
    else painterResource(id = R.drawable.hide)
    Column {
        Text(
            text = text,
            modifier = modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            color = Color.Black.copy(alpha = 0.8f)

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
            singleLine = true,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedContainerColor = White
            ),
            shape = RoundedCornerShape(8.dp),
            readOnly = isReadOnly,
            trailingIcon = {


                if (trailingIconOn && !isPassord)
                    Image(
                        painter = imageRes,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 16.dp)
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
                                .padding(end = 16.dp)
                                .size(24.dp)
                                .alpha(0.6f)
                        )
                    }
            },
            visualTransformation = if (passwordVisible || !isPassord) VisualTransformation.None else PasswordVisualTransformation(),

        )
        if (error != null && error.isNotEmpty()) {
            Text(
                text = error,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}


@Composable
fun ProfileOptionItem(
    option: String,
    info: String,
    imageRes: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = modifier.size(width = 40.dp, height = 40.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDAC7CA))
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    painter = imageRes,
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
            Text(text = option, fontWeight = FontWeight(500))
            Text(text = info, color = Color.Black.copy(alpha = 0.5f))
        }
        Spacer(modifier = modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            modifier = modifier
                .size(20.dp)
                .alpha(0.5f)
                .clickable { onClick() }
        )

    }
}

@Composable
fun MoreOptionItem(
    option: String,
    imageRes: Painter,
    isArrow: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = imageRes,
            contentDescription = "$option + Icon",
            modifier = modifier
                .size(24.dp),
            tint = Color.Black.copy(alpha = 0.5f),

            )

        Text(
            text = option,
            fontWeight = FontWeight(500),
            color = Color.Black.copy(alpha = 0.5f),
            fontSize = 20.sp,
            modifier = modifier.padding(20.dp)
        )

        Spacer(modifier = modifier.weight(1f))
        if (isArrow) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    modifier = modifier
                        .size(20.dp)
                        .alpha(0.5f)
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHeader(title: String, onBackClick: () -> Unit) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,

                )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "Back button",
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White.copy(alpha = 0f)
        )

    )

}

@Composable
fun iconNamedVertically(
    onClick: () -> Unit,
    isSelected: Boolean = false,
    imageRes: Painter,
    text: String = "",
    annotatedString: AnnotatedString = buildAnnotatedString { append(text) },
    imageDescription: String = "",
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    imageSize: Dp = 28.dp,
    textColor: Color = Color.Black.copy(alpha = 0.5f),
    tint: Color = Color.Black.copy(alpha = 0.5f)
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.clickable { onClick() }
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier
                .size(imageSize + 16.dp)
                .clip(
                    RoundedCornerShape(20f)
                )
                .background(Color.White.copy(alpha = 0.1f)),

            )
        {

            Icon(
                painter = imageRes,
                contentDescription = imageDescription,
                modifier = modifier
                    .size(imageSize)
                    .padding(bottom = 4.dp),
                tint = if (isSelected) Maroon else tint
            )
        }
        Text(
            text = annotatedString,
            modifier = modifier.padding(bottom = 8.dp),
            fontSize = fontSize,
            color = if (isSelected) Maroon else textColor,
            fontWeight = FontWeight(500)
        )

    }
}

@Composable
fun FavouriteItem(
    name: String,
    account: String,
    isEditable: Boolean = false,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {},
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable { onItemClick() },

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
                    fontSize = 18.sp,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Text(text = account, color = Black.copy(alpha = 0.5f))

            }
            if (isEditable) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit Icon",
                    Modifier
                        .size(24.dp)
                        .clickable { onEdit() },
                    tint = Black.copy(alpha = 0.5f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete Icon",
                    tint = Maroon,
                    modifier = modifier
                        .padding(start = 16.dp)
                        .size(24.dp)
                        .clickable { onDelete() }
                )

            }
        }
    }


}