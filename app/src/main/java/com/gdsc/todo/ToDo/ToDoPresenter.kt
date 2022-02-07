package com.gdsc.todo.ToDo

class ToDoPresenter(val toDoView: ToDoContract.View): ToDoContract.Presenter {
    // Presenter가 연산이 끝나고 화면을 갱신해주기 위해서는 뷰를 알고 있어야 한다.
    init {
        toDoView.presenter = this
    }

    override fun start() {
        TODO("Not yet implemented")
    }
}