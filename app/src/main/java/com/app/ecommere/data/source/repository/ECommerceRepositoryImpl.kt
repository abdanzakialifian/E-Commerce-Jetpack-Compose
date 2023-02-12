package com.app.ecommere.data.source.repository

import com.app.ecommere.data.source.local.LocalDataSource
import com.app.ecommere.domain.interfaces.ECommerceRepository
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register
import com.app.ecommere.domain.model.Transaction
import com.app.ecommere.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ECommerceRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    ECommerceRepository {
    override fun insertUserRegister(register: Register) {
        val registerEntity = DataMapper.mapRegisterToRegisterEntity(register)
        localDataSource.insertUserRegister(registerEntity)
    }

    override fun getUserByEmail(email: String, password: String): Flow<Boolean> =
        localDataSource.getUserByEmail(email, password)

    override fun getAllProduct(): Flow<List<Product>> = localDataSource.getAllProduct().map {
        DataMapper.mapProductEntityToProduct(it)
    }

    override fun insertCheckout(checkout: Checkout) {
        val checkoutEntity = DataMapper.mapCheckoutToCheckoutEntity(checkout)
        localDataSource.insertCheckout(checkoutEntity)
    }

    override fun updateProductByProductCode(productCode: String, productQuantity: Int) {
        localDataSource.updateProductByProductCode(productCode, productQuantity)
    }

    override fun getProductByProductCode(productCode: String): Flow<Boolean> =
        localDataSource.getProductByProductCode(productCode)

    override fun getCheckoutCount(): Flow<Int> = localDataSource.getCheckoutCount()
    override fun getAllCheckout(): Flow<List<Checkout>> = localDataSource.getAllCheckout().map {
        DataMapper.mapCheckoutEntityToCheckout(it)
    }

    override fun getProductById(productId: Int): Flow<Product> =
        localDataSource.getProductById(productId).map {
            DataMapper.mapProductEntityToProduct(it)
        }

    override fun saveUserSession(isLogin: Boolean) {
        localDataSource.saveUserSession(isLogin)
    }

    override fun getUserSession(): Flow<Boolean> = localDataSource.getUserSession()
    override fun getUserData(email: String): Flow<Register> =
        localDataSource.getUserData(email).map {
            DataMapper.mapRegisterEntityToRegister(it)
        }

    override fun getUserData(): Flow<String> = localDataSource.getUserData()

    override fun saveUserData(email: String) {
        localDataSource.saveUserData(email)
    }

    override fun insertTransaction(transaction: Transaction) {
        val transactionEntity = DataMapper.mapTransactionToTransactionEntity(transaction)
        localDataSource.insertTransaction(transactionEntity)
    }

    override fun deleteCheckout() {
        localDataSource.deleteCheckout()
    }
}