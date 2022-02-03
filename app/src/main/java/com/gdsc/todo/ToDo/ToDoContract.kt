package com.gdsc.todo.ToDo

import com.gdsc.todo.BasePresenter
import com.gdsc.todo.BaseView

interface ToDoContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

    }
}