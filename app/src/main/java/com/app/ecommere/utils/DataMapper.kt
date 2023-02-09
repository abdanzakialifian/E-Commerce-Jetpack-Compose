package com.app.ecommere.utils

import com.app.ecommere.data.source.local.entity.RegisterEntity
import com.app.ecommere.domain.model.Register

object DataMapper {
    fun mapRegisterToRegisterEntity(input: Register): RegisterEntity =
        RegisterEntity(name = input.name, email = input.email, password = input.password)
}