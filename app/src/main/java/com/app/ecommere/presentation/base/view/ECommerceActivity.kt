package com.app.ecommere.presentation.base.view

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.ecommere.presentation.about.view.AboutScreen
import com.app.ecommere.presentation.base.viewmode.ECommerceViewModel
import com.app.ecommere.presentation.checkout.view.CheckoutScreen
import com.app.ecommere.presentation.checkout.viewmodel.CheckoutViewModel
import com.app.ecommere.presentation.detail.view.DetailScreen
import com.app.ecommere.presentation.detail.viewmodel.DetailViewModel
import com.app.ecommere.presentation.home.view.HomeScreen
import com.app.ecommere.presentation.home.viewmodel.HomeViewModel
import com.app.ecommere.presentation.login.view.LoginScreen
import com.app.ecommere.presentation.login.viewmodel.LoginViewModel
import com.app.ecommere.presentation.navigation.Screen
import com.app.ecommere.presentation.register.view.RegisterScreen
import com.app.ecommere.presentation.register.viewmodel.RegisterViewModel
import com.app.ecommere.presentation.splash.view.SplashScreen
import com.app.ecommere.presentation.theme.ECommerceTheme
import com.app.ecommere.utils.Argument
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


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ECommerceApp(vm: ECommerceViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        vm.getUserSession()
    }

    val activity = LocalContext.current as? Activity

    val navController = rememberNavController()

    val state = vm.getUserSession.collectAsStateWithLifecycle().value

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                onNavigate = {
                    if (state) {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
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
                },
                onNavigateToHomeScreen = { navController.navigate(Screen.HomeScreen.route) }
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
            HomeScreen(
                viewModel = viewModel,
                onShoppingBagClicked = {
                    navController.navigate(Screen.CheckoutScreen.route)
                },
                onItemClicked = { navController.navigate(Screen.DetailScreen.createRoute(it)) },
                onLogoutClicked = { navController.navigate(Screen.LoginScreen.route) },
                onPhysicalBackClicked = { activity?.finish() }
            )
        }
        composable(route = Screen.CheckoutScreen.route) {
            val viewModel = hiltViewModel<CheckoutViewModel>()
            CheckoutScreen(
                viewModel = viewModel,
                onBackClicked = { navController.navigateUp() },
                onNavigateToHome = { navController.navigate(Screen.HomeScreen.route) })
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(Argument.PRODUCT_ID) { type = NavType.IntType })
        ) {
            val viewModel = hiltViewModel<DetailViewModel>()
            val productId = it.arguments?.getInt(Argument.PRODUCT_ID)
            DetailScreen(
                viewModel = viewModel,
                productId = productId ?: 0,
                onBackClicked = { navController.navigateUp() },
                onShoppingBagClicked = { navController.navigate(Screen.CheckoutScreen.route) })
        }

        composable(route = Screen.AboutScreen.route) {
            AboutScreen(onLogoutClicked = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ECommercePreview() {
    ECommerceTheme {
        ECommerceApp()
    }
}