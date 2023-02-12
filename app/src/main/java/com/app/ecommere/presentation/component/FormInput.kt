package com.app.ecommere.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ecommere.R
import com.app.ecommere.presentation.theme.ECommerceTheme

@Composable
fun FormInput(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    isToggleActive: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), elevation = 2.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            singleLine = true,
            visualTransformation = if (isToggleActive) if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation() else VisualTransformation.None,
            label = { Text(text = hint) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedLabelColor = colorResource(id = R.color.blue),
                cursorColor = colorResource(id = R.color.blue)
            ),
            trailingIcon = {
                if (isToggleActive) {
                    val image =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            },
            onValueChange = { onValueChange(it) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormInputPreview() {
    var value by remember {
        mutableStateOf("")
    }
    ECommerceTheme {
        FormInput(hint = stringResource(id = R.string.email_address),
            value = value,
            onValueChange = { value = it })
    }
}