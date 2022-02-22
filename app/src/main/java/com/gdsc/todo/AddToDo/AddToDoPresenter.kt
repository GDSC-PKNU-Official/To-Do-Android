package com.gdsc.todo.AddToDo

import android.util.Log
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList

class AddToDoPresenter(val addToDoView: AddToDoContract.View): AddToDoContract.Presenter {
    // Presenter가 연산이 끝나고 화면을 갱신해주기 위해서는 뷰를 알고 있어야 한다.

    // 프리젠터 초기화
    init {
        addToDoView.presenter = this
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    // room에 투두 추가하기
    override fun saveToDo(db: ToDoDatabase, title: String, content: String) {
        Thread{
            val newToDo = MyToDoList(title = title, content = content)
            db.getToDoDao().insert(newToDo)
        }.start()
    }
}