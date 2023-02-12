package com.app.ecommere.domain.usecase

import com.app.ecommere.domain.interfaces.ECommerceRepository
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register
import com.app.ecommere.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ECommerceUseCaseImpl @Inject constructor(private val eCommerceRepository: ECommerceRepository) :
    ECommerceUseCase {
    override fun insertUserRegister(register: Register) {
        eCommerceRepository.insertUserRegister(register)
    }

    override fun getUserByEmail(email: String, password: String): Flow<Boolean> =
        eCommerceRepository.getUserByEmail(email, password)

    override fun getAllProduct(): Flow<List<Product>> = eCommerceRepository.getAllProduct()
    override fun insertCheckout(checkout: Checkout) {
        eCommerceRepository.insertCheckout(checkout)
    }

    override fun updateProductByProductCode(productCode: String, productQuantity: Int) {
        eCommerceRepository.updateProductByProductCode(productCode, productQuantity)
    }

    override fun getProductByProductCode(productCode: String): Flow<Boolean> =
        eCommerceRepository.getProductByProductCode(productCode)

    override fun getCheckoutCount(): Flow<Int> = eCommerceRepository.getCheckoutCount()

    override fun getAllCheckout(): Flow<List<Checkout>> = eCommerceRepository.getAllCheckout()

    override fun getProductById(productId: Int): Flow<Product> =
        eCommerceRepository.getProductById(productId)

    override fun saveUserSession(isLogin: Boolean) {
        eCommerceRepository.saveUserSession(isLogin)
    }

    override fun getUserSession(): Flow<Boolean> = eCommerceRepository.getUserSession()
    override fun getUserData(email: String): Flow<Register> = eCommerceRepository.getUserData(email)

    override fun getUserData(): Flow<String> = eCommerceRepository.getUserData()

    override fun saveUserData(email: String) {
        eCommerceRepository.saveUserData(email)
    }

    override fun insertTransaction(transaction: Transaction) {
        eCommerceRepository.insertTransaction(transaction)
    }

    override fun deleteCheckout() {
        eCommerceRepository.deleteCheckout()
    }

    override fun searchProduct(productName: String): Flow<List<Product>> =
        eCommerceRepository.searchProduct(productName)
}