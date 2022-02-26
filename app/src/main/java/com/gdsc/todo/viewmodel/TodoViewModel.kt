package com.gdsc.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDAO = TodoDatabase.getInstance(application).todoDAO()
    private val todoRepository = TodoRepository(todoDAO)


    val allTodoList = todoRepository.getAllTodoList()

    fun insert(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insert(todoModel)
        }
    }

    fun update(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.update(todoModel)
        }
    }

    fun delete(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.delete(todoModel)
        }
    }
}
