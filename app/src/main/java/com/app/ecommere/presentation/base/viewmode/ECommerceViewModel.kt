package com.app.ecommere.presentation.base.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommere.domain.interfaces.ECommerceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ECommerceViewModel @Inject constructor(private val eCommerceUseCase: ECommerceUseCase) :
    ViewModel() {
    private val _getUserSession: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val getUserSession = _getUserSession.asStateFlow()

    fun getUserSession() {
        viewModelScope.launch {
            eCommerceUseCase.getUserSession().collect { isLogin ->
                _getUserSession.value = isLogin
            }
        }
    }
}