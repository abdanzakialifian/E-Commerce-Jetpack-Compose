package com.app.ecommere.presentation.navigation

import com.app.ecommere.utils.Route

sealed class Screen(val route: String) {
    object SplashScreen : Screen(Route.SPLASH_ROUTE)
    object LoginScreen : Screen(Route.LOGIN_ROUTE)
    object RegisterScreen : Screen(Route.REGISTER_ROUTE)
    object HomeScreen : Screen(Route.HOME_ROUTE)
    object CheckoutScreen : Screen(Route.CHECKOUT_ROUTE)
    object DetailScreen : Screen(Route.DETAIL_ROUTE) {
        fun createRoute(productId: Int): String = "detail_screen/$productId"
    }
    object AboutScreen : Screen(Route.ABOUT_ROUTE)
}
