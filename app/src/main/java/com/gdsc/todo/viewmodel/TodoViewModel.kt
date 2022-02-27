package com.gdsc.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository = TodoRepository(application)

    fun getAllTodoList() = todoRepository.getAllTodoList()

    fun insert(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insert(todoModel)
        }
    }

    fun delete(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.delete(todoModel)
        }
    }
}
