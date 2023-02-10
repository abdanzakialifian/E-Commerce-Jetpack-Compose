package com.app.ecommere.domain.interfaces

import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register
import kotlinx.coroutines.flow.Flow

interface ECommerceUseCase {
    fun insertUserRegister(register: Register)
    fun getUserByEmail(email: String,  password: String): Flow<Boolean>
    fun getAllProduct(): Flow<List<Product>>
    fun insertCheckout(checkout: Checkout)
    fun updateProductByProductCode(productCode: String, productQuantity: Int)
    fun getProductByProductCode(productCode: String): Flow<Boolean>
    fun getCheckoutCount(): Flow<Int>
}