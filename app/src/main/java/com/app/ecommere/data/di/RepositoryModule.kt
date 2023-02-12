package com.app.ecommere.data.di

import com.app.ecommere.data.source.repository.ECommerceRepositoryImpl
import com.app.ecommere.domain.interfaces.ECommerceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(eCommerceRepositoryImpl: ECommerceRepositoryImpl): ECommerceRepository
}