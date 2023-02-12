package com.app.ecommere.domain.model

data class Transaction(
    val documentNumber: Int? = null,
    val documentCode: String? = null,
    val user: String? = null,
    val total: Int? = null,
    val date: String? = null
)