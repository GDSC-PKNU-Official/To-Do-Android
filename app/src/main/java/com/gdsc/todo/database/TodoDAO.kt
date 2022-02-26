package com.gdsc.todo.database;

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsc.todo.model.TodoModel

@Dao
interface TodoDAO {
    @Query("SELECT * FROM tb_todoList ORDER BY timeStamp ASC")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)    // 입력 중복일 경우 덮어쓰기.
    fun insert(todoModel: TodoModel)

}
