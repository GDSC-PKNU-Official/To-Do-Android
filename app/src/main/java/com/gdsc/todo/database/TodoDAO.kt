package com.gdsc.todo.database;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Insert
import androidx.room.Query;
import com.gdsc.todo.model.TodoModel


// Data Access Object
@Dao
interface TodoDAO {
    @Query("SELECT * FROM todoList ORDER BY timeStamp ASC")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Insert
    fun insertTodo(todoModel: TodoModel)
}
