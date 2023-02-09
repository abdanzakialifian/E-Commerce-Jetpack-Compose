package com.app.ecommere.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ecommere.R

@Composable
fun FormInput(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), elevation = 2.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            label = { Text(text = hint) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedLabelColor = colorResource(id = R.color.blue),
                cursorColor = colorResource(id = R.color.blue)
            ),
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
    FormInput(
        hint = stringResource(id = R.string.email_address),
        value = value,
        onValueChange = { value = it })
}