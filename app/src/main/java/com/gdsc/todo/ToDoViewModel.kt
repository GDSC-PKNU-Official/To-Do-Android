package com.gdsc.todo

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.ToDo.TAG2
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
        Thread{
            _myToDoSet = repository.getAllToDo() as ArrayList<MyToDoList>
        }.start()
        Thread.sleep(TIME)
    }

    fun addButtonClick(){
        if(title.toString()!=null  && content.toString()!=null){
            Thread{
                val newToDo = MyToDoList(title = title.toString(), content = content.toString())
                repository.insert(newToDo)
            }.start()
            Thread.sleep(TIME) // 있어야 제목이 저장됨(왜?)
        } else{

        }
    }

    companion object{
        const val TAG = "ToDoViewModel"
        const val TIME: Long = 1000
    }
}