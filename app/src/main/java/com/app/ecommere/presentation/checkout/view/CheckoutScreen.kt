package com.app.ecommere.presentation.checkout.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.ecommere.R
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Transaction
import com.app.ecommere.presentation.checkout.viewmodel.CheckoutViewModel
import com.app.ecommere.presentation.component.ButtonRounded
import com.app.ecommere.presentation.component.CheckoutItem
import com.app.ecommere.presentation.component.CustomAlertDialog
import com.app.ecommere.presentation.theme.ECommerceTheme
import com.app.ecommere.utils.UiState
import com.app.ecommere.utils.formatRupiah
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = viewModel(),
    onBackClicked: () -> Unit,
    onNavigateToHome: () -> Unit,
) {

    var checkouts by remember {
        mutableStateOf(listOf<Checkout>())
    }

    var totalPrice by remember {
        mutableStateOf(0)
    }

    val getAllCheckoutState = viewModel.getAllCheckout.collectAsStateWithLifecycle().value
    val email = viewModel.email.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.getAllCheckout()
        viewModel.getUserData()
    }

    LaunchedEffect(getAllCheckoutState) {
        when (getAllCheckoutState) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                checkouts = getAllCheckoutState.data
                var total = 0
                getAllCheckoutState.data.forEach {
                    total += it.subTotal ?: 0
                }
                totalPrice = total
            }
            is UiState.Error -> {}
        }
    }

    if (viewModel.isButtonClicked) {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        val transaction = Transaction(
            documentCode = "TRX", user = email, total = totalPrice, date = currentDate
        )
        viewModel.insertTransaction(transaction)
        CustomAlertDialog(
            title = stringResource(id = R.string.success),
            subTitle = stringResource(id = R.string.sub_title_checkout),
            onConfirmClicked = {
                viewModel.deleteCheckout()
                onNavigateToHome()
                viewModel.isButtonClicked = false
            },
            onDismissClicked = {},
        )
    }

    CheckoutContent(
        modifier = modifier,
        checkouts,
        totalPrice,
        onBackClicked,
        onButtonConfirmClicked = {
            if (checkouts.isNotEmpty()) {
                viewModel.isButtonClicked = true
            }
        },
    )
}

@Composable
fun CheckoutContent(
    modifier: Modifier = Modifier,
    checkouts: List<Checkout>,
    totalPrice: Int,
    onBackClicked: () -> Unit,
    onButtonConfirmClicked: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Box(modifier = Modifier.padding(16.dp)) {
                Image(
                    modifier = Modifier.clickable(
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
                    text = stringResource(id = R.string.checkout),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(checkouts, key = { it.productCode ?: "" }) { checkout ->
                    val drawableId = remember(checkout.imageName) {
                        context.resources.getIdentifier(
                            checkout.imageName, "drawable", context.packageName
                        )
                    }
                    CheckoutItem(
                        painter = painterResource(id = drawableId),
                        productName = checkout.productName ?: "",
                        productQuantity = checkout.productQuantity ?: 0,
                        productUnit = checkout.unit.toString(),
                        subTotal = checkout.subTotal?.toDouble()?.formatRupiah() ?: ""
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp, end = 18.dp, bottom = 14.dp, top = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = stringResource(id = R.string.total),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        Text(
                            text = totalPrice.toDouble().formatRupiah(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
                item {
                    ButtonRounded(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        text = stringResource(id = R.string.confirm),
                        enabled = true,
                        onClick = onButtonConfirmClicked
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun CheckoutScreenPreview() {
    ECommerceTheme {
        CheckoutScreen(onBackClicked = {}, onNavigateToHome = {})
    }
}