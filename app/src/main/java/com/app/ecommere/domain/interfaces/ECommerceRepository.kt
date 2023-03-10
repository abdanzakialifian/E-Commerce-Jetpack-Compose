package com.app.ecommere.domain.interfaces

import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register
import com.app.ecommere.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface ECommerceRepository {
    fun insertUserRegister(register: Register)
    fun getUserByEmail(email: String, password: String): Flow<Boolean>
    fun getAllProduct(): Flow<List<Product>>
    fun insertCheckout(checkout: Checkout)
    fun updateProductByProductCode(productCode: String, productQuantity: Int)
    fun getProductByProductCode(productCode: String): Flow<Boolean>
    fun getCheckoutCount(): Flow<Int>
    fun getAllCheckout(): Flow<List<Checkout>>
    fun getProductById(productId: Int): Flow<Product>
    fun saveUserSession(isLogin: Boolean)
    fun getUserSession(): Flow<Boolean>
    fun getUserData(email: String): Flow<Register>
    fun saveUserData(email: String)
    fun getUserData(): Flow<String>
    fun insertTransaction(transaction: Transaction)
    fun deleteCheckout()
    fun searchProduct(productName: String): Flow<List<Product>>
}