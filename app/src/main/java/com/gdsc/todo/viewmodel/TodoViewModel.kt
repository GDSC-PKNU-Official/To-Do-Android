package com.gdsc.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gdsc.todo.dao.TodoDao
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.dto.TodoModel
import com.gdsc.todo.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao: TodoDao = TodoDatabase.getInstance(application)!!.todoDao()
    private val todoRepository: TodoRepository = TodoRepository(todoDao)
    private val getAllTodo: LiveData<List<TodoModel>> = todoRepository.getAllTodoList

    fun getAllTodo() = getAllTodo

    fun searchTodo(todoTitle: String): LiveData<List<TodoModel>> = todoRepository.searchTodo(todoTitle)

    fun addTodo(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.addTodo(todoModel)
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todoModel)
        }
    }

    fun deleteTodo(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todoModel)
        }
    }
}