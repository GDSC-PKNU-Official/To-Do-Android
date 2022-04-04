package com.gdsc.todo.data.local

import com.gdsc.todo.data.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoLocalDataSource {

    fun getToDoList(): Flow<List<ToDoEntity>?>

    fun addToDo(toDo: ToDoEntity)

    suspend fun getToDoListSortedByTitle(): Flow<List<ToDoEntity>>
}