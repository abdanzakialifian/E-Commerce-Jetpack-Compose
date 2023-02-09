package com.app.ecommere.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.ecommere.data.source.local.dao.ECommerceDao
import com.app.ecommere.data.source.local.database.ECommerceDatabase
import com.app.ecommere.utils.DataEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        provider: Provider<ECommerceDao>
    ): ECommerceDatabase {
        return Room.databaseBuilder(context, ECommerceDatabase::class.java, "ecommerce_db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Executors.newSingleThreadScheduledExecutor().execute {
                        provider.get().insertAllProduct(DataEntity.populateData())
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideDao(eCommerceDatabase: ECommerceDatabase): ECommerceDao =
        eCommerceDatabase.eCommerceDao()
}