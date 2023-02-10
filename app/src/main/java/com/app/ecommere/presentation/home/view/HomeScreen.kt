package com.app.ecommere.presentation.home.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.ecommere.R
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.presentation.component.ProductItem
import com.app.ecommere.presentation.component.SearchBar
import com.app.ecommere.presentation.home.viewmodel.HomeViewModel
import com.app.ecommere.presentation.theme.ECommerceTheme
import com.app.ecommere.utils.UiState
import java.text.NumberFormat
import java.util.*

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()) {

    var products by remember {
        mutableStateOf(listOf<Product>())
    }

    var checkoutModel by remember {
        mutableStateOf(Checkout())
    }

    val context = LocalContext.current

    when (val state = viewModel.getAllProduct.collectAsState().value) {
        is UiState.Loading -> {}
        is UiState.Success -> products = state.data ?: emptyList()
        is UiState.Error -> {}
    }

    when(val state = viewModel.getProductByProductCode.collectAsState().value) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            if (state.data == true) {
                Toast.makeText(context, "FOUND", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertCheckout(checkoutModel)
                Toast.makeText(context, "NOT FOUND", Toast.LENGTH_SHORT).show()
            }
        }
        is UiState.Error -> {}
    }

    HomeContent(
        modifier = modifier,
        products,
        onAddClicked = { productCode, checkout ->
            viewModel.getProductByProductCode(productCode)
            checkoutModel = checkout
        }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onAddClicked: (String, Checkout) -> Unit
) {
    val context = LocalContext.current

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
                        width = 0.5.dp, shape = CircleShape, color = Color.Gray
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
        SearchBar(modifier = Modifier.padding(top = 20.dp),
            hint = stringResource(id = R.string.hint_search),
            value = searchValue,
            onValueChange = { searchValue = it })

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 20.dp),
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(products) { product ->
                val drawableId = remember(product.imageName) {
                    context.resources.getIdentifier(
                        product.imageName,
                        "drawable",
                        context.packageName
                    )
                }

                val discount = (product.productPrice?.div(100))?.times(product.discount ?: 0)
                val price = product.productPrice?.minus(discount ?: 0)

                ProductItem(
                    image = painterResource(id = drawableId),
                    title = product.productName ?: "",
                    description = product.description ?: "",
                    discount = formatRupiah(discount?.toDouble() ?: 0.0),
                    price = formatRupiah(price?.toDouble() ?: 0.0),
                    onItemClicked = {},
                    onAddClicked = { 
                        val checkout = Checkout(
                            documentNumber = "00${product.productId}",
                            documentCode = "TRX",
                            productCode = product.productCode,
                            productPrice = product.productPrice,
                            productQuantity = 1,
                            unit = product.unit,
                            subTotal = product.productPrice,
                            currency = product.currency
                        )
                        onAddClicked(product.productCode ?: "", checkout)
                    }
                )
            }
        }
    }
}

fun formatRupiah(price: Double): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(price)
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun HomeScreenPreview() {
    ECommerceTheme {
        HomeScreen()
    }
}