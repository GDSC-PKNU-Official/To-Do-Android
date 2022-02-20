package com.gdsc.todo.ToDo

import com.gdsc.todo.BasePresenter
import com.gdsc.todo.BaseView
import com.gdsc.todo.model.entity.MyToDoList

interface ToDoContract {
    interface View : BaseView<Presenter> {
        fun setRecyclerView(myToDoSet: List<MyToDoList>)
    }

    interface Presenter : BasePresenter {
        fun addToDo(myToDoSet: MutableList<MyToDoList>, title: String, content: String)
    }
}