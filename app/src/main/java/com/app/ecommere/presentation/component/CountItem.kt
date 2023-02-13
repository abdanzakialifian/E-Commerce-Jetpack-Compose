package com.app.ecommere.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ecommere.R
import com.app.ecommere.presentation.theme.ECommerceTheme

@Composable
fun CountItem(
    count: String,
    modifier: Modifier = Modifier,
    onDeleteClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    shape = CircleShape,
                    color = colorResource(id = R.color.light_blue)
                )
                .clickable(
                    onClick = onDeleteClicked,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                )
        ) {
            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "Delete products"
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .align(Alignment.CenterVertically),
            text = count,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Box(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.blue),
                    shape = CircleShape
                )
                .clickable(
                    onClick = onAddClicked,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                )
        ) {
            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add products"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountItemPreview() {
    ECommerceTheme {
        CountItem(count = "1", onDeleteClicked = {}, onAddClicked = {})
    }
}