package com.app.ecommere.presentation.detail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.ecommere.R
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.presentation.component.ButtonRounded
import com.app.ecommere.presentation.component.CountItem
import com.app.ecommere.presentation.detail.viewmodel.DetailViewModel
import com.app.ecommere.presentation.theme.ECommerceTheme
import com.app.ecommere.utils.UiState
import com.app.ecommere.utils.formatRupiah

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(),
    onBackClicked: () -> Unit,
    onShoppingBagClicked: () -> Unit
) {

    var count by remember {
        mutableStateOf(0)
    }

    var imageName by remember {
        mutableStateOf("")
    }

    var productName by remember {
        mutableStateOf("")
    }

    var productDescription by remember {
        mutableStateOf("")
    }

    var productDiscount by remember {
        mutableStateOf(0)
    }

    var productPrice by remember {
        mutableStateOf(0)
    }

    var tempProductPrice by remember {
        mutableStateOf(0)
    }

    var productCode by remember {
        mutableStateOf("")
    }

    var unit by remember {
        mutableStateOf("")
    }

    var currency by remember {
        mutableStateOf("")
    }

    var checkout by remember {
        mutableStateOf(Checkout())
    }

    var productCount by remember {
        mutableStateOf(1)
    }

    val getProductByIdState = viewModel.getProductById.collectAsStateWithLifecycle().value
    val getCheckoutCountState = viewModel.getCheckoutCount.collectAsStateWithLifecycle().value
    val getProductByProductCodeState =
        viewModel.getProductByProductCode.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.getProductById(productId)
    }

    viewModel.getCheckoutCount()

    LaunchedEffect(getProductByIdState) {
        when (getProductByIdState) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                val data = getProductByIdState.data
                imageName = data.imageName ?: ""
                productName = data.productName ?: ""
                productDescription = data.description ?: ""
                productDiscount = data.discount ?: 0
                productPrice = data.productPrice ?: 0
                tempProductPrice = data.productPrice ?: 0
                productCode = data.productCode ?: ""
                unit = data.unit ?: ""
                currency = data.currency ?: ""
            }
            is UiState.Error -> {}
        }
    }

    LaunchedEffect(getCheckoutCountState) {
        when (getCheckoutCountState) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                count = getCheckoutCountState.data
            }
            is UiState.Error -> {}
        }
    }

    if (viewModel.isButtonClicked) {
        when (getProductByProductCodeState) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                if (getProductByProductCodeState.data) {
                    viewModel.updateProductByProductCode(
                        productCode = productCode,
                        productQuantity = productCount
                    )
                } else {
                    viewModel.insertCheckout(checkout)
                }
                onShoppingBagClicked()
                viewModel.isButtonClicked = false
            }
            is UiState.Error -> {}
        }
    }

    DetailContent(
        modifier = modifier,
        count = count,
        imageName = imageName,
        productName = productName,
        productDescription = productDescription,
        productDiscount = productDiscount,
        productPrice = if (productCount != 1) tempProductPrice else productPrice,
        productCount = productCount,
        onBackClicked = onBackClicked,
        onShoppingBagClicked = onShoppingBagClicked,
        onButtonClicked = {
            val discount = (tempProductPrice / 100) * productDiscount
            val price = tempProductPrice.minus(discount)

            checkout = Checkout(
                documentNumber = "00${productId}",
                documentCode = "TRX",
                productCode = productCode,
                productPrice = productPrice,
                productQuantity = productCount,
                unit = unit,
                subTotal = price,
                currency = currency,
                imageName = imageName,
                productName = productName
            )
            viewModel.getProductByProductCode(productCode)
            viewModel.isButtonClicked = true
        },
        onDeleteClicked = {
            if (productCount != 1) {
                productCount -= 1
                tempProductPrice -= productPrice
            }
        },
        onAddClicked = {
            productCount += 1
            tempProductPrice += productPrice
        }
    )
}

@Composable
fun DetailContent(
    count: Int,
    imageName: String,
    productName: String,
    productDescription: String,
    productDiscount: Int,
    productPrice: Int,
    productCount: Int,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onShoppingBagClicked: () -> Unit,
    onButtonClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val context = LocalContext.current

    val drawableId = remember(imageName) {
        context.resources.getIdentifier(
            if (imageName != "") imageName else "ic_placeholder", "drawable", context.packageName
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Box {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(
                            onClick = onBackClicked,
                            interactionSource = interactionSource,
                            indication = null
                        ),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Icon Back",
                    colorFilter = ColorFilter.tint(color = Color.Black),
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    text = stringResource(id = R.string.detail_product),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Box(Modifier.align(Alignment.CenterEnd)) {
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
            }
            Image(
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                painter = painterResource(id = drawableId),
                contentDescription = "Image Detail"
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = productName,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = productDescription,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
            val discount = (productPrice / 100) * productDiscount
            val price = productPrice.minus(discount)

            if (discount != 0) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = discount.toDouble().formatRupiah(),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = if (discount != 0) 6.dp else 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = price.toDouble().formatRupiah(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                CountItem(
                    count = productCount.toString(),
                    onDeleteClicked = onDeleteClicked,
                    onAddClicked = onAddClicked
                )
            }
        }

        ButtonRounded(
            text = stringResource(id = R.string.add_to_bag),
            enabled = true,
            onClick = onButtonClicked
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun DetailScreenPreview() {
    ECommerceTheme {
        DetailScreen(productId = 0, onBackClicked = {}, onShoppingBagClicked = {})
    }
}