package com.app.ecommere.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val eCommerceUseCase: ECommerceUseCase) :
    ViewModel() {
    private val _getAllProduct: MutableStateFlow<UiState<List<Product>>> =
        MutableStateFlow(UiState.Loading)
    var getAllProduct = _getAllProduct.asStateFlow()

    private val _getProductByProductCode: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState.Loading)
    val getProductByProductCode = _getProductByProductCode.asStateFlow()

    init {
        getAllProduct()
    }

    private fun getAllProduct() {
        viewModelScope.launch {
            eCommerceUseCase.getAllProduct().collect {
                _getAllProduct.value = UiState.Success(it)
            }
        }
    }

    fun getProductByProductCode(productCode: String) {
        viewModelScope.launch {
            _getProductByProductCode.value = UiState.Success(null)
            eCommerceUseCase.getProductByProductCode(productCode).collect { isAlreadyProduct ->
                _getProductByProductCode.value = UiState.Success(isAlreadyProduct)
            }
        }
    }

    fun insertCheckout(checkout: Checkout) {
        viewModelScope.launch {
            eCommerceUseCase.insertCheckout(checkout)
        }
    }
}