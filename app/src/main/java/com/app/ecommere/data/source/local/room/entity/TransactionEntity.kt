package com.app.ecommere.data.source.local.room.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "tb_transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("document_number")
    val documentNumber: Int? = null,

    @ColumnInfo("document_code")
    val documentCode: String? = null,

    @ColumnInfo("user")
    val user: String? = null,

    @ColumnInfo("total")
    val total: Int? = null,

    @ColumnInfo("date")
    val date: String? = null
)