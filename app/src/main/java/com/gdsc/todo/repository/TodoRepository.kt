package com.gdsc.todo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gdsc.todo.database.TodoDAO
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel

class TodoRepository(application: Application) {
    private var todoDatabase: TodoDatabase = TodoDatabase.getInstance(application)!!
    private var todoDAO: TodoDAO = todoDatabase.todoDAO()
    private var todoItems: LiveData<List<TodoModel>> = todoDAO.getAllTodoList()

    fun getAllTodoList(): LiveData<List<TodoModel>> = todoItems

    suspend fun insert(todoModel: TodoModel) {
        try {
            todoDAO.insert(todoModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun delete(todoModel: TodoModel) {
        try {
            todoDAO.delete(todoModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}