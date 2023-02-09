package com.app.ecommere.data.source.local

import com.app.ecommere.data.source.local.dao.ECommerceDao
import com.app.ecommere.data.source.local.entity.RegisterEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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
}