package com.gdsc.todo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.gdsc.todo.database.TodoDAO
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel

private const val DATABASE = "todo-database.db"

class TodoRepository private constructor(context: Context) {
    // TODO 싱글톤 패턴을 데이터베이스에 넘겨보기
    companion object {
        private var INSTANCE: TodoRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = TodoRepository(context)
            }
        }

        fun get(): TodoRepository{
            return INSTANCE ?:
            throw IllegalStateException("TodoRepository Must Be Initialized")
        }
    }

    private val database: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        DATABASE
    ).build()

    private val todoDAO: TodoDAO = database.todoDAO()

    fun getAllTodoList(): LiveData<MutableList<TodoModel>> = todoDAO.getAllTodoList()

    fun insert(todoModel: TodoModel) = todoDAO.insert(todoModel)

    suspend fun update(todoModel: TodoModel) = todoDAO.update(todoModel)

    fun delete(todoModel: TodoModel) = todoDAO.delete(todoModel)

/*    fun getAllTodoList(): LiveData<MutableList<TodoModel>> {
        try{
            todoDAO.getAllTodoList()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun insert(todoModel: TodoModel) {
        try {
            todoDAO.insert(todoModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun delete(todoModel: TodoModel) {
        try {
            todoDAO.delete(todoModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
 */


/*
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
    }*/
}