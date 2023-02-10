package com.app.ecommere.presentation.login.viewmodel

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
    private val _getUserByEmail: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Loading)
    val getUserByEmail = _getUserByEmail.asStateFlow()

    fun getUserByEmail(email: String, password: String) {
        viewModelScope.launch {
            _getUserByEmail.value = UiState.Success(null)
            eCommerceUseCase.getUserByEmail(email, password).collect { isAlreadyAccount ->
                _getUserByEmail.value = UiState.Success(isAlreadyAccount)
            }
        }
    }
}