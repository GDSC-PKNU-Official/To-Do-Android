package com.gdsc.todo.repository

import androidx.lifecycle.LiveData
import com.gdsc.todo.database.TodoDAO
import com.gdsc.todo.model.TodoModel

class TodoRepository(val todoDAO: TodoDAO) {
    fun getAllTodoList(): LiveData<List<TodoModel>> = todoDAO.getAllTodoList()

    suspend fun insert(todoModel: TodoModel) = todoDAO.insert(todoModel)

    suspend fun update(todoModel: TodoModel) = todoDAO.update(todoModel)

    suspend fun delete(todoModel: TodoModel) = todoDAO.delete(todoModel)
}