package com.app.ecommere.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("product_id")
    val productId: Int? = null,

    @ColumnInfo("product_code")
    val productCode: String? = null,

    @ColumnInfo("product_name")
    val productName: String? = null,

    @ColumnInfo("product_price")
    val productPrice: Int? = null,

    @ColumnInfo("currency")
    val currency: String? = null,

    @ColumnInfo("discount")
    val discount: Int? = null,

    @ColumnInfo("dimension")
    val dimension: String? = null,

    @ColumnInfo("unit")
    val unit: String? = null,

    @ColumnInfo("image_name")
    val imageName: String? = null,

    @ColumnInfo("description")
    val description: String? = null
)