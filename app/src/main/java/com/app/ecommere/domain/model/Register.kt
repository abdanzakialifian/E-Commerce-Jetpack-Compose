package com.app.ecommere.domain.model

data class Register(
    val userId: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null
)