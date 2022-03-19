package com.gdsc.todo.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.gdsc.todo.model.ToDoRepository
import com.gdsc.todo.model.entity.MyToDoList

// UI에 데이터를 제공하는 역할을 한다.
class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private var _myToDoSet = ArrayList<MyToDoList>()

    val myToDoSet: List<MyToDoList>
        get() = _myToDoSet
    var title = ""
    var content = ""

    private val repository: ToDoRepository by lazy{
        ToDoRepository(application)
    }

    init {
        Log.d(TAG, "init")
        getAll()
    }

    fun getAll(){
        Thread{
            _myToDoSet = repository.getAllToDo() as ArrayList<MyToDoList>
        }.start()
        Thread.sleep(TIME)
    }

    fun addButtonClick(){
        Thread{
            val newToDo = MyToDoList(title = title.toString(), content = content.toString())
            repository.insert(newToDo)
        }.start()
        Thread.sleep(TIME) // 있어야 제목이 저장됨(왜?)
    }

    fun deleteToDo(toDo: MyToDoList){
        Thread{
            repository.delete(toDo)
        }.start()
    }

    fun checkEmpty(): Boolean{
        return title!=""  && content!=""
    }

    companion object{
        const val TAG = "ToDoViewModel"
        const val TIME: Long = 1000
    }
}