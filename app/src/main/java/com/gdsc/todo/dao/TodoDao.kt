package com.gdsc.todo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsc.todo.dto.TodoModel

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoModel WHERE title LIKE :todoTitle")
    fun searchTodo(todoTitle: String) : LiveData<List<TodoModel>>

    @Query("SELECT * FROM TodoModel ORDER BY timeStamp")
    fun getAllTodoList() : LiveData<List<TodoModel>>

    // OnConflictStrategy.IGNORE -> 같은 아이디가 있으면 무시함!
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todoModel: TodoModel)

    @Update
    suspend fun updateTodo(todoModel: TodoModel)

    @Delete
    suspend fun deleteTodo(todoModel: TodoModel)
}