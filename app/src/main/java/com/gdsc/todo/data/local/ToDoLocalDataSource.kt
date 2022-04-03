package com.gdsc.todo.data.local

import androidx.lifecycle.LiveData
import com.gdsc.todo.data.entity.ToDo
import com.gdsc.todo.data.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoLocalDataSource {

    fun getToDoList(): LiveData<List<ToDoEntity>?>

    fun addToDo(toDo: ToDoEntity)

    suspend fun getToDoListSortedByTitle(): Flow<List<ToDoEntity>>
}