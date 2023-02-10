package com.app.ecommere.data.source.local

import com.app.ecommere.data.source.local.dao.ECommerceDao
import com.app.ecommere.data.source.local.entity.CheckoutEntity
import com.app.ecommere.data.source.local.entity.ProductEntity
import com.app.ecommere.data.source.local.entity.RegisterEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val eCommerceDao: ECommerceDao) {
    fun insertUserRegister(registerEntity: RegisterEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            eCommerceDao.insertUserRegister(registerEntity)
        }
    }

    fun getUserByEmail(email: String, password: String): Flow<Boolean> =
        eCommerceDao.getUserByEmail(email, password)

    fun getAllProduct(): Flow<List<ProductEntity>> = flow { emit(eCommerceDao.getAllProduct()) }

    fun insertCheckout(checkoutEntity: CheckoutEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            eCommerceDao.insertCheckout(checkoutEntity)
        }
    }

    fun updateProductByProductCode(productCode: String, productQuantity: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            eCommerceDao.updateProductByProductCode(productCode, productQuantity)
        }
    }

    fun getProductByProductCode(productCode: String): Flow<Boolean> =
        eCommerceDao.getProductByProductCode(productCode)
}