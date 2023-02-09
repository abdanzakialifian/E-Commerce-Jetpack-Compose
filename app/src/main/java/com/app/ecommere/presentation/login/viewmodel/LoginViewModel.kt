package com.app.ecommere.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val eCommerceUseCase: ECommerceUseCase) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Boolean>> get() = _uiState

    fun getUserByEmail(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Success(null)
            eCommerceUseCase.getUserByEmail(email, password).collect { isAlreadyAccount ->
                _uiState.value = UiState.Success(isAlreadyAccount)
            }
        }
    }
}