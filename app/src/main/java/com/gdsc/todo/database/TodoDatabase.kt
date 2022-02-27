package com.gdsc.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDAO(): TodoDAO

    companion object {

        private var INSTANCE: TodoDatabase? = null

        //getInstance() :  여러 스레드가 접근하지 못하도록 synchronized로 설정
        fun getInstance(context: Context): TodoDatabase? {

            if (INSTANCE == null) {
                synchronized(TodoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(  //Room.databaseBuilder 로 인스턴스를 생성
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "tb_todo"
                    )
                        .fallbackToDestructiveMigration()  //.fallbackToDestructiveMigration() : 데이터베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
                        .build()
                }
            }
            return INSTANCE
        }

    }

/*    // 싱글톤 사용
    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        // 여러 스레드가 접근하지 못하도록
        fun getInstance(context: Context): TodoDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "tb_todoList"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }*/
}

/*        fun getInstance(context: Context): TodoDatabase{
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
        }*/



