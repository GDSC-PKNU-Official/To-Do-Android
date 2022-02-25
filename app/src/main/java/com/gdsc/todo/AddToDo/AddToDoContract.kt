package com.gdsc.todo.AddToDo

import com.gdsc.todo.BasePresenter
import com.gdsc.todo.BaseView
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList

interface AddToDoContract {
    interface View : BaseView<Presenter> {
        fun showEmptyToDoError()
        fun getTitlee(): String?
        fun getContent(): String?
    }

    interface Presenter : BasePresenter {
        fun saveToDo(db: ToDoDatabase)
    }
}