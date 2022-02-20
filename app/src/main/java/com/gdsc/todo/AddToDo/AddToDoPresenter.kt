package com.gdsc.todo.AddToDo

import android.content.Intent
import android.util.Log
import com.gdsc.todo.R
import com.gdsc.todo.model.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddToDoPresenter(val addToDoView: AddToDoContract.View): AddToDoContract.Presenter {
    // Presenter가 연산이 끝나고 화면을 갱신해주기 위해서는 뷰를 알고 있어야 한다.

    // 프리젠터 초기화
    init {
        addToDoView.presenter = this
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun saveToDo(db: ToDoDatabase, title: String, content: String) {
        // room에 투두 추가하기
        val newToDo = MyToDoList(0, title, content)
        db!!.toDoDao().insert(newToDo)


    }

    override fun sendToDo(title: String, content: String, intent: Intent) {
        intent.putExtra(R.string.title.toString(), title)
        intent.putExtra(R.string.content.toString(), content)
    }
}