package com.gdsc.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc.todo.database.TodoDAO
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    // TODO viewModelScope 사용해보기

    private val todoRepository: TodoRepository = TodoRepository.get()
//    val todoList: LiveData<MutableList<TodoModel>> = todoRepository.getAllTodoList()

    fun getAllTodoList() = todoRepository.getAllTodoList()

    fun insert(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insert(todoModel)
        }
    }

    fun update(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.update(todoModel)
        }
    }

    fun delete(todoModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.update(todoModel)
        }
    }


/*
{
    private val todoDAO: TodoDAO = TodoDatabase.getInstance(application)!!.todoDAO()
    private val todoRepository: TodoRepository = TodoRepository(todoDAO)
//    val getAllTodoList: LiveData<List<TodoModel>> = todoRepository.getAllTodoList()

//    fun getAllTodoList() = todoRepository.getAllTodoList()

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
*/
}
