package com.app.ecommere.domain.usecase

import com.app.ecommere.domain.interfaces.ECommerceRepository
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.model.Register
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
}