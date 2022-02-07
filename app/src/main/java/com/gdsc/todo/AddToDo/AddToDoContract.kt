package com.gdsc.todo.AddToDo

import android.content.Intent
import com.gdsc.todo.BasePresenter
import com.gdsc.todo.BaseView

interface AddToDoContract {
    interface View : BaseView<Presenter> {
        fun showEmptyToDoError()
    }

    interface Presenter : BasePresenter {
        fun saveToDo(title: String, content: String)
        fun sendToDo(title: String, content: String, intent: Intent)
    }
}