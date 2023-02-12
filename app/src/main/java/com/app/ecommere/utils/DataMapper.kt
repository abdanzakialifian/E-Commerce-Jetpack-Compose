package com.app.ecommere.utils

import com.app.ecommere.data.source.local.room.entity.CheckoutEntity
import com.app.ecommere.data.source.local.room.entity.ProductEntity
import com.app.ecommere.data.source.local.room.entity.RegisterEntity
import com.app.ecommere.data.source.local.room.entity.TransactionEntity
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register
import com.app.ecommere.domain.model.Transaction

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
        productCode = input.productCode ?: "",
        productPrice = input.productPrice,
        productQuantity = input.productQuantity,
        unit = input.unit,
        subTotal = input.subTotal,
        currency = input.currency,
        imageName = input.imageName,
        productName = input.productName
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
                    currency = data.currency,
                    imageName = data.imageName,
                    productName = data.productName
                )
            )
        }
        return checkouts
    }

    fun mapProductEntityToProduct(input: ProductEntity): Product = Product(
        productId = input.productId,
        productCode = input.productCode,
        productName = input.productName,
        productPrice = input.productPrice,
        currency = input.currency,
        discount = input.discount,
        dimension = input.dimension,
        unit = input.unit,
        imageName = input.imageName,
        description = input.description
    )

    fun mapRegisterEntityToRegister(input: RegisterEntity) = Register(
        userId = input.userId,
        name = input.name,
        email = input.email,
        password = input.password
    )

    fun mapTransactionToTransactionEntity(input: Transaction): TransactionEntity =
        TransactionEntity(
            documentNumber = input.documentNumber,
            documentCode = input.documentCode,
            user = input.user,
            total = input.total,
            date = input.date
        )
}