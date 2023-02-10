package com.app.ecommere.data.repository

import com.app.ecommere.data.source.local.LocalDataSource
import com.app.ecommere.domain.interfaces.ECommerceRepository
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register
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
}