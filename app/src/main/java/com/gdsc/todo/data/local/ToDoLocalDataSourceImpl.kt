package com.gdsc.todo.data.local

import androidx.lifecycle.LiveData
import com.gdsc.todo.data.entity.ToDo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoLocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : ToDoLocalDataSource {

    override fun getToDoList(): LiveData<List<ToDo>?> = toDoDao.fetchAllToDo()

    override fun addToDo(toDo: ToDo) {
        toDoDao.insertToDo(toDo)
    }

    override suspend fun getToDoListSortedByTitle() = toDoDao.fetchAllToDoSortedByTitle()
}