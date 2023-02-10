package com.app.ecommere.utils

import com.app.ecommere.data.source.local.entity.CheckoutEntity
import com.app.ecommere.data.source.local.entity.ProductEntity
import com.app.ecommere.data.source.local.entity.RegisterEntity
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register

object DataMapper {
    fun mapRegisterToRegisterEntity(input: Register): RegisterEntity =
        RegisterEntity(name = input.name, email = input.email, password = input.password)

    fun mapProductEntityToProduct(input: List<ProductEntity>): List<Product> {
        val products = mutableListOf<Product>()
        input.forEach { data ->
            products.add(
                Product(
                    productId = data.productId,
                    productCode = data.productCode,
                    productName = data.productName,
                    productPrice = data.productPrice,
                    currency = data.currency,
                    discount = data.discount,
                    dimension = data.dimension,
                    unit = data.unit,
                    imageName = data.imageName,
                    description = data.description
                )
            )
        }
        return products
    }

    fun mapCheckoutToCheckoutEntity(input: Checkout): CheckoutEntity = CheckoutEntity(
        documentNumber = input.documentNumber ?: "",
        documentCode = input.documentCode,
        productCode = input.productCode,
        productPrice = input.productPrice,
        productQuantity = input.productQuantity,
        unit = input.unit,
        subTotal = input.subTotal,
        currency = input.currency
    )

    fun mapCheckoutEntityToCheckout(input: List<CheckoutEntity>): List<Checkout> {
        val checkouts = mutableListOf<Checkout>()
        input.forEach { data ->
            checkouts.add(
                Checkout(
                    documentNumber = data.documentNumber,
                    documentCode = data.documentCode,
                    productCode = data.productCode,
                    productPrice = data.productPrice,
                    productQuantity = data.productQuantity,
                    unit = data.unit,
                    subTotal = data.subTotal,
                    currency = data.currency
                )
            )
        }
        return checkouts
    }
}