package com.gdsc.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.dao.TodoDao
import com.gdsc.todo.dto.TodoModel

/**
 * 데이터베이스를 매번 생성하는 것을 비효율적이다!
 * 따라서, 싱글톤 패턴을 사용하여 구현한다.
 */

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: TodoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TodoDatabase? {
            if (INSTANCE == null) {
                synchronized(TodoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "TodoList.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}