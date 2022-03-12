package com.gdsc.todo.di.module

import com.gdsc.todo.data.local.ToDoLocalDataSource
import com.gdsc.todo.data.local.ToDoLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindToDoDataSource(dataSource: ToDoLocalDataSourceImpl): ToDoLocalDataSource
}