package com.gdsc.todo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.model.entity.MyToDoList
import com.gdsc.todo.model.local.ToDoDao

@Database(entities = [MyToDoList::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object {
        private var instance: ToDoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ToDoDatabase? {
            if(instance == null){
                synchronized(ToDoDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        "toDo-database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }
    }
}