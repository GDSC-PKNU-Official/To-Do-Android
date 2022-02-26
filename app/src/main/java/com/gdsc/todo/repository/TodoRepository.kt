package com.gdsc.todo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gdsc.todo.database.TodoDAO
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel


class TodoRepository(private val todoDAO: TodoDAO) {
    private val todoItems: LiveData<List<TodoModel>> = todoDAO.getTodoList()

    fun getTodo(): LiveData<List<TodoModel>> {
        return todoItems
    }

    fun insertTodo(todoModel: TodoModel) {
        Thread(Runnable {
            todoDAO.insert(todoModel)
        }).start()
    }
}