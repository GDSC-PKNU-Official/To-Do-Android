package com.gdsc.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.repository.TodoRepository

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDAO = TodoDatabase.getInstance(application)!!.todoDAO()
    private val todoRepository = TodoRepository(todoDAO)
    private val todoItems: LiveData<List<TodoModel>> = todoRepository.getTodo()

    fun getTodo(): LiveData<List<TodoModel>>{
        return todoItems
    }

    fun insertTodo(todoModel: TodoModel){
        todoRepository.insertTodo(todoModel)
    }
}