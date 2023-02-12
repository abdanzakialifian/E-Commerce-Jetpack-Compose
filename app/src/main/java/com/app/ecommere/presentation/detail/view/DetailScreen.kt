package com.app.ecommere.presentation.detail.view

import android.util.Log
import android.widget.Toast
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
import com.app.ecommere.presentation.detail.viewmodel.DetailViewModel
import com.app.ecommere.utils.UiState
import com.app.ecommere.utils.formatRupiah

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(),
    productId: Int,
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

    LaunchedEffect(key1 = Unit) {
        viewModel.getProductById(productId)
        viewModel.getCheckoutCount()
    }

    when (val getProductByIdState = viewModel.getProductById.collectAsState().value) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            val data = getProductByIdState.data
            imageName = data.imageName ?: ""
            productName = data.productName ?: ""
            productDescription = data.description ?: ""
            productDiscount = data.discount ?: 0
            productPrice = data.productPrice ?: 0
            productCode = data.productCode ?: ""
            unit = data.unit ?: ""
            currency = data.currency ?: ""
        }
        is UiState.Error -> {}
    }

    when (val getCheckoutCountState = viewModel.getCheckoutCount.collectAsState().value) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            count = getCheckoutCountState.data
        }
        is UiState.Error -> {}
    }

    if (viewModel.isButtonClicked) {
        when (val getProductByProductCodeState =
            viewModel.getProductByProductCode.collectAsStateWithLifecycle().value) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                onShoppingBagClicked()
                if (getProductByProductCodeState.data) {
                    viewModel.updateProductByProductCode(
                        productCode = productCode,
                        productQuantity = checkout.productQuantity ?: 0
                    )
                } else {
                    viewModel.insertCheckout(checkout)
                }
                viewModel.isButtonClicked = false
            }
            is UiState.Error -> {}
        }
    }

    DetailContent(
        modifier,
        count = count,
        imageName = imageName,
        productName = productName,
        productDescription = productDescription,
        productDiscount = productDiscount,
        productPrice = productPrice,
        onBackClicked = onBackClicked,
        onShoppingBagClicked = onShoppingBagClicked,
        onButtonClicked = {
            checkout = Checkout(
                documentNumber = "00${productId}",
                documentCode = "TRX",
                productCode = productCode,
                productPrice = productPrice,
                productQuantity = 1,
                unit = unit,
                subTotal = productPrice,
                currency = currency,
                imageName = imageName,
                productName = productName
            )
            viewModel.getProductByProductCode(productCode)
            viewModel.isButtonClicked = true
        }
    )
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    count: Int,
    imageName: String,
    productName: String,
    productDescription: String,
    productDiscount: Int,
    productPrice: Int,
    onBackClicked: () -> Unit,
    onShoppingBagClicked: () -> Unit,
    onButtonClicked: () -> Unit,
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
                    contentDescription = null,
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
                contentDescription = null
            )
            Text(text = productName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = productDescription,
                fontSize = 14.sp,
                color = Color.Gray
            )
            val discount = ((productPrice / 100) * productDiscount)
            val price = (productPrice.minus(discount))

            if (discount != 0) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = discount.toDouble().formatRupiah(),
                    color = Color.Gray,
                    fontSize = 12.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = price.toDouble().formatRupiah(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        ButtonRounded(text = stringResource(id = R.string.add_to_bag), onClick = onButtonClicked)
    }
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun DetailScreenPreview() {
    DetailScreen(productId = 0, onBackClicked = {}, onShoppingBagClicked = {})
}