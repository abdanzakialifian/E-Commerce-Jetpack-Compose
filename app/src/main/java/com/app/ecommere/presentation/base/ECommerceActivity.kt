package com.app.ecommere.presentation.base

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.ecommere.presentation.home.view.HomeScreen
import com.app.ecommere.presentation.home.viewmodel.HomeViewModel
import com.app.ecommere.presentation.login.view.LoginScreen
import com.app.ecommere.presentation.login.viewmodel.LoginViewModel
import com.app.ecommere.presentation.navigation.Screen
import com.app.ecommere.presentation.register.view.RegisterScreen
import com.app.ecommere.presentation.register.viewmodel.RegisterViewModel
import com.app.ecommere.presentation.splash.SplashScreen
import com.app.ecommere.presentation.theme.ECommerceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ECommerceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ECommerceApp()
                }
            }
        }
    }
}

@Composable
fun ECommerceApp() {
    val activity = LocalContext.current as? Activity
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                onNavigate = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(route = Screen.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                viewModel = viewModel,
                onBackClicked = { activity?.finish() },
                onPhysicalBackClicked = { activity?.finish() },
                onTextClicked = {
                    navController.navigate(Screen.RegisterScreen.route)
                }
            )
        }
        composable(route = Screen.RegisterScreen.route) {
            val viewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(
                viewModel = viewModel,
                onBackClicked = { activity?.finish() },
                onPhysicalBackClicked = { activity?.finish() },
                onTextClicked = {
                    navController.navigate(Screen.LoginScreen.route)
                }
            )
        }
        composable(route = Screen.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ECommercePreview() {
    ECommerceApp()
}