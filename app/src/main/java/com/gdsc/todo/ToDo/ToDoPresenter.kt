package com.gdsc.todo.ToDo

import com.gdsc.todo.model.entity.MyToDoList

class ToDoPresenter(val toDoView: ToDoContract.View): ToDoContract.Presenter {
    // Presenter가 연산이 끝나고 화면을 갱신해주기 위해서는 뷰를 알고 있어야 한다.

    init {
        toDoView.presenter = this
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun addToDo(myToDoSet: MutableList<MyToDoList>, title: String, content: String) {
        myToDoSet.add(MyToDoList(0, title, content))
    }

}