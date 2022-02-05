package com.gdsc.todo.AddToDo

import com.gdsc.todo.BasePresenter
import com.gdsc.todo.BaseView

interface AddToDoContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {
        
    }
}