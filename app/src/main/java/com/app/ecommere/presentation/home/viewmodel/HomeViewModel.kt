package com.app.ecommere.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.model.Checkout
import com.app.ecommere.domain.model.Product
import com.app.ecommere.domain.model.Register
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

    private val _getCheckoutCount: MutableStateFlow<UiState<Int>> =
        MutableStateFlow(UiState.Loading)
    val getCheckoutCount = _getCheckoutCount.asStateFlow()

    private val _getUserData: MutableStateFlow<UiState<Register>> =
        MutableStateFlow(UiState.Loading)
    val getUserData = _getUserData.asStateFlow()

    var isButtonClicked by mutableStateOf(false)

    var isLogoutClicked by mutableStateOf(false)

    fun getAllProduct() {
        viewModelScope.launch {
            eCommerceUseCase.getAllProduct().collect { products ->
                _getAllProduct.value = UiState.Success(products)
            }
        }
    }

    fun getProductByProductCode(productCode: String) {
        viewModelScope.launch {
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

    fun updateProductByProductCode(productCode: String, productQuantity: Int) {
        viewModelScope.launch {
            eCommerceUseCase.updateProductByProductCode(productCode, productQuantity)
        }
    }

    fun getCheckoutCount() {
        viewModelScope.launch {
            eCommerceUseCase.getCheckoutCount().collect { count ->
                _getCheckoutCount.value = UiState.Success(count)
            }
        }
    }

    fun setUserSession(isLogin: Boolean) {
        viewModelScope.launch {
            eCommerceUseCase.saveUserSession(isLogin)
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            eCommerceUseCase.getUserData().collect { email ->
                getUserData(email)
            }
        }
    }

    private fun getUserData(email: String) {
        viewModelScope.launch {
            eCommerceUseCase.getUserData(email).collect { data ->
                _getUserData.value = UiState.Success(data)
            }
        }
    }
}