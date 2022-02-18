package com.gdsc.todo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gdsc.todo.database.TodoDAO
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel

class TodoRepository(application: Application) {
    private var todoDatabase: TodoDatabase = TodoDatabase.getInstance(application)
    private var todoDAO: TodoDAO = todoDatabase.todoDAO()
    private var todoItems: LiveData<List<TodoModel>> = todoDAO.getTodoList ()

    fun getTodoList(): LiveData<List<TodoModel>> {
        return todoItems
    }

    fun insertTodo(todoModel: TodoModel) {
        Thread(Runnable {
            todoDAO.insertTodo(todoModel)
        }).start()
    }
}

