package com.app.ecommere.presentation.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.ecommere.R
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.presentation.component.CustomAlertDialog
import com.app.ecommere.presentation.component.ProductItem
import com.app.ecommere.presentation.component.SearchBar
import com.app.ecommere.presentation.home.viewmodel.HomeViewModel
import com.app.ecommere.presentation.theme.ECommerceTheme
import com.app.ecommere.utils.UiState
import com.app.ecommere.utils.formatRupiah
import java.util.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onShoppingBagClicked: () -> Unit,
    onItemClicked: (Int) -> Unit,
    onLogoutClicked: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllProduct()
        viewModel.setUserSession(true)
        viewModel.getUserData()
    }

    viewModel.getCheckoutCount()

    var checkout by remember {
        mutableStateOf(Checkout())
    }

    var search by remember {
        mutableStateOf("")
    }

    var count by remember {
        mutableStateOf(0)
    }

    var products by remember {
        mutableStateOf(listOf<Product>())
    }

    var name by remember {
        mutableStateOf("")
    }

    when (val getUserDataState = viewModel.getUserData.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {}
        is UiState.Success -> name = getUserDataState.data.name ?: ""
        is UiState.Error -> {}
    }

    when (val getCheckoutCountState =
        viewModel.getCheckoutCount.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {}
        is UiState.Success -> count = getCheckoutCountState.data
        is UiState.Error -> {}
    }

    when (val getAllProductState = viewModel.getAllProduct.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {}
        is UiState.Success -> products = getAllProductState.data
        is UiState.Error -> {}
    }

    if (viewModel.isButtonClicked) {
        when (val getProductByProductCodeState =
            viewModel.getProductByProductCode.collectAsStateWithLifecycle().value) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                if (getProductByProductCodeState.data) {
                    viewModel.updateProductByProductCode(
                        productCode = checkout.productCode ?: "",
                        productQuantity = checkout.productQuantity ?: 0
                    )
                    viewModel.isButtonClicked = false
                } else {
                    viewModel.insertCheckout(checkout)
                    viewModel.isButtonClicked = false
                }
            }
            is UiState.Error -> {}
        }
    }

    if (viewModel.isLogoutClicked) {
        CustomAlertDialog(title = stringResource(id = R.string.logout),
            subTitle = stringResource(
                id = R.string.sub_title_logout
            ),
            isShowNegativeButton = true,
            onConfirmClicked = {
                onLogoutClicked()
                viewModel.isLogoutClicked = false
            },
            onDismissClicked = { viewModel.isLogoutClicked = false })

    }

    HomeContent(
        modifier = modifier,
        products,
        search,
        count,
        name,
        onSearchChange = { search = it },
        onAddClicked = { product ->
            checkout = Checkout(
                documentNumber = "00${product.productId}",
                documentCode = "TRX",
                productCode = product.productCode,
                productPrice = product.productPrice,
                productQuantity = 1,
                unit = product.unit,
                subTotal = product.productPrice,
                currency = product.currency,
                imageName = product.imageName,
                productName = product.productName
            )
            viewModel.getProductByProductCode(product.productCode ?: "")
            viewModel.isButtonClicked = true
        },
        onShoppingBagClicked = onShoppingBagClicked,
        onItemClicked = onItemClicked,
        onLogoutClicked = { viewModel.isLogoutClicked = true }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    products: List<Product>,
    search: String,
    count: Int,
    name: String,
    onSearchChange: (String) -> Unit,
    onAddClicked: (Product) -> Unit,
    onShoppingBagClicked: () -> Unit,
    onItemClicked: (Int) -> Unit,
    onLogoutClicked: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = "Hello $name,",
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
            Row {
                Box {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 0.5.dp, shape = CircleShape, color = Color.Gray
                            )
                            .clickable(
                                onClick = onShoppingBagClicked,
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = false)
                            )
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(18.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_shopping_bag),
                            contentDescription = "Shopping Bag",
                        )

                    }

                    if (count != 0) {
                        Box(
                            modifier = Modifier
                                .background(
                                    shape = CircleShape,
                                    color = colorResource(id = R.color.blue)
                                )
                                .align(
                                    Alignment.TopEnd
                                )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 3.dp, horizontal = 6.dp)
                                    .align(Alignment.Center),
                                text = count.toString(),
                                color = Color.White,
                                fontSize = 8.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(start = 6.dp))
                Box(
                    modifier = Modifier
                        .border(
                            width = 0.5.dp, shape = CircleShape, color = Color.Gray
                        )
                        .clickable(
                            onClick = onLogoutClicked,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false)
                        )
                ) {
                    Image(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(16.dp)
                            .align(Alignment.Center),
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "Shopping Bag",
                    )

                }
            }
        }
        SearchBar(modifier = Modifier.padding(top = 20.dp),
            hint = stringResource(id = R.string.hint_search),
            value = search,
            onValueChange = { onSearchChange(it) })

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 20.dp),
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(products, key = { it.productCode ?: "" }) { product ->
                val drawableId = remember(product.imageName) {
                    context.resources.getIdentifier(
                        product.imageName, "drawable", context.packageName
                    )
                }

                val discount = (product.productPrice?.div(100))?.times(product.discount ?: 0)
                val price = product.productPrice?.minus(discount ?: 0)

                ProductItem(image = painterResource(id = drawableId),
                    title = product.productName ?: "",
                    description = product.description ?: "",
                    discount = discount?.toDouble()?.formatRupiah() ?: "",
                    price = price?.toDouble()?.formatRupiah() ?: "",
                    onItemClicked = { onItemClicked(product.productId ?: 0) },
                    onAddClicked = { onAddClicked(product) })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun HomeScreenPreview() {
    ECommerceTheme {
        HomeScreen(
            onShoppingBagClicked = {},
            onItemClicked = {},
            onLogoutClicked = {})
    }
}