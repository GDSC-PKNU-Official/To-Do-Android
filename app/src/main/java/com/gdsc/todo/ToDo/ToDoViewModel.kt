package com.gdsc.todo.ToDo

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdsc.todo.AddToDo.AddToDoPresenter
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList

// UI에 데이터를 제공하는 역할을 한다.
class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        const val TAG="ToDoViewModel"
    }

    // 내부에서 설정하는 자료형은 뮤터블로 변경가능하도록 설정
    private var _myToDoSet = ArrayList<MyToDoList>()

    // 공개적으로 가져오는 변수는 외부에서도 접근 가능하도록 설정
    // 하지만 값을 직접 라이브데이터에 접근하지 않고 뷰모델을 통해 가져올 수 있도록 설정
    val myToDoSet: List<MyToDoList>
        get() = _myToDoSet
    val title = ObservableField<String>()
    val content = ObservableField<String>()

    private val repository: ToDoRepository by lazy{
        ToDoRepository(application)
    }

    init {
        Log.d(TAG, "init")
        _myToDoSet = repository.getAllToDo() as ArrayList<MyToDoList>
    }

    fun addButtonClick(){
        if(title.toString()!=null  && content.toString()!=null){
            Thread{
                val newToDo = MyToDoList(title = title.toString(), content = content.toString())
                repository.insert(newToDo)
            }.start()
            Thread.sleep(1000) // 있어야 제목이 저장됨(왜?)
        } else{

        }
    }
}