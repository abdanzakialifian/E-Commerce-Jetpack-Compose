package com.app.ecommere.data.source.local

import com.app.ecommere.data.source.local.datastore.ECommerceDataStore
import com.app.ecommere.data.source.local.room.dao.ECommerceDao
import com.app.ecommere.data.source.local.room.entity.CheckoutEntity
import com.app.ecommere.data.source.local.room.entity.ProductEntity
import com.app.ecommere.data.source.local.room.entity.RegisterEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val eCommerceDao: ECommerceDao,
    private val eCommerceDataStore: ECommerceDataStore
) {
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

    fun getCheckoutCount(): Flow<Int> = eCommerceDao.getCheckoutCount()

    fun getAllCheckout(): Flow<List<CheckoutEntity>> = eCommerceDao.getAllCheckout()

    fun getProductById(productId: Int): Flow<ProductEntity> = eCommerceDao.getProductById(productId)

    fun saveUserSession(isLogin: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            eCommerceDataStore.saveUserSession(isLogin)
        }
    }

    fun getUserSession(): Flow<Boolean> = eCommerceDataStore.getUserSession()

    fun getUserData(email: String): Flow<RegisterEntity> =
        eCommerceDao.getUserData(email)

    fun saveUserData(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            eCommerceDataStore.saveUserData(email)
        }
    }

    fun getUserData(): Flow<String> = eCommerceDataStore.getUserData()
}