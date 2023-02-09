package com.app.ecommere.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.ecommere.data.source.local.entity.ProductEntity
import com.app.ecommere.data.source.local.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ECommerceDao {
    @Insert
    suspend fun insertUserRegister(registerEntity: RegisterEntity)

    @Query("SELECT EXISTS (SELECT * FROM tb_register WHERE email LIKE :email AND password LIKE :password)")
    fun getUserByEmail(email: String, password: String): Flow<Boolean>

    @Insert
    fun insertAllProduct(productEntity: List<ProductEntity>)

    @Query("SELECT * FROM tb_product")
    fun getAllProduct(): List<ProductEntity>
}