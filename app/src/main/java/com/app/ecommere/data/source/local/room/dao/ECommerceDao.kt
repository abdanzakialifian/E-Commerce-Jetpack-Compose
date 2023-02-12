package com.app.ecommere.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.ecommere.data.source.local.room.entity.CheckoutEntity
import com.app.ecommere.data.source.local.room.entity.ProductEntity
import com.app.ecommere.data.source.local.room.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ECommerceDao {
    @Insert
    suspend fun insertUserRegister(registerEntity: RegisterEntity)

    @Query("SELECT EXISTS (SELECT * FROM tb_register WHERE email LIKE :email AND password LIKE :password)")
    fun getUserByEmail(email: String, password: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProduct(productEntity: List<ProductEntity>)

    @Query("SELECT * FROM tb_product")
    suspend fun getAllProduct(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckout(checkoutEntity: CheckoutEntity)

    @Query("UPDATE tb_checkout SET product_quantity = product_quantity + :productQuantity, sub_total = (product_quantity + 1) * product_price WHERE product_code LIKE :productCode")
    suspend fun updateProductByProductCode(productCode: String, productQuantity: Int)

    @Query("SELECT EXISTS (SELECT * FROM tb_checkout WHERE product_code LIKE :productCode)")
    fun getProductByProductCode(productCode: String): Flow<Boolean>

    @Query("SELECT COUNT(*) FROM tb_checkout")
    fun getCheckoutCount(): Flow<Int>

    @Query("SELECT * FROM tb_checkout")
    fun getAllCheckout(): Flow<List<CheckoutEntity>>

    @Query("SELECT * FROM tb_product WHERE product_id LIKE :productId")
    fun getProductById(productId: Int): Flow<ProductEntity>

    @Query("SELECT * FROM tb_register WHERE email LIKE :email")
    fun getUserData(email: String): Flow<RegisterEntity>
}