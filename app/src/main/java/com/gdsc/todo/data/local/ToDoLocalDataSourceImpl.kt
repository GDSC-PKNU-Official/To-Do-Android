package com.gdsc.todo.data.local

import com.gdsc.todo.data.entity.ToDo
import javax.inject.Inject

class ToDoLocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : ToDoLocalDataSource {

    override fun getToDoList(): List<ToDo> = toDoDao.fetchAllToDo()

    override fun addToDo(toDo: ToDo) {
        toDoDao.insertToDo(toDo)
    }
}