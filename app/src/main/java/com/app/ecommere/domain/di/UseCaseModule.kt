package com.app.ecommere.domain.di

import com.app.ecommere.domain.interfaces.ECommerceUseCase
import com.app.ecommere.domain.usecase.ECommerceUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun provideUseCase(eCommerceUseCaseImpl: ECommerceUseCaseImpl): ECommerceUseCase
}