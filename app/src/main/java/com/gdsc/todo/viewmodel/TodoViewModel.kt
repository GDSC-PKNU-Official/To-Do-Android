package com.gdsc.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.repository.TodoRepository

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoRepository: TodoRepository = TodoRepository(application)
    private var todoItems: LiveData<List<TodoModel>> = todoRepository.getTodoList()

    fun insertTodo(todoModel: TodoModel){
        todoRepository.insertTodo(todoModel)
    }

    fun getTodoList(): LiveData<List<TodoModel>>{
        return todoItems
    }
}