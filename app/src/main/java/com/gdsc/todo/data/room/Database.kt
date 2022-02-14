package com.gdsc.todo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gdsc.todo.data.entity.ToDo
import com.gdsc.todo.data.local.ToDoDao

@Database(entities = [ToDo::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}