package com.gdsc.todo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsc.todo.data.entity.ToDo
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM `todo` ORDER BY title")
    fun fetchAllToDoSortedByTitle(): Flow<List<ToDo>>
}