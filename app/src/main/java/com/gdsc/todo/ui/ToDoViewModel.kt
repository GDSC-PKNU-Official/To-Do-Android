package com.gdsc.todo.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.gdsc.todo.model.ToDoRepository
import com.gdsc.todo.model.entity.MyToDoList

// UI에 데이터를 제공하는 역할을 한다.
class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private var _myToDoSet = ArrayList<MyToDoList>()
    private var _sortMyToDoSet = ArrayList<MyToDoList>()

    val myToDoSet: List<MyToDoList>
        get() = _myToDoSet
    val sortMyToDoSet: List<MyToDoList>
        get() = _sortMyToDoSet
    var title = ""
    var content = ""

    private val repository: ToDoRepository by lazy{
        ToDoRepository(application)
    }

    init {
        Log.d(TAG, "init")
        getAll()
        // _sortMyToDoSet = _myToDoSet 얕은 복사(기존 객체에 영향 O)
        _sortMyToDoSet.addAll(_myToDoSet) // 깊은 복사(기존 객체에 영향 X)
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

    fun checkEmpty() = title!=""  && content!=""

    fun sortTitle(){
        _sortMyToDoSet.sortBy{it.title}
    }

    companion object{
        const val TAG = "ToDoViewModel"
        const val TIME: Long = 500
    }
}