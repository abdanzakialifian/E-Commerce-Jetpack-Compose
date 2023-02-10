package com.app.ecommere.domain.model

data class Checkout(
    val documentNumber: String? = null,

    val documentCode: String? = null,

    val productCode: String? = null,

    val productPrice: Int? = null,

    val productQuantity: Int? = null,

    val unit: String? = null,

    val subTotal: Int? = null,

    val currency: String? = null,
)