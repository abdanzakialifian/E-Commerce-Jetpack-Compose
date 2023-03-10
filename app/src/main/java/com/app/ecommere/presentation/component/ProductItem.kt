package com.app.ecommere.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ecommere.R
import com.app.ecommere.presentation.theme.ECommerceTheme

@Composable
fun ProductItem(
    image: Painter,
    title: String,
    description: String,
    discount: String,
    price: String,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    Card(modifier = modifier, shape = RoundedCornerShape(10.dp)) {
        Column(modifier = Modifier
            .clickable { onItemClicked() }
            .padding(14.dp)) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                painter = image,
                contentDescription = "Image Product"
            )
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(text = description, color = Color.Gray, fontSize = 12.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.align(Alignment.Bottom)) {
                    if (discount != "Rp0") {
                        Text(
                            text = discount,
                            color = Color.Gray,
                            fontSize = 10.sp,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )
                    }
                    Text(
                        text = price,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.blue),
                            shape = CircleShape
                        )
                        .align(Alignment.CenterVertically)
                        .clickable(
                            onClick = onAddClicked,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false)
                        ),
                ) {
                    Image(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(18.dp),
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add Icon"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ECommerceTheme {
        ProductItem(
            image = painterResource(id = R.drawable.mie_sedap),
            title = "Mie Sedap",
            description = "The winning noddle",
            discount = "Rp0",
            price = "Rp 2.500",
            onItemClicked = {},
            onAddClicked = {}
        )
    }
}