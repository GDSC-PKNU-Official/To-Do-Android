package com.gdsc.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDAO(): TodoDAO

/*    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        // 싱글톤 생성
        fun getInstance(context: Context): TodoDatabase? {
            // 인스턴스가 존재하지 않을 경우 생성하고, 아닐 경우엔 바로 반환한다.
            if (INSTANCE == null) {
                synchronized(TodoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(  //Room.databaseBuilder 로 인스턴스를 생성
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo_table"
                    )
                        .fallbackToDestructiveMigration()  // 데이터베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
                        .build()
                }
            }
            return INSTANCE
        }
    }*/
}
