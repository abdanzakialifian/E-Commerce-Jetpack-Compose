package com.app.ecommere.domain.interfaces

import com.app.ecommere.domain.model.Register
import kotlinx.coroutines.flow.Flow

interface ECommerceRepository {
    fun insertUserRegister(register: Register)
    fun getUserByEmail(email: String,  password: String): Flow<Boolean>
}