package com.app.ecommere.presentation.about.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ecommere.R
import com.app.ecommere.presentation.component.ButtonRounded
import com.app.ecommere.presentation.theme.ECommerceTheme

@Composable
fun AboutScreen(modifier: Modifier = Modifier, onLogoutClicked: () -> Unit) {
    Column(modifier = modifier.padding(24.dp)) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.photo_profile),
            contentDescription = "Image Profile"
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(id = R.string.name_developer),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(id = R.string.email_developer),
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        ButtonRounded(
            modifier = Modifier.padding(top = 30.dp),
            text = stringResource(id = R.string.logout),
            enabled = true,
            onClick = onLogoutClicked
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun AboutScreenPreview() {
    ECommerceTheme {
        AboutScreen(onLogoutClicked = {})
    }
}