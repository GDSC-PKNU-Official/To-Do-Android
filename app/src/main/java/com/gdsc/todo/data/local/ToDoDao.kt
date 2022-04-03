package com.gdsc.todo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsc.todo.data.entity.ToDo
import com.gdsc.todo.data.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert
    fun insertToDo(toDo: ToDoEntity)

    @Query("SELECT * FROM `todoentity`")
    fun fetchAllToDo(): LiveData<List<ToDoEntity>?>

    @Delete
    fun deleteToDo(toDo: ToDoEntity)

    @Update
    fun updateToDo(toDo: ToDoEntity)

    @Query("SELECT * FROM `todoentity` ORDER BY title")
    fun fetchAllToDoSortedByTitle(): Flow<List<ToDoEntity>>
}