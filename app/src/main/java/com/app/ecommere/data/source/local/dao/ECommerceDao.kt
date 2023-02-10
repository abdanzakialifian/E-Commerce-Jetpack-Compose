package com.app.ecommere.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.ecommere.data.source.local.entity.CheckoutEntity
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
    suspend fun insertAllProduct(productEntity: List<ProductEntity>)

    @Query("SELECT * FROM tb_product")
    suspend fun getAllProduct(): List<ProductEntity>

    @Insert
    suspend fun insertCheckout(checkoutEntity: CheckoutEntity)

    @Query("UPDATE tb_checkout SET product_quantity = product_quantity + :productQuantity OR sub_total = sub_total * product_quantity WHERE product_code LIKE :productCode")
    suspend fun updateProductByProductCode(productCode: String, productQuantity: Int)

    @Query("SELECT EXISTS (SELECT * FROM tb_checkout WHERE product_code LIKE :productCode)")
    fun getProductByProductCode(productCode: String): Flow<Boolean>
}