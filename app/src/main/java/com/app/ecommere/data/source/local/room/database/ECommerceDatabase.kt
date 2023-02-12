package com.app.ecommere.data.source.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.ecommere.data.source.local.room.dao.ECommerceDao
import com.app.ecommere.data.source.local.room.entity.CheckoutEntity
import com.app.ecommere.data.source.local.room.entity.ProductEntity
import com.app.ecommere.data.source.local.room.entity.RegisterEntity

@Database(
    entities = [RegisterEntity::class, ProductEntity::class, CheckoutEntity::class],
    version = 4,
    exportSchema = true
)
abstract class ECommerceDatabase : RoomDatabase() {
    abstract fun eCommerceDao(): ECommerceDao
}