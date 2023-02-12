package com.app.ecommere.presentation.checkout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.model.Checkout
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

    fun getAllCheckout() {
        viewModelScope.launch {
            eCommerceUseCase.getAllCheckout().collect { checkouts ->
                _getAllCheckout.value = UiState.Success(checkouts)
            }
        }
    }
}