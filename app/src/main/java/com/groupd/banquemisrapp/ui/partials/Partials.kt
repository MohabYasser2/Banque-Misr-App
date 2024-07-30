package com.groupd.banquemisrapp.ui.partials

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupd.banquemisrapp.R

@Composable
fun namedField(
    text: String,
    message: String,
    modifier: Modifier = Modifier,
    value: String,
    imageRes: Painter = painterResource(
        id = R.drawable.loading_dot
    ),
    trailingIconOn: Boolean = false,
    isPassord: Boolean = false,
    onValueChange: (String) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val passwordIcon = if (passwordVisible) painterResource(id = R.drawable.show)
    else painterResource(id = R.drawable.hide)
    Column {
        Text(
            text = text,
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 24.dp, top = 8.dp),
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
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {


                if (trailingIconOn && isPassord)
                    Image(painter = imageRes, contentDescription = "")
                if (isPassord)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Image(
                            painter = passwordIcon,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .size(24.dp)
                        )
                    }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            )
    }
}