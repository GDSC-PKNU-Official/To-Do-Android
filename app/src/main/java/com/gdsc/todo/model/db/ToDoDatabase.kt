package com.gdsc.todo.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.model.entity.MyToDoList
import com.gdsc.todo.model.dao.ToDoDao

@Database(entities = [MyToDoList::class], version = 4)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun getToDoDao(): ToDoDao

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

        fun destroyInstance() {
            instance = null
        }
    }
}