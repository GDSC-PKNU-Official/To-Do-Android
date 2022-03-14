package com.gdsc.todo.database;

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsc.todo.model.TodoModel

@Dao
interface TodoDAO {
    // TODO 일단 MutableList형식으로 넣고 실행되는거 확인하면 일반 List로 사용해보기
    @Query("SELECT * FROM todo_Table ORDER BY timeStamp ASC")
    fun getAllTodoList(): LiveData<MutableList<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)    // 입력 중복일 경우 덮어쓰기.
    fun insert(todoModel: TodoModel)

    @Update
    suspend fun update(todoModel: TodoModel)

    @Delete
    fun delete(todoModel: TodoModel)
}
