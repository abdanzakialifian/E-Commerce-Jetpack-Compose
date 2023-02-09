package com.app.ecommere.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ecommere.R
import com.app.ecommere.presentation.component.ProductItem
import com.app.ecommere.presentation.component.SearchBar
import com.app.ecommere.presentation.theme.ECommerceTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    HomeContent(modifier = modifier)
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    var searchValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = "Hello Abdan Zaki Alifian,",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = stringResource(id = R.string.asking_buying),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            Box(
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        shape = CircleShape,
                        color = Color.Gray
                    )
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(18.dp),
                    painter = painterResource(id = R.drawable.ic_shopping_bag),
                    contentDescription = "Shopping Bag",
                )
            }
        }
        SearchBar(
            modifier = Modifier.padding(top = 20.dp),
            hint = stringResource(id = R.string.hint_search),
            value = searchValue,
            onValueChange = { searchValue = it }
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 20.dp),
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(10) {
                ProductItem(
                    image = painterResource(id = R.drawable.mie_sedap),
                    title = "Mie Sedap",
                    description = "The best noddle",
                    price = "Rp 2.500",
                    onItemClicked = {},
                    onAddClicked = {}
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun HomeScreenPreview() {
    ECommerceTheme {
        HomeScreen()
    }
}