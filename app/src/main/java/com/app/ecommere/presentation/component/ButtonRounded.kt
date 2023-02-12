package com.app.ecommere.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ecommere.R
import com.app.ecommere.presentation.theme.ECommerceTheme

@Composable
fun ButtonRounded(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.blue),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoundedButtonPreview() {
    ECommerceTheme {
        ButtonRounded(text = stringResource(id = R.string.sign_in), onClick = {})
    }
}