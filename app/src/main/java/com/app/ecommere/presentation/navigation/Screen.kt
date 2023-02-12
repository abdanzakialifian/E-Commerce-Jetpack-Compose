package com.app.ecommere.presentation.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen/{email}/{password}") {
        fun createRoute(email: String, password: String): String = "home_screen/$email/$password"
    }

    object CheckoutScreen : Screen("checkout_screen")
    object DetailScreen : Screen("detail_screen/{productId}") {
        fun createRoute(productId: Int): String = "detail_screen/$productId"
    }
}
