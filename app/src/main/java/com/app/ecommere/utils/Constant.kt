package com.app.ecommere.utils

object Route {
    const val SPLASH_ROUTE = "splash_screen"
    const val LOGIN_ROUTE = "login_screen"
    const val REGISTER_ROUTE = "register_screen"
    const val HOME_ROUTE = "home_screen"
    const val CHECKOUT_ROUTE = "checkout_screen"
    const val DETAIL_ROUTE = "detail_screen/{${Argument.PRODUCT_ID}}"
    const val ABOUT_ROUTE = "about_screen"
}

object Argument {
    const val PRODUCT_ID = "productId"
}