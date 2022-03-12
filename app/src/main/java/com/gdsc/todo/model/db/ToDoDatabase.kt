package com.gdsc.todo.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdsc.todo.model.entity.MyToDoList
import com.gdsc.todo.model.dao.ToDoDao

// SQLiteOpenHelper을 처리했던 작업을 다룬다.
// SQL 쿼리를 컴파일 타임에 검사하는 기능을 제공하며
// 반드시 RoomDatabase를 상속받은 추상클래스를 만들어야 한다.
// 추상 클래스로 만든 인스턴스는 주로 싱글톤으로 만든다.
// 같은 시간에 여러 개의 인스턴스에서 데이터베이스에 접근하는 것을 막기 위함이다.
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