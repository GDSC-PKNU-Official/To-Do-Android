package com.gdsc.todo.ToDo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdsc.todo.model.dao.ToDoDao
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList

// 싱글톤 패턴으로 뷰모델마다 동일한 Repository 인스턴스로 접근하여 데이터를 로드하도록 도와준다.
// 여러 개의 데이터 소스를 관리하는 클래스이다.
class ToDoRepository(application: Application) {

    // lazy? application?
    private val toDoDao: ToDoDao by lazy{
        val db = ToDoDatabase.getInstance(application)!!
        db.getToDoDao()
    }

    private val myToDoSet: List<MyToDoList> = toDoDao.getAll()

    fun getAllToDo(): List<MyToDoList>{
        return myToDoSet
    }

    fun insert(newToDo: MyToDoList){
        toDoDao.insert(newToDo)
    }

    fun delete(id: Long){
        TODO()
    }
}