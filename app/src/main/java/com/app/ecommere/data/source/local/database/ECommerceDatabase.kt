package com.app.ecommere.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.ecommere.data.source.local.dao.ECommerceDao
import com.app.ecommere.data.source.local.entity.ProductEntity
import com.app.ecommere.data.source.local.entity.RegisterEntity
import com.app.ecommere.utils.DataEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [RegisterEntity::class, ProductEntity::class],
    version = 2,
    exportSchema = true
)
abstract class ECommerceDatabase : RoomDatabase() {
    abstract fun eCommerceDao(): ECommerceDao

    fun populateDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            eCommerceDao().insertAllProduct(DataEntity.populateData())
        }
    }
}