package com.gdsc.todo.di

import android.content.Context
import androidx.room.Room
import com.gdsc.todo.data.room.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDatabaseModule { // TODO: 왜 object?

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        Database::class.java,
        "AppDatabase.db"
    )
//        .createFromAsset("default.db")
        .build()

    @Provides
    fun provideToDoDao(database: Database) = database.toDoDao()
}