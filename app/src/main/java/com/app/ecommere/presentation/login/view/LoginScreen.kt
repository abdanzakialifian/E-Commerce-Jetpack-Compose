package com.app.ecommere.presentation.login.view

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.ecommere.R
import com.app.ecommere.presentation.component.ButtonRounded
import com.app.ecommere.presentation.component.FormInput
import com.app.ecommere.presentation.login.viewmodel.LoginViewModel
import com.app.ecommere.presentation.theme.ECommerceTheme
import com.app.ecommere.utils.UiState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    onPhysicalBackClicked: () -> Unit,
    onTextClicked: () -> Unit
) {
    val context = LocalContext.current

    when (val uiState = viewModel.getUserByEmail.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            if (uiState.data == true) {
                Toast.makeText(context, "MASUK", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "GAGAL", Toast.LENGTH_SHORT).show()
            }
        }
        is UiState.Error -> {}
    }

    LoginScreenContent(
        modifier = modifier,
        onBackClicked = onBackClicked,
        onPhysicalBackClicked = onPhysicalBackClicked,
        onTextClicked = onTextClicked,
        onSubmitClicked = { email, password ->
            viewModel.getUserByEmail(email, password)
        }
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onPhysicalBackClicked: () -> Unit,
    onTextClicked: () -> Unit,
    onSubmitClicked: (String, String) -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    BackHandler {
        onPhysicalBackClicked()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .width(width = 70.dp)
                .padding(top = 32.dp)
                .clickable {
                    onBackClicked()
                }, elevation = 0.dp, shape = RoundedCornerShape(
                topStart = 0.dp, bottomStart = 0.dp, topEnd = 10.dp, bottomEnd = 10.dp
            )
        ) {
            Image(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.light_blue))
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Button",
            )
        }
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = stringResource(id = R.string.title_sign_in),
                color = colorResource(id = R.color.blue),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            FormInput(modifier = Modifier.padding(top = 32.dp),
                hint = stringResource(id = R.string.email_address),
                value = email,
                onValueChange = { email = it })
            FormInput(modifier = Modifier.padding(top = 15.dp),
                hint = stringResource(id = R.string.password),
                value = password,
                onValueChange = { password = it })
            ButtonRounded(
                modifier = Modifier.padding(top = 32.dp),
                text = stringResource(id = R.string.sign_in),
                onClick = { onSubmitClicked(email, password) }
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(id = R.string.content_sign_up),
                    color = Color.Black,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.clickable {
                        onTextClicked()
                    },
                    text = stringResource(id = R.string.sign_up),
                    color = colorResource(id = R.color.blue),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun LoginScreenPreview() {
    ECommerceTheme {
        LoginScreen(onBackClicked = {}, onPhysicalBackClicked = {}, onTextClicked = {})
    }
}