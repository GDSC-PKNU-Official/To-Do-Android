package com.gdsc.todo.data.local

import com.gdsc.todo.data.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoLocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : ToDoLocalDataSource {

    override fun getToDoList(): Flow<List<ToDoEntity>?> = toDoDao.fetchAllToDo()

    override fun addToDo(toDo: ToDoEntity) = toDoDao.insertToDo(toDo)

    override suspend fun getToDoListSortedByTitle() = toDoDao.fetchAllToDoSortedByTitle()
}