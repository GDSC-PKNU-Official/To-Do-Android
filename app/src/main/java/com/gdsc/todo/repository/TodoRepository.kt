package com.gdsc.todo.repository

import androidx.lifecycle.LiveData
import com.gdsc.todo.dao.TodoDao
import com.gdsc.todo.dto.TodoModel

class TodoRepository(private val todoDao: TodoDao) {
    // todo 이것도 prival화 해서 함수로 getter setter하는게 나으려나??
    val getAllTodoList: LiveData<List<TodoModel>> = todoDao.getAllTodoList()

    fun searchTodo(todoTitle: String): LiveData<List<TodoModel>> = todoDao.searchTodo(todoTitle)

    suspend fun addTodo(todoModel: TodoModel) {
        todoDao.addTodo(todoModel)
    }

    suspend fun updateTodo(todoModel: TodoModel) {
        todoDao.updateTodo(todoModel)
    }

    suspend fun deleteTodo(todoModel: TodoModel) {
        todoDao.deleteTodo(todoModel)
    }
}