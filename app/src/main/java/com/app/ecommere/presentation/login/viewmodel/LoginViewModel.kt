package com.app.ecommere.presentation.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val eCommerceUseCase: ECommerceUseCase) :
    ViewModel() {
    private val _getUserByEmail: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState.Loading)
    val getUserByEmail = _getUserByEmail.asStateFlow()

    var isButtonClicked by mutableStateOf(false)

    fun getUserByEmail(email: String, password: String) {
        viewModelScope.launch {
            eCommerceUseCase.getUserByEmail(email, password).collect { isAlreadyAccount ->
                _getUserByEmail.value = UiState.Success(isAlreadyAccount)
            }
        }
    }

    fun setUserSession(isLogin: Boolean) {
        viewModelScope.launch {
            eCommerceUseCase.saveUserSession(isLogin)
        }
    }

    fun saveUserData(email: String) {
        viewModelScope.launch {
            eCommerceUseCase.saveUserData(email)
        }
    }
}