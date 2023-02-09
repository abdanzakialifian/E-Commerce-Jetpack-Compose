package com.app.ecommere.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.model.Register
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val eCommerceUseCase: ECommerceUseCase) :
    ViewModel() {
    fun insertUserRegister(register: Register) {
        eCommerceUseCase.insertUserRegister(register)
    }
}