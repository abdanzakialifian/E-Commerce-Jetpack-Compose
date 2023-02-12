package com.app.ecommere.presentation.checkout.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Transaction
import com.app.ecommere.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val eCommerceUseCase: ECommerceUseCase) :
    ViewModel() {
    private val _getAllCheckout: MutableStateFlow<UiState<List<Checkout>>> =
        MutableStateFlow(UiState.Loading)
    val getAllCheckout = _getAllCheckout.asStateFlow()

    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    val email = _email.asStateFlow()

    var isButtonClicked by mutableStateOf(false)

    fun getAllCheckout() {
        viewModelScope.launch {
            eCommerceUseCase.getAllCheckout().collect { checkouts ->
                _getAllCheckout.value = UiState.Success(checkouts)
            }
        }
    }

    fun insertTransaction(transaction: Transaction) {
        viewModelScope.launch {
            eCommerceUseCase.insertTransaction(transaction)
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            eCommerceUseCase.getUserData().collect { email ->
                _email.value = email
            }
        }
    }

    fun deleteCheckout() {
        viewModelScope.launch {
            eCommerceUseCase.deleteCheckout()
        }
    }
}