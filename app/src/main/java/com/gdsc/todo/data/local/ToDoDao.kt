package com.gdsc.todo.data.local

import androidx.room.*
import com.gdsc.todo.data.entity.ToDo

@Dao
interface ToDoDao {

    @Insert
    fun insertToDo(toDo: ToDo)

    @Query("SELECT * FROM `todo`")
    fun fetchAllToDo(): List<ToDo>

    @Delete
    fun deleteToDo(toDo: ToDo)

    @Update
    fun updateToDo(toDo: ToDo)
}