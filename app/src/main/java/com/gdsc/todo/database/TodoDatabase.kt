package com.gdsc.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDAO(): TodoDAO

    // 싱글톤 사용
    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "tb_todoList"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

//        fun getInstance(context: Context): TodoDatabase {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        TodoDatabase::class.java,
//                        "tb_todoList"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//            } else {
//                throw IllegalStateException("TodoRepository 초기화 해야합니다!")
//            }
//            return INSTANCE
//        }
    }
}

