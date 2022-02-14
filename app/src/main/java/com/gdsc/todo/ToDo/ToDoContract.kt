package com.gdsc.todo.ToDo

import com.gdsc.todo.BasePresenter
import com.gdsc.todo.BaseView
import com.gdsc.todo.model.MyToDoList

interface ToDoContract {
    interface View : BaseView<Presenter> {
        fun setTitle(): String
        fun setContent(): String
        fun setRecyclerView(myToDoSet: List<MyToDoList>)
    }

    interface Presenter : BasePresenter {
        fun addToDo(myToDoSet: MutableList<MyToDoList>, title: String, content: String)
    }
}