package com.app.ecommere.data.repository

import com.app.ecommere.data.source.local.LocalDataSource
import com.app.ecommere.domain.interfaces.ECommerceRepository
import com.app.ecommere.domain.model.Register
import com.app.ecommere.utils.DataMapper
import kotlinx.coroutines.flow.Flow
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
}