package com.app.ecommere.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tb_checkout")
data class CheckoutEntity(
    @PrimaryKey
    @ColumnInfo("document_number")
    val documentNumber: String = "",

    @ColumnInfo("document_code")
    val documentCode: String? = null,

    @ColumnInfo("product_code")
    val productCode: String? = null,

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
)