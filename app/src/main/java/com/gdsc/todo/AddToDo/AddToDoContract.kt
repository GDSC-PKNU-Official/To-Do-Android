package com.gdsc.todo.AddToDo

import android.content.Intent
import com.gdsc.todo.BasePresenter
import com.gdsc.todo.BaseView
import com.gdsc.todo.model.MyToDoList

interface AddToDoContract {
    interface View : BaseView<Presenter> {
        fun showEmptyToDoError()
    }

    interface Presenter : BasePresenter {
        fun saveToDo(myToDoSet: MutableList<MyToDoList>, title: String, content: String)
        fun sendToDo(title: String, content: String, intent: Intent)
    }
}