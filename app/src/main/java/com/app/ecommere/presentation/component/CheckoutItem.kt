package com.app.ecommere.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ecommere.R

@Composable
fun CheckoutItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    productName: String,
    productQuantity: Int,
    productUnit: String,
    subTotal: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Image(
            modifier = Modifier
                .size(120.dp)
                .padding(10.dp),
            painter = painter,
            contentDescription = null
        )
        Column(modifier = Modifier
            .padding(start = 10.dp)
            .align(Alignment.CenterVertically)) {
            Text(text = productName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row(modifier = Modifier.padding(top = 4.dp)) {
                Card(
                    shape = RoundedCornerShape(4.dp), elevation = 0.dp, border = BorderStroke(
                        0.5.dp, color = colorResource(
                            id = R.color.light_blue
                        )
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                        text = productQuantity.toString(),
                        fontSize = 12.sp
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .align(Alignment.CenterVertically),
                    text = productUnit,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
            Row(modifier = Modifier.padding(top = 4.dp)) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.sub_total),
                    color = Color.Black,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = subTotal,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutItemPreview() {
    CheckoutItem(
        painter = painterResource(id = R.drawable.mie_sedap),
        productName = "Mie Sedap",
        productQuantity = 4,
        productUnit = "PCS",
        subTotal = "Rp12.000"
    )
}