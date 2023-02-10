package com.app.ecommere.domain.model

data class Product(
    val productId: Int? = null,

    val productCode: String? = null,

    val productName: String? = null,

    val productPrice: Int? = null,

    val currency: String? = null,

    val discount: Int? = null,

    val dimension: String? = null,

    val unit: String? = null,

    val imageName: String? = null,

    val description: String? = null
)