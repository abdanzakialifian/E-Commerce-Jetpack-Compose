package com.app.ecommere.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.ecommere.data.source.local.dao.ECommerceDao
import com.app.ecommere.data.source.local.entity.CheckoutEntity
import com.app.ecommere.data.source.local.entity.ProductEntity
import com.app.ecommere.data.source.local.entity.RegisterEntity

@Database(
    entities = [RegisterEntity::class, ProductEntity::class, CheckoutEntity::class],
    version = 3,
    exportSchema = true
)
abstract class ECommerceDatabase : RoomDatabase() {
    abstract fun eCommerceDao(): ECommerceDao
}