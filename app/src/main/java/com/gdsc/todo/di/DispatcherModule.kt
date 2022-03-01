package com.gdsc.todo.di

import com.gdsc.todo.DispatcherProvider
import com.gdsc.todo.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DispatcherModule {

    @Binds
    fun bindDispatcherModule(provider: DispatcherProviderImpl): DispatcherProvider
}