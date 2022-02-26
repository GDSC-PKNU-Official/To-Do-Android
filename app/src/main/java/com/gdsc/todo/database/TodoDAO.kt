package com.gdsc.todo.database;

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsc.todo.model.TodoModel

@Dao
interface TodoDAO {
    @Query("SELECT * FROM tb_todoList ORDER BY timeStamp ASC")
    fun getAllTodoList(): LiveData<List<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)    // 입력 중복일 경우 덮어쓰기.
    suspend fun insert(todoModel: TodoModel)

    @Update
    suspend fun update(todoModel: TodoModel)

    @Delete
    suspend fun delete(todoModel: TodoModel)
}
