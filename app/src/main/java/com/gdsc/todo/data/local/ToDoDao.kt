package com.gdsc.todo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsc.todo.data.entity.ToDo

@Dao
interface ToDoDao {

    @Insert
    fun insertToDo(toDo: ToDo)

    @Query("SELECT * FROM `todo`")
    fun fetchAllToDo(): LiveData<List<ToDo>?>

    @Delete
    fun deleteToDo(toDo: ToDo)

    @Update
    fun updateToDo(toDo: ToDo)
}