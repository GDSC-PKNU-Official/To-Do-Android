package com.gdsc.todo.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.model.entity.MyToDoList
import com.gdsc.todo.model.dao.ToDoDao

@Database(entities = [MyToDoList::class], version = 6)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun getToDoDao(): ToDoDao

    // 데이터베이스 객체를 인스턴스 할 때 싱글톤으로 구현
    companion object {
        private var instance: ToDoDatabase? = null

        // 데이터베이스 객체를 반환
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

        // 데이터베이스 객체를 삭제
        fun destroyInstance() {
            instance = null
        }
    }
}