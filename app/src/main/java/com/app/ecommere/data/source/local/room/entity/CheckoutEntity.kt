package com.app.ecommere.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tb_checkout")
data class CheckoutEntity(
    @PrimaryKey
    @ColumnInfo("product_code")
    val productCode: String = "",

    @ColumnInfo("document_number")
    val documentNumber: String? = null,

    @ColumnInfo("document_code")
    val documentCode: String? = null,

    @ColumnInfo("product_price")
    val productPrice: Int? = null,

    @ColumnInfo("product_quantity")
    val productQuantity: Int? = null,

    @ColumnInfo("unit")
    val unit: String? = null,

    @ColumnInfo("sub_total")
    val subTotal: Int? = null,

    @ColumnInfo("currency")
    val currency: String? = null,

    @ColumnInfo("image_name")
    val imageName: String? = null,

    @ColumnInfo("product_name")
    val productName: String? = null,
)